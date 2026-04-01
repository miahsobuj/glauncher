package com.glauncher.launcher.models;

/**
 * Model class for a text note
 * Used by the Notepad tool
 */
public class Note {

    private int id;
    private String title;
    private String content;
    private long timestamp;

    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
