package com.example.tapthecolors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    Button spielen;
    ImageButton info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spielen = findViewById(R.id.spielenButton);
        info = findViewById(R.id.infoButton);

        Intent spielenActivity = new Intent(MainActivity.this, SpielActivity.class);
        Intent infoActivity = new Intent(MainActivity.this, InfoActivity.class);

        spielen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(spielenActivity);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(infoActivity);
            }
        });

    }
}