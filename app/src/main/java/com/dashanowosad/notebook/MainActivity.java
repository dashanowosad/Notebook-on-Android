package com.dashanowosad.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Config;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {


    private Realm realm;
    private Note note;
    protected RelativeLayout relativeLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        this.realm = Realm.getInstance(config);

        //this.AddNote();

        this.relativeLayout = new RelativeLayout(this);
        this.AddButton();

        setContentView(this.relativeLayout);
        
    }


    public void AddButton(){
        Button addButton = new Button(this);
        addButton.setText("Добавить");

        RelativeLayout.LayoutParams AddButtonParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        AddButtonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        AddButtonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        this.relativeLayout.addView(addButton, AddButtonParams);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Menu.class);
                startActivity(intent);
            }
        });
    }




    public void AddNote(){

        this.realm.beginTransaction();

        this.note = realm.createObject(Note.class);
        this.note.SetNote("Some text");
        this.note.SetTitle("It`s example");

        this.realm.commitTransaction();

        RealmResults<Note> res = this.realm.where(Note.class).findAll();

        String s = null;
        for(Note n: res)
            s += n.GetNot();

        TextView textView = new TextView(this);
        textView.setText(s);
        setContentView(textView);



    }
}
