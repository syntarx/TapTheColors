package com.example.tapthecolors.services;

import android.content.Context;

public class Score {
    PreferencesData preferencesData = new PreferencesData();
    Integer overAllScore = 0;

    public void updateScore(Context context, Integer score) {
        // Zuerst holen wir uns den vorherigen Gesamtpunktestand aus den Einstellungen
        int overallScore = preferencesData.getInt(context, "overall_score", 0);

        /*// Berechnen der Differenz zwischen dem aktuellen Score und dem vorherigen Gesamtpunktestand
        Integer scoreDifference = score - previousOverallScore;*/

        // Aktualisieren des Gesamtpunktestands
        overAllScore += score;

        // Speichern des aktuellen Gesamtpunktestands
        preferencesData.saveInt(context, "overall_score", overAllScore);

        // Speichern der Differenz zwischen den Runden
        preferencesData.saveInt(context, "score_difference", score);
    }

    public int getOverallScore(Context context) {
        // Rückgabe des aktuellen Gesamtpunktestands aus den Einstellungen
        return preferencesData.getInt(context, "overall_score", 0);
    }

    public int getScoreDifference(Context context) {
        // Rückgabe der Differenz zwischen den Runden aus den Einstellungen
        return preferencesData.getInt(context, "score_difference", 0);
    }

    public void resetScore(Context context) {
        // Zurücksetzen des Gesamtpunktestands auf 0
        overAllScore = 0;
        preferencesData.saveInt(context, "overall_score", 0);

        // Zurücksetzen der Differenz zwischen den Runden auf 0
        preferencesData.saveInt(context, "score_difference", 0);
    }
}

