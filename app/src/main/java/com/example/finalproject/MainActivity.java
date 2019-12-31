package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private EditText nameValue;
    private EditText ageValue;
    private EditText hurtValue;
    private static Toast t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInit();
        setListener();
    }

    private void setInit(){
        button1 = findViewById(R.id.button1);
        nameValue = findViewById(R.id.eText1);
        ageValue = findViewById(R.id.eText2);
        hurtValue = findViewById(R.id.eText3);
    }

    private void setListener(){
        button1.setOnClickListener(bEvent);
    }

    private View.OnClickListener bEvent = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try
            {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, search.class);
                Bundle bundle = new Bundle();
                bundle.putString("nameValue", nameValue.getText().toString());
                bundle.putString("ageValue", ageValue.getText().toString());
                bundle.putString("hurtValue", hurtValue.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);

            }
            catch (Exception e)
            {
                Toast.makeText(MainActivity.this, "please enter completly1", Toast.LENGTH_SHORT).show();
            }
        }
    };


}
