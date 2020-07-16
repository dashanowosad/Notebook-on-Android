package com.dashanowosad.notebook;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    private  RelativeLayout relativeLayout;
    private Integer LastIdOfEditText;
    private RelativeLayout.LayoutParams paramsPlu;
    private Integer Weight;
    private Integer TitleId;
    private Integer PlusButId;
    private ArrayList <Pair <Integer, String>> ArrayTextEditId = new ArrayList<>();
    private ArrayList <Integer> ArrayMinusButId = new ArrayList<>();
    private Integer NumberOfMemo;

    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);

        Bundle w = getIntent().getExtras();
        this.Weight = (Integer) w.get("Weight");

        Bundle arg = getIntent().getExtras();
        String name = arg.get("Color").toString();

        this.relativeLayout = new RelativeLayout(this);
        this.NumberOfMemo = 1;

        this.Title();
        this.EditTextList(this.TitleId);
        this.PlusBut();


        setContentView(this.relativeLayout);

    }

    private void Title(){
        EditText editText = new EditText(this);
        editText.setHint("Заголовок");
        editText.setTextSize(20);
        editText.setId(Integer.valueOf(1));

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        this.relativeLayout.addView(editText, params);

        this.TitleId = editText.getId();
    }

    private void EditTextList(int id){
        final EditText editText = new EditText(this);
        editText.setId(this.NumberOfMemo + 20);
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        this.LastIdOfEditText = this.NumberOfMemo + 20;


        editText.setHint("Пункт " + (this.NumberOfMemo));
        this.NumberOfMemo++;

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                this.Weight - this.Weight/16,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        params.addRule(RelativeLayout.BELOW, id);


        editText.addTextChangedListener(new TextWatcher() {
           private String str;
           private Integer DoubleEmpty;

           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                this.str = "";
                this.DoubleEmpty = 0;
           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
                this.str += s;
                if(this.str.length() == 0)
                    this.DoubleEmpty++;
           }

           @Override
           public void afterTextChanged(Editable s) {
               if(this.str.contains("\n")){
                   List.this.EditTextList(List.this.LastIdOfEditText);
                   List.this.paramsPlu.addRule(RelativeLayout.BELOW, List.this.LastIdOfEditText);
                   this.str = str.replace("\n","");
                   editText.setText(this.str);
                   TextView NewFocus = findViewById(Integer.valueOf(List.this.LastIdOfEditText));
                   NewFocus.requestFocus();
               }

           }
       });

        this.relativeLayout.addView(editText, params);
        this.MinusBut(editText.getId());

        Pair <Integer, String> pair = new Pair<Integer, String>(List.this.LastIdOfEditText,  "");

        List.this.ArrayTextEditId.add(pair);

    }

    private void PlusBut(){
        Drawable PlusImage = getResources().getDrawable(R.drawable.plus);
        PlusImage.setBounds(0,0,30,30);


        final Button PlusBut = new Button(this);
        PlusBut.setCompoundDrawables(PlusImage,null,null,null);
        PlusBut.setText("  Новый пункт");
        PlusBut.setBackground(Drawable.createFromPath("#14ffffff"));
        PlusBut.setId(View.generateViewId());
        this.PlusButId = PlusBut.getId();

        this.paramsPlu = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        PlusBut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                List.this.EditTextList(List.this.LastIdOfEditText);
                List.this.paramsPlu.addRule(RelativeLayout.BELOW, List.this.LastIdOfEditText);
                TextView NewFocus = findViewById(Integer.valueOf(List.this.LastIdOfEditText));
                NewFocus.requestFocus();
                //List.this.MinusBut(List.this.LastIdOfEditText);

            }
        });

        this.paramsPlu.addRule(RelativeLayout.BELOW, this.LastIdOfEditText);

        this.relativeLayout.addView(PlusBut, this.paramsPlu);
        }

        public void MinusBut(final int id) {
            Drawable DrawableMinus = getResources().getDrawable(R.drawable.minus);
            DrawableMinus.setBounds(0, 0, 30, 30);

            final Button MinusBut = new Button(this);
            MinusBut.setCompoundDrawables(DrawableMinus, null, null, null);
            MinusBut.setId(this.NumberOfMemo + 40);
            this.ArrayMinusButId.add(MinusBut.getId());
            //MinusBut.setBackground(Drawable.createFromPath("#14ffffff"));

            final RelativeLayout.LayoutParams paramsMin = new RelativeLayout.LayoutParams(
                    this.Weight / 13,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );

            paramsMin.addRule(RelativeLayout.ALIGN_BOTTOM, id);
            paramsMin.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

            this.relativeLayout.addView(MinusBut, paramsMin);

            final Integer MinButIdNow = MinusBut.getId();

            MinusBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List.this.GetTextFromTextEdit();

                    Button PlusBut = findViewById(List.this.PlusButId);
                    List.this.relativeLayout.removeView(PlusBut);

                    for (int i = 0; i < List.this.ArrayMinusButId.size(); ++i) {
                        EditText editTextToDel = findViewById(List.this.ArrayTextEditId.get(i).first);


                        List.this.relativeLayout.removeView(editTextToDel);

                        Button buttonToDel = findViewById(List.this.ArrayMinusButId.get(i));
                        List.this.relativeLayout.removeView(buttonToDel);

                    }

                    String s = "";
                    for (int i = 0; i < List.this.ArrayMinusButId.size(); ++i)
                        s += List.this.ArrayTextEditId.get(i).first + " ";
                    Log.v("s = ", s);


                    for (int i = 0; i < List.this.ArrayTextEditId.size(); ++i) {
                        if (List.this.ArrayMinusButId.get(i) == MinButIdNow)
                            List.this.ArrayMinusButId.remove(i);
                        if(List.this.ArrayTextEditId.get(i).first == id)
                            List.this.ArrayTextEditId.remove(i);


                    }

                    String s0 = "";
                    for (int i = 0; i < List.this.ArrayMinusButId.size(); ++i)
                        s0 += List.this.ArrayTextEditId.get(i).first + " ";
                    Log.v("s0 = ", s0 + " " + id);


                    Integer kol = List.this.ArrayTextEditId.size();

                    List.this.NumberOfMemo = 1;
                    for (int i = 0; i < kol; ++i) {
                        if (i == 0)
                            List.this.EditTextList(List.this.TitleId);

                        else
                            List.this.EditTextList(List.this.ArrayTextEditId.get(List.this.ArrayTextEditId.size() - 1).first);



                        EditText NewEditText = findViewById(List.this.ArrayTextEditId.get(List.this.ArrayTextEditId.size() - 1).first);
                        NewEditText.setText(List.this.ArrayTextEditId.get(0).second);


                        List.this.ArrayTextEditId.remove(0);
                        List.this.ArrayMinusButId.remove(0);

                    }

                    for(int i = 0; i < kol; ++i){
                        EditText NewEditText;
                        if (List.this.ArrayTextEditId.get(i).first == id){
                            NewEditText = findViewById(id);
                            NewEditText.requestFocus();
                            break;
                        }
                    }
                    String s2 = "";
                    for (int i = 0; i < List.this.ArrayMinusButId.size(); ++i)
                        s2 += List.this.ArrayTextEditId.get(i).first + " ";
                    Log.v("s2 = ", s2);


                    //TextView NewFocus = findViewById(Integer.valueOf(List.this.LastIdOfEditText));
                    //NewFocus.requestFocus();

                }
            });
        }


            private void GetTextFromTextEdit(){

                ArrayList <Pair <Integer, String>> arrayList = new ArrayList<>();
                for(int i = 0; i < this.ArrayTextEditId.size(); ++i) {
                    EditText editText = findViewById(List.this.ArrayTextEditId.get(i).first);
                    Pair <Integer, String> pair = new Pair(this.ArrayTextEditId.get(i).first, this.ArrayTextEditId.get(i).second.concat(editText.getText().toString()));
                    arrayList.add(pair);
                }
                this.ArrayTextEditId = arrayList;
            }




}
