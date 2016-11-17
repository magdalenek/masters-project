package com.charitable.charity.crud.model;


import org.springframework.data.annotation.Id;

public class Note {

    @Id
    private String id;
    private String note;

    public Note(){}

    public Note(String id, String note) {
        this.id = id;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
