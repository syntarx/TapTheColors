package com.example.tapthecolors;

import static com.example.tapthecolors.services.PreferencesData.saveInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tapthecolors.services.DemoSchwierigkeit;
import com.example.tapthecolors.services.PreferencesData;
import com.example.tapthecolors.services.SchwererColorGenerator;
import com.example.tapthecolors.services.SchwierigkeitsGrad;
import com.example.tapthecolors.services.Timer;

import java.util.ArrayList;
import java.util.Random;


public class SpielActivity extends AppCompatActivity {

    Button[] buttons = new Button[9];
    PreferencesData preferencesData = new PreferencesData();

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

    public void incrementCounter(Context context, String counterKey) {
        int counter = preferencesData.getInt(context, counterKey, 0);

        counter++;

        preferencesData.saveInt(context, counterKey, counter);
    }

    public int getCounter(Context context, String counterKey) {
        return preferencesData.getInt(context, counterKey, 0);
    }

    public void resetCounter(Context context, String counterKey) {
        int counter = preferencesData.getInt(context, counterKey, 0);

        counter = 0;

        preferencesData.saveInt(context, counterKey, counter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel);

        Timer timer = new Timer(this, findViewById(R.id.textView6));
        timer.startCountDown();


        this.notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        // loop
        buttons[0] = findViewById(R.id.button);
        buttons[1] = findViewById(R.id.button1);
        buttons[2] = findViewById(R.id.button2);
        buttons[3] = findViewById(R.id.button3);
        buttons[4] = findViewById(R.id.button4);
        buttons[5] = findViewById(R.id.button5);
        buttons[6] = findViewById(R.id.button6);
        buttons[7] = findViewById(R.id.button7);
        buttons[8] = findViewById(R.id.button8);

        ConstraintLayout view = findViewById(R.id.activity_spiel);

        SchwererColorGenerator colorGenerator = new SchwererColorGenerator();

        // Zwei verschiedene Schwierigkeitsgrad, Demo ist selbsterklärend nur fpr die Demo
        SchwierigkeitsGrad schwierigkeitsGrad = new SchwierigkeitsGrad();
        DemoSchwierigkeit demoSchwierigkeit = new DemoSchwierigkeit();

        Random random = new Random();
        Integer indexRichtigerButton = random.nextInt(9);

        Intent spielActivity = new Intent(SpielActivity.this, SpielActivity.class);
        Intent gameOverActivity = new Intent(SpielActivity.this, GameOverActivity.class);

        String counter = "my_counter";
        Integer anzahlRunden = getCounter(this, counter);

        Integer abweichung =demoSchwierigkeit.Schwierigkeit(anzahlRunden);

        Log.println(Log.DEBUG, "abweichung", String.valueOf(abweichung));

        ArrayList<String> neunFarben = colorGenerator.Farbe(abweichung);

        Log.println(Log.DEBUG, "Neun Farben", String.valueOf(neunFarben));

        for (int i = 0; i < buttons.length; i++){
            Log.println(Log.DEBUG, "debugging alle Farben", "Farbe " + i + ": " + String.valueOf(neunFarben.get(i)));
            buttons[i].setBackgroundColor(Color.parseColor(neunFarben.get(i)));
        }
        Log.println(Log.DEBUG, "richtige Farbe ID", "ID von richtiger Farbe: " + String.valueOf(indexRichtigerButton));
        view.setBackgroundColor(Color.parseColor(neunFarben.get(indexRichtigerButton)));

        ColorDrawable viewColor = (ColorDrawable) view.getBackground();
        int colorId = viewColor.getColor();
        String hexColor = String.format("#%06X", (0xFFFFFF & colorId));
        Log.println(Log.DEBUG, "Hintergrundfarbe", "richtige Farbe: " + hexColor);

        for (int i = 0; i < buttons.length; i++) {
            if (hexColor.equals(neunFarben.get(i))) {
                Log.println(Log.DEBUG, "Farbe vergleichen", hexColor + " is equal to " + neunFarben.get(i));
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vibrate();
                        String counter = "my_counter";
                        incrementCounter(v.getContext(), counter);
                        startActivity(spielActivity);
                        int currentCounterValue = getCounter(v.getContext(), counter);
                        Log.println(Log.DEBUG, "counter", String.valueOf(currentCounterValue));
                    }
                });
            } else {
                int finalI = i;
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.println(Log.DEBUG, "Farbe vergleichen falsch", hexColor + " is not equal to " + neunFarben.get(finalI));
                        sendNotification();
                        String counter = "my_counter";
                        resetCounter(v.getContext(), counter);
                        startActivity(gameOverActivity);
                    }
                });
            }
        }
        // Log.println(Log.DEBUG, "counter", getCounter(this, currentCounterValue));
    }
}