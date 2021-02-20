package com.gshivansh37.internshala;

public class Note {
    private String title;
    private String noteData;

    public Note(String title, String noteData) {
        this.title = title;
        this.noteData = noteData;
    }

    public String getTitle() {
        return title;
    }

    public String getNoteData() {
        return noteData;
    }
}
