package com.dashanowosad.notebook;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;



public class Menu extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private ColorStateList colorStateList;
    private Integer Heigt;
    private Integer Weight;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        this.DisplayMetricks();
        this.relativeLayout = new RelativeLayout(this);
//Change Color radio button
        this.colorStateList = new ColorStateList( new int[][]{ new int[]{android.R.attr.state_enabled}},
                new int[] {getResources().getColor(R.color.colorPrimary) } );

        this.ChooseColor(this.AddType());
        this.BackButton();
        this.CreateButton();

        setContentView(this.relativeLayout);
    }

    @SuppressLint("RestrictedApi")
    private int AddType(){
        //Text
        TextView PaperTypeText = new TextView(this);
        PaperTypeText.setText("Выберите тип:");
        PaperTypeText.setTextSize(20);
        PaperTypeText.setId(Integer.valueOf(1));
        PaperTypeText.setBackgroundColor(Color.rgb(168,255,210));
        RelativeLayout.LayoutParams paremsForTextType = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        //RadioGroup and RadioButton
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        radioGroup.setId(Integer.valueOf(2));
        radioGroup.setPadding(0,50,0,50);

        final AppCompatRadioButton Paper = new AppCompatRadioButton(this);
        Paper.setText("Заметка");
        Paper.setTextSize(18);
        Paper.setId(Integer.valueOf(3));
        Paper.setChecked(true);

        AppCompatRadioButton List = new AppCompatRadioButton(this);
        List.setText("Список");
        List.setTextSize(18);

        radioGroup.addView(Paper);
        radioGroup.addView(List);

        //Listener RadioGroup


        //Change Color radio button
        Paper.setSupportButtonTintList(this.colorStateList);
        List.setSupportButtonTintList(this.colorStateList);


        RelativeLayout.LayoutParams paremsForRadioGroup = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        paremsForRadioGroup.addRule(RelativeLayout.BELOW, PaperTypeText.getId());




        this.relativeLayout.addView(PaperTypeText, paremsForTextType);
        this.relativeLayout.addView(radioGroup, paremsForRadioGroup);

        return radioGroup.getId();
    }

    @SuppressLint("RestrictedApi")
    private void ChooseColor(int RadioButId){
        //Text
        TextView ColorTypeText = new TextView(this);
        ColorTypeText.setText("Выберите цвет заметки:");
        ColorTypeText.setTextSize(20);
        ColorTypeText.setId(Integer.valueOf(5));
        ColorTypeText.setBackgroundColor(Color.rgb(168,255,210));
        RelativeLayout.LayoutParams paremsForTextType = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        //RadioGroup and RadioButton
        paremsForTextType.addRule(RelativeLayout.BELOW, RadioButId);

        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        radioGroup.setPadding(0,70,0,0);

        AppCompatRadioButton [] MassOfColor = new AppCompatRadioButton[8];

        for(int i = 0; i < 8; ++i)
            MassOfColor[i] = new AppCompatRadioButton(this);


        MassOfColor[0].setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.white), null);
        MassOfColor[1].setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.red), null);
        MassOfColor[2].setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.orange), null);
        MassOfColor[3].setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.yellow), null);
        MassOfColor[4].setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.green), null);
        MassOfColor[5].setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.blue), null);
        MassOfColor[6].setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.purple), null);
        MassOfColor[7].setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.brown), null);



        int k = 7;
        //Change Color radio button
        for(int i = 0; i < 8; ++i) {
            MassOfColor[i].setSupportButtonTintList(this.colorStateList);
            radioGroup.addView(MassOfColor[i]);
            MassOfColor[i].setId(k);
            ++k;
        }
        MassOfColor[0].setChecked(true);


        RelativeLayout.LayoutParams paremsForRadioGroup = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        paremsForRadioGroup.addRule(RelativeLayout.BELOW, ColorTypeText.getId());

        this.relativeLayout.addView(ColorTypeText, paremsForTextType);
        this.relativeLayout.addView(radioGroup, paremsForRadioGroup);
    }

    private void BackButton(){
        Button BackButton = new Button(this);
        BackButton.setText("Назад");

        RelativeLayout.LayoutParams paramsForBackButton = new RelativeLayout.LayoutParams(
                this.Weight/2,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        paramsForBackButton.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        paramsForBackButton.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        this.relativeLayout.addView(BackButton, paramsForBackButton);

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CreateButton(){
        Button SaveButton = new Button(this);
        SaveButton.setText("Создать");

        RelativeLayout.LayoutParams paramsForSaveButton = new RelativeLayout.LayoutParams(
                this.Weight/2,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        paramsForSaveButton.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        paramsForSaveButton.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        this.relativeLayout.addView(SaveButton, paramsForSaveButton);



        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatRadioButton RadBut = findViewById(Integer.valueOf(3));
                AppCompatRadioButton Color[] = new AppCompatRadioButton[8];

                int k = 7, ColorChecked = 1;
                for(int i = 0; i < 8; ++i) {
                    Color[i] = findViewById(k);
                    if(Color[i].isChecked())
                        ColorChecked = k-6;
                    ++k;
                }

                if(RadBut.isChecked()){
                    Intent intent = new Intent(Menu.this, Memo.class);
                    intent.putExtra("Color", ColorChecked);
                    intent.putExtra("Weight", Weight);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Menu.this, List.class);
                    intent.putExtra("Color", ColorChecked);
                    intent.putExtra("Weight", Weight);
                    startActivity(intent);
                }

            }
        });
    }

    private void DisplayMetricks(){
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        this.Heigt = displayMetrics.heightPixels;
        this.Weight = displayMetrics.widthPixels;
    }
}
