package com.dashanowosad.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;



import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {


    private Realm realm;
    private Note note;
    protected RelativeLayout relativeLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(MainActivity.this);

        this.realm = Realm.getInstance(new RealmConfiguration.Builder().name("MyRealm").build());
        //this.realm = Realm.getDefaultInstance();

        this.relativeLayout = new RelativeLayout(this);
        this.AddButton();


        this.Print();
        this.DellAll();

        setContentView(this.relativeLayout);

        this.realm.close();
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


    public void Print(){
        RealmResults<Note> res = this.realm.where(Note.class).findAll();

        String Title = "";
        String Color = "";
        String Note = "";
        RealmList <String> List =  new RealmList<>();


        for(Note n: res){
            Title += n.GetTitle();
            Color += n.GetColor();
            Note += n.GetNote();
            for(int i = 0; i < n.GetList().size(); ++i)
                List.add(n.GetList().get(i));
        }

        TextView textView = new TextView(this);
        textView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);

        textView.setText(Title + "\n" + Color + "\n" + Note + "\n");
        for(int i = 0; i < List.size(); ++i)
            textView.setText(List.get(i) + ", ");

        this.relativeLayout.addView(textView);
    }

    public void AddNote(){

        this.realm.beginTransaction();
        this.note = new Note();

        this.note = realm.createObject(Note.class);
        this.note.SetNote("Some text");
        this.note.SetTitle("It`s example");
        this.note.SetColor("GREEN");

        RealmList <String> r = new RealmList<String>();
        r.add("fefef");

        this.note.SetList(r);


        this.realm.commitTransaction();

        RealmResults<Note> res = this.realm.where(Note.class).findAll();

        String s = null;
        for(Note n: res) {
            s += n.GetColor();

            r = n.GetList();
        }
        String  str2 = r.get(0);
        TextView textView = new TextView(this);
        textView.setText(str2);

        this.relativeLayout.addView(textView);

    }

    public void DellAll(){
        this.realm.beginTransaction();
        this.realm.delete(Note.class);
        this.realm.commitTransaction();
    }
}
