package net.bingyan.jinge.mydownload.old;

/**
 * Created on 2015/9/29.
 */
public class AtomTask {
    private String url;
    private int start;
    private int end;
    private String filePath;

    private int size;

    public AtomTask(String url, int start, int end, String filePath) {
        this.url = url;
        this.start = start;
        this.end = end;
        this.filePath = filePath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
