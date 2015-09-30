package net.bingyan.jinge.mydownload;

public class FileUtil
{
	private static String MAIN_DIR = "/storage/emulated/0/TestFile/";
	private static String CACHE_DIR = "cache/";
	private static String SPLIT = "/";
	
	public static void checkPath(){
		
	}
	
	public static String getCachePath() {
		return MAIN_DIR + CACHE_DIR;
	}
	
	public static String getFileNameFromUrl(String url){
		int lastIndex = url.lastIndexOf(SPLIT);
		return url.substring(lastIndex+1,url.length());
	}
	
	public static String getPathFromUrl(String url){
		return getCachePath() + getFileNameFromUrl(url);
	}
}
