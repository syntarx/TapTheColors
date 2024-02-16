package com.example.tapthecolors;

import static java.lang.Math.round;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tapthecolors.services.Counter;
import com.example.tapthecolors.services.DemoSchwierigkeit;
import com.example.tapthecolors.services.SchwererColorGenerator;
import com.example.tapthecolors.services.SchwierigkeitsGrad;
import com.example.tapthecolors.services.Score;
import com.example.tapthecolors.services.Timer;

import java.util.ArrayList;
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

        Timer timer = new Timer(this, findViewById(R.id.textView6));
        timer.startCountDown();
        Double restZeit = Timer.getRemainingTimeMillis();

        this.notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }



        Score scoreManager = new Score();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Integer restZeitUebergeben = Math.toIntExact(round(extras.getDouble("restZeit", 12))); // ist immer default value
            scoreManager.updateScore(this, restZeitUebergeben);

            Log.println(Log.DEBUG, "extras", String.valueOf(extras));
            Log.println(Log.DEBUG, "restZeitUebergeben", String.valueOf(restZeitUebergeben));
        }

        TextView overallScoreField = findViewById(R.id.textView);
        TextView currentScoreField = findViewById(R.id.textView2);

        String overallScore = String.valueOf(scoreManager.getOverallScore(this));
        String scoreDifference = String.valueOf(scoreManager.getScoreDifference(this));

        overallScoreField.setText(overallScore);
        currentScoreField.setText(scoreDifference);



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

        Counter zaehler = new Counter();

        SchwererColorGenerator colorGenerator = new SchwererColorGenerator();

        // Zwei verschiedene Schwierigkeitsgrad, Demo ist selbsterklärend nur für die Demo
        SchwierigkeitsGrad schwierigkeitsGrad = new SchwierigkeitsGrad();
        DemoSchwierigkeit demoSchwierigkeit = new DemoSchwierigkeit();

        Random random = new Random();
        Integer indexRichtigerButton = random.nextInt(9);

        Intent spielActivity = new Intent(SpielActivity.this, SpielActivity.class);
        Intent gameOverActivity = new Intent(SpielActivity.this, GameOverActivity.class);

        String counter = "my_counter";
        Integer anzahlRunden = zaehler.getCounter(this, counter);

        Integer abweichung = demoSchwierigkeit.schwierigkeit(anzahlRunden);

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
                        zaehler.incrementCounter(v.getContext(), counter);
                        spielActivity.putExtra("restZeit", restZeit);
                        startActivity(spielActivity);
                        int currentCounterValue = zaehler.getCounter(v.getContext(), counter);;
                        timer.stopCountDown();

                        Log.println(Log.DEBUG, "counter", String.valueOf(currentCounterValue));
                    }
                });
            } else {
                int finalI = i;
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendNotification();
                        String counter = "my_counter";
                        zaehler.resetCounter(v.getContext(), counter);
                        startActivity(gameOverActivity);
                        timer.stopCountDown();
                        scoreManager.resetScore(v.getContext());

                        Log.println(Log.DEBUG, "Farbe vergleichen falsch", hexColor + " is not equal to " + neunFarben.get(finalI));
                    }
                });
            }
        }
        // Log.println(Log.DEBUG, "counter", getCounter(this, currentCounterValue));
    }
}