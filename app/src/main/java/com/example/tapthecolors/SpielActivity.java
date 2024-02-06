package com.example.tapthecolors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tapthecolors.services.ColorGenerator;
import com.example.tapthecolors.services.schwererColorGenerator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SpielActivity extends AppCompatActivity {

    Button[] buttons = new Button[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel);

        buttons[0] = findViewById(R.id.button);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[4] = findViewById(R.id.button5);
        buttons[5] = findViewById(R.id.button6);
        buttons[6] = findViewById(R.id.button7);
        buttons[7] = findViewById(R.id.button8);
        buttons[8] = findViewById(R.id.button9);

        ConstraintLayout view = findViewById(R.id.activity_spiel);

        schwererColorGenerator colorGenerator = new schwererColorGenerator();

        Random random = new Random();
        Integer indexRichtigerButton = random.nextInt(9);
        String richtigeFarbeHex = "#FFFFFF";

        Intent spielActivity = new Intent(SpielActivity.this, SpielActivity.class);
        Intent gameOverActivity = new Intent(SpielActivity.this, GameOverActivity.class);

        Integer counter = 0;

        ArrayList<String> neunFarben = colorGenerator.Farbe(100);

        for (int i = 0; i < buttons.length; i++){
            Log.println(Log.DEBUG, "huaraChabis", String.valueOf(neunFarben.get(i)));
            buttons[i].setBackgroundColor(Color.parseColor(neunFarben.get(i)));
        }
        Log.println(Log.DEBUG, "richtige Farbe", neunFarben.get(indexRichtigerButton));
        Log.println(Log.DEBUG, "richtige Farbe ID", String.valueOf(indexRichtigerButton));
        view.setBackgroundColor(Color.parseColor(neunFarben.get(indexRichtigerButton)));

        for (int i = 0; i < buttons.length; i++) {
            // Log.println(Log.DEBUG, "debugging", String.valueOf(farbe));
            if (view.getBackground() == buttons[i].getBackground()) {
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(spielActivity);
                    }
                });
            } else {
                // buttons[i].setBackgroundColor(Color.parseColor(neunFarben.get(i)));
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(gameOverActivity);
                    }
                });
// test
            }
            // buttons[i].setBackgroundColor(Color.parseColor(neunFarben.get(0)));
        }
    }
}