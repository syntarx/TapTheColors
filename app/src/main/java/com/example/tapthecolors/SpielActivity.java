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

import java.lang.reflect.Array;
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

        ColorGenerator colorGenerator = new ColorGenerator();

        Random random = new Random();
        Integer indexRichtigeFarbe = random.nextInt(9);
        String richtigeFarbeHex = "#FFFFFF";

        for (int i = 0; i < buttons.length; i++) {
            String farbe = colorGenerator.Farbe();
            Log.println(Log.DEBUG,"debugging", String.valueOf(farbe));
            if (i == indexRichtigeFarbe) {
                richtigeFarbeHex = farbe;
            }
            buttons[i].setBackgroundColor(Color.parseColor(farbe));
            buttons[i].;
        }

        view.setBackgroundColor(Color.parseColor(richtigeFarbeHex));

        ActionListener actionListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent) {
                JButton button = (JButton)actionEvent.getSource();
                String label = button.getLabel(); //Deprecated

                String label2 = button.getText();
            }
        };

        Intent gameOverActivity = new Intent(SpielActivity.this, GameOverActivity.class);

/*        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(gameOverActivity);
            }
        });*/
    }
}