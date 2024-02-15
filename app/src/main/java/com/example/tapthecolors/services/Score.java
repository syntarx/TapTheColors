package com.example.tapthecolors.services;

import android.content.Context;

public class Score {
    PreferencesData preferencesData = new PreferencesData();

    Integer overAllScore = 0;

    public void updateScore(Context context, Integer score) {
        int momentanScore = preferencesData.getInt(context, String.valueOf(score), 0);

        overAllScore += momentanScore;

        preferencesData.saveInt(context, String.valueOf(score), overAllScore);
    }

    public int getScore(Context context, Integer score) {
        return preferencesData.getInt(context, String.valueOf(score), 0);
    }

    public void resetScore(Context context, Integer score) {
        int counter = preferencesData.getInt(context, String.valueOf(score), 0);

        counter = 0;

        preferencesData.saveInt(context, String.valueOf(score), counter);
    }
}
