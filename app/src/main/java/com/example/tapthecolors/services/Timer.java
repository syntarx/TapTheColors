package com.example.tapthecolors.services;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.tapthecolors.GameOverActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Timer {
    private TextView textView;
    private Context context;
    private CountDownTimer countDownTimer;
    private static long remainingTimeMillis;

    public Timer(Context context, TextView textView) {
        this.context = context;
        this.textView = textView;
    }

    public void startCountDown() {
        if (countDownTimer != null) {
            countDownTimer.cancel(); // Stoppe den Timer, wenn er bereits läuft
        }

        Intent gameOverIntent = new Intent(context, GameOverActivity.class);

        countDownTimer = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                remainingTimeMillis = millisUntilFinished; // Aktualisiere die verbleibende Zeit
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                textView.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
            }

            public void onFinish() {
                textView.setText("00:00:00");
                context.startActivity(gameOverIntent);
            }
        }.start();
    }

    public void stopCountDown() {
        if (countDownTimer != null) {
            countDownTimer.cancel(); // Stoppe den Timer
        }
    }

    public static double getRemainingTimeMillis() {
        return remainingTimeMillis;
    }
}
