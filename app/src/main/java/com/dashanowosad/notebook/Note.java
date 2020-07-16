package com.dashanowosad.notebook;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Required;

public class Note extends RealmObject {

    private String Title;

    private String Note;

    private RealmList <String> List;

    @Required //не может быть пустым
    private String Color;

    public Note(){
        this.List = new RealmList<>();
    }

    public void SetTitle(String title){
        this.Title = title;
    }

    public String GetTitle(){
        return this.Title;
    }

    public void SetNote(String note){
        this.Note = note;
    }

    public String GetNote(){
        return this.Note;
    }

    public void SetList(RealmList <String> list){
        this.List = list;
    }

    public RealmList<String> GetList(){
        return this.List;
    }

    public void SetColor (String color){
        this.Color = color;
    }

    public String GetColor(){
        return this.Color;
    }


}
