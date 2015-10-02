package net.bingyan.jinge.testmultithread;

public interface DCallback {
	void getSize(String url, int size);

	void getPercent(String url, int partId, int size);
}
