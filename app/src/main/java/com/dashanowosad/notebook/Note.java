package com.dashanowosad.notebook;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class Note extends RealmObject {
    @Required //не может быть пустым
    private String Title;

    @Required
    private String Note;

    public void SetTitle(String title){
        this.Title = title;
    }

    public String GetTitle(){
        return this.Title;
    }

    public void SetNote(String note){
        this.Note = note;
    }

    public String GetNot(){
        return this.Note;
    }
}
