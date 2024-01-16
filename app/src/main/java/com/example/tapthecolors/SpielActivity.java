package com.example.tapthecolors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tapthecolors.services.ColorGenerator;

import java.lang.reflect.Array;
import java.util.Random;

public class SpielActivity extends AppCompatActivity {

    Button[] buttons = new Button[9];

    private NotificationManager notificationManager;
    private static final String CHANNEL_ID = "defaultChannel";
    private static final String CHANNEL_NAME = "Default Channel";

    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setContentTitle("Game Over!")
                .setContentText("Du hast verloren, hahhahahahahahaha!")
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        notificationManager.notify(1, builder.build());
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel);

        this.notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }


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

        Intent spielActivity = new Intent(SpielActivity.this, SpielActivity.class);
        Intent gameOverActivity = new Intent(SpielActivity.this, GameOverActivity.class);

        for (int i = 0; i < buttons.length; i++) {
            String farbe = colorGenerator.Farbe();
            Log.println(Log.DEBUG, "debugging", String.valueOf(farbe));
            if (i == indexRichtigeFarbe) {
                richtigeFarbeHex = farbe;
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vibrate();
                        startActivity(spielActivity);
                    }
                });
            } else {
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendNotification();
                        startActivity(gameOverActivity);
                    }
                });

            }
            buttons[i].setBackgroundColor(Color.parseColor(farbe));
        }
        view.setBackgroundColor(Color.parseColor(richtigeFarbeHex));
    }
}