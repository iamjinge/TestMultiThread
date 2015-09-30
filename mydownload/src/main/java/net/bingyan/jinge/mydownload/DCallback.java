package net.bingyan.jinge.mydownload;

public interface DCallback {
	void getSize(String url, int size);

	void getPercent(String url, int size);
}
