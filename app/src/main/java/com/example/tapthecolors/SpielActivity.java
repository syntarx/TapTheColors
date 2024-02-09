package com.example.tapthecolors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tapthecolors.services.schwererColorGenerator;

import java.util.ArrayList;
import java.util.Random;

public class SpielActivity extends AppCompatActivity {

    Button[] buttons = new Button[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel);

        // loop
        buttons[0] = findViewById(R.id.button4);
        buttons[1] = findViewById(R.id.button1);
        buttons[2] = findViewById(R.id.button5);
        buttons[3] = findViewById(R.id.button7);
        buttons[4] = findViewById(R.id.button3);
        buttons[5] = findViewById(R.id.button2);
        buttons[6] = findViewById(R.id.button8);
        buttons[7] = findViewById(R.id.button6);
        buttons[8] = findViewById(R.id.button);

        ConstraintLayout view = findViewById(R.id.activity_spiel);

        schwererColorGenerator colorGenerator = new schwererColorGenerator();

        Random random = new Random();
        Integer indexRichtigerButton = random.nextInt(9);
        String richtigeFarbeHex = "#FFFFFF";

        Intent spielActivity = new Intent(SpielActivity.this, SpielActivity.class);
        Intent gameOverActivity = new Intent(SpielActivity.this, GameOverActivity.class);

        Integer counter = 0;

        ArrayList<String> neunFarben = colorGenerator.Farbe(255);

        Log.println(Log.DEBUG, "Neun Farben", String.valueOf(neunFarben));

        for (int i = 0; i < buttons.length; i++){
            Log.println(Log.DEBUG, "debugging alle Farben", "Farbe " + i + " " + String.valueOf(neunFarben.get(i)));
            buttons[i].setBackgroundColor(Color.parseColor(neunFarben.get(i)));
        }
        Log.println(Log.DEBUG, "richtige Farbe ID", String.valueOf(indexRichtigerButton));
        view.setBackgroundColor(Color.parseColor(neunFarben.get(indexRichtigerButton)));

        // drawble to hex

        ColorDrawable viewColor = (ColorDrawable) view.getBackground();
        int colorId = viewColor.getColor();
        String hexColor = String.format("#%06X", (0xFFFFFF & colorId));
        Log.println(Log.DEBUG, "Hintergrundfarbe als HEX", hexColor);
        Log.println(Log.DEBUG, "Buttons", neunFarben.get(0));


        for (int i = 0; i < buttons.length; i++) {
            // Log.println(Log.DEBUG, "debugging", String.valueOf(farbe));
            if (hexColor.equals(neunFarben.get(i))) {
                Log.println(Log.DEBUG, "Farbe vergleichen richtig", hexColor + " is equal to " + neunFarben.get(i));
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(spielActivity);
                    }
                });
            } else {
                // buttons[i].setBackgroundColor(Color.parseColor(neunFarben.get(i)));
                int finalI = i;
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.println(Log.DEBUG, "Farbe vergleichen falsch", hexColor + " is equal to " + neunFarben.get(finalI));
                        startActivity(gameOverActivity);
                    }
                });
// test
            }
            // buttons[i].setBackgroundColor(Color.parseColor(neunFarben.get(0)));
        }
    }
}