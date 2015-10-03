package net.bingyan.jinge.mydownload;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.UnknownFormatConversionException;
import java.util.*;

/**
 * Created on 2015/10/2.
 */
public class DownloadDataHelper {
    public static String DB_NAME = "download_data";
    public static int DB_VERSION = 1;

    private static DownloadDataHelper instance;
    private DownloadDatabase downloadDatabase;
    private SQLiteDatabase database;

    private DownloadDataHelper(Context context) {
        downloadDatabase = new DownloadDatabase(context, DB_NAME, null, DB_VERSION);
        database = downloadDatabase.getWritableDatabase();
    }

    public static DownloadDataHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (DownloadDataHelper.class) {
                if (instance == null) {
                    instance = new DownloadDataHelper(context);
                }
            }
        }
        return instance;
    }

    public synchronized void addData(int wid, int start, int end) {
        Cursor cursor = database.query(DownloadData.TABLE_NAME, new String[]{DownloadData.ID},
                DownloadData.START + " <= ? and " + DownloadData.END + " >= ? ",
                new String[]{String.valueOf(start), String.valueOf(end)},
                null, null, null);

        if (cursor.getCount() == 0) {
            Cursor union = database.query(DownloadData.TABLE_NAME,
                    new String[]{DownloadData.ID, DownloadData.START, DownloadData.END},
                    DownloadData.END + " = ? or " + DownloadData.START + " = ? ",
                    new String[]{String.valueOf(start - 1), String.valueOf(end + 1)},
                    null, DownloadData.START, null);
            if (union.getCount() == 2) {
                union.moveToFirst();
                int firstId = union.getInt(union.getColumnIndex(DownloadData.ID));
                start = union.getInt(union.getColumnIndex(DownloadData.START));
                union.moveToNext();
                int secondId = union.getInt(union.getColumnIndex(DownloadData.ID));
                end = union.getInt(union.getColumnIndex(DownloadData.END));

                database.delete(DownloadData.TABLE_NAME, DownloadData.ID + "in (?)",
                        new String[]{firstId + ", " + secondId});
            } else if (union.getCount() == 1) {
                union.moveToFirst();
                int aId = union.getInt(union.getColumnIndex(DownloadData.ID));
                int aStart = union.getInt(union.getColumnIndex(DownloadData.START));
                int aEnd = union.getInt(union.getColumnIndex(DownloadData.END));
                if (start > aStart) {
                    start = aStart;
                } else {
                    end = aEnd;
                }
                database.delete(DownloadData.TABLE_NAME, DownloadData.ID + " = ? ",
                        new String[]{String.valueOf(aId)});
            }
            union.close();

            database.delete(DownloadData.TABLE_NAME, DownloadData.WORK_ID + " = ? and " +
                            DownloadData.START + " >= ? and " + DownloadData.END + " <= ? ",
                    new String[]{String.valueOf(wid), String.valueOf(start), String.valueOf(end)});

            ContentValues values = new ContentValues();
            values.put(DownloadData.WORK_ID, wid);
            values.put(DownloadData.START, start);
            values.put(DownloadData.END, end);
            database.insert(DownloadData.TABLE_NAME, null, values);
        }
        cursor.close();

    }

    public synchronized void addData(String url, int start, int end){
        int wid = getIdOfUrl(url);
        addData(wid, start, end);
    }

    public synchronized int getIdOfUrl(String url) {
        Cursor cursor = database.query(SizeData.TABLE_NAME, new String[]{SizeData.ID},
                SizeData.URL + " = ? ", new String[]{url},
                null, null, null);
        int wid;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            wid =  cursor.getInt(cursor.getColumnIndex(SizeData.ID));
        } else {
            wid =  -1;
        }
        cursor.close();
        return wid;
    }

    public List<Integer> getAbcentData(int wid, int size) {
        Cursor cursor = database.query(DownloadData.TABLE_NAME,
                new String[]{DownloadData.START, DownloadData.END},
                DownloadData.WORK_ID + " = ? ",
                new String[]{String.valueOf(wid)},
                null, null, null);

        List<Integer> parts = new ArrayList<>();
        cursor.moveToFirst();
		if(cursor.getInt(cursor.getColumnIndex(DownloadData.START))!=0){
			parts.add(0);
			parts.add(cursor.getInt(cursor.getColumnIndex(DownloadData.START)));
		}
        int start = cursor.getInt(cursor.getColumnIndex(DownloadData.END))+1;
        int end;
        while (cursor.moveToNext()) {
            end = cursor.getInt(cursor.getColumnIndex(DownloadData.START))-1;
			parts.add(start);
			parts.add(end);
            start = cursor.getInt(cursor.getColumnIndex(DownloadData.END))+1;
        }
		if(cursor.getInt(cursor.getColumnIndex(DownloadData.END))!=size-1){
			parts.add(cursor.getInt(cursor.getColumnIndex(DownloadData.END))+1);
			parts.add(size-1);
		}
		return parts;
    }

}
