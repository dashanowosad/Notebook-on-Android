package com.dashanowosad.notebook;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class List extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);

        Bundle arg = getIntent().getExtras();
        String name = arg.get("Color").toString();

        Log.v("fefeff", name);
    }
}
