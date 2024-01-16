package com.example.tapthecolors.services;

import android.util.Log;

import java.util.Random;


public class schwererColorGenerator {
    public int[] Farbe(Integer abweichung){

        Random random = new Random();

        int[] basisRGB = new int[3];
        for (int i = 0; i < 3; i++) {
            basisRGB[i] = random.nextInt(255);
            return basisRGB;
        }

        int[] newRGB = new int[3];
        for (int j = 0; j < basisRGB.length; j++) {
            newRGB[j] = basisRGB[j] % abweichung;
            return newRGB;
        }

        Log.println(Log.DEBUG, "debuggingService", String.valueOf(basisRGB));
        Log.println(Log.DEBUG, "debuggingServiceWithAddition", String.valueOf(newRGB));

        // String colorCode = String.format("#%06x", nextInt);

        return newRGB;
    }
}


// https://stackoverflow.com/questions/35459454/java-random-color-string
