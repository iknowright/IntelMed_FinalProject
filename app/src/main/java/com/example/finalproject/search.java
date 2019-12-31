package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class search extends AppCompatActivity {

    private Button btnSuggest;
    private Button btnNormal;
    private Button btnAudio;
    private String name;
    private int age;
    private String injury_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setInit();
    }

    private void setInit() {
        btnSuggest = findViewById(R.id.btnSuggestSearch);
        btnNormal = findViewById(R.id.btnNormalSearch);
        btnAudio = findViewById(R.id.btnAudioSearch);


    }
}
