package com.dashanowosad.notebook;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Binder;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ShareActionProvider;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Memo extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private Integer Weight;
    private String color;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.relativeLayout = new RelativeLayout(this);


        Bundle w = getIntent().getExtras();
        this.Weight = (Integer) w.get("Weight");

        Bundle c = getIntent().getExtras();
        this.color = c.get("Color").toString();

        this.TextOfMemo(this.Title());
        this.SaveBut();
        this.BaskBut();

        setContentView(this.relativeLayout);
    }

    private  int Title(){
        EditText editText = new EditText(this);
        editText.setHint("Заголовок");
        editText.setTextSize(20);
        editText.setId(Integer.valueOf(1));

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        this.relativeLayout.addView(editText, params);

        return editText.getId();
    }

    private void TextOfMemo(int IdTitle){
        EditText editText = new EditText(this);
        editText.setHint("Напишите что-нибудь");
        editText.setTextSize(18);
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        editText.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        editText.setHorizontalScrollBarEnabled(true);
        editText.setId(Integer.valueOf(2));

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        params.addRule(RelativeLayout.BELOW, IdTitle);

        this.relativeLayout.addView(editText, params);
    }

    private void SaveBut(){
        Button SaveBut = new Button(this);
        SaveBut.setText("Сохранить");

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                this.Weight/2,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);


        SaveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText textTitle = findViewById(Integer.valueOf(1));
                EditText textNote = findViewById(Integer.valueOf(2));


                Realm.init(Memo.this);
                Realm realm = Realm.getInstance(new RealmConfiguration.Builder().name("MyRealm").build());

                realm.beginTransaction();

                Note note = new Note();
                note = realm.createObject(Note.class);
                note.SetColor(this.SetColor(Memo.this.color));
                note.SetTitle(String.valueOf(textTitle.getText()));
                note.SetNote(String.valueOf(textNote.getText()));

                realm.commitTransaction();
                realm.close();
            }

            private String SetColor(String number){
                switch(number){
                    case"1":
                        return "WHITE";

                    case"2":
                        return "RED";

                    case"3":
                        return "ORANGE";

                    case"4":
                        return "YELLOW";

                    case"5":
                        return "GREEN";

                    case"6":
                        return "BLUE";

                    case"7":
                        return "PURPLE";

                    case"8":
                        return "BROWN";

                    default:
                        return "ERROR";
                }
            }
        });

        this.relativeLayout.addView(SaveBut, params);
    }

    private void BaskBut(){
        Button BaskBut = new Button(this);
        BaskBut.setText("Отмена");

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                this.Weight/2,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);


        BaskBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Memo.this, Menu.class);
                startActivity(intent);
            }
        });

        this.relativeLayout.addView(BaskBut, params);
    }



}
