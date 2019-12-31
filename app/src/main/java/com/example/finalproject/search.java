package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class search extends AppCompatActivity {

    private Button btnSuggest;
    private Button btnNormal;
    private Button btnAudio;
    private String name;
    private int age;
    private String injury_history;
    private static Toast t1, t2, t3;

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
        sendVar();

    }

    private void sendVar(){
        Bundle bundle = this.getIntent().getExtras();

        age = Integer.parseInt(bundle.getString("ageValue"));
        try
        {
            Toast.makeText(search.this, age, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(search.this, "please enter completly", Toast.LENGTH_SHORT).show();
        }



    }
}
