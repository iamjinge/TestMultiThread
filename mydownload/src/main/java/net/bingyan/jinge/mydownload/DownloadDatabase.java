package net.bingyan.jinge.mydownload;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created on 2015/10/2.
 */
public class DownloadDatabase extends SQLiteOpenHelper {
    public DownloadDatabase(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDownloadTable = "CREATE TABLE IF NOT EXISTS " +
                DownloadData.TABLE_NAME + " (" +
                DownloadData.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DownloadData.WORK_ID + " INTEGER," +
                DownloadData.START + " INTEGER," +
                DownloadData.END + " INTEGER)";

        String createSizeTable = "CREATE TABLE IF NOT EXISTS " +
                SizeData.TABLE_NAME + " (" +
                SizeData.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SizeData.URL + " VARCHAR," +
                SizeData.SIZE + " INTEGER)";

        db.execSQL(createDownloadTable);
        db.execSQL(createSizeTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
