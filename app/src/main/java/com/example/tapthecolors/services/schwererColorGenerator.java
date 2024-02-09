package com.example.tapthecolors.services;

import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;



public class schwererColorGenerator {
    public ArrayList<String> Farbe(Integer abweichung){

        Random random = new Random();

        ArrayList<String> neunHexWerte = new ArrayList<String>();

        // hier wird die basis Farbe in RGB generiert und unter dem for-loop wird sie in hex umgewandelt und dann der ArrayList zugefügt
        int[] basisRGB = new int[3];
        for (int i = 0; i < 3; i++) {
            basisRGB[i] = random.nextInt(255);
        }
        String basisHex = String.format("#%02X%02X%02X", basisRGB[0], basisRGB[1], basisRGB[2]);
        neunHexWerte.add(basisHex);


        for (int j = 1; j <= 8; j++) {
            Integer R = (random.nextInt(abweichung) +  basisRGB[0]) % 255;
            Integer G = (random.nextInt(abweichung) +  basisRGB[1]) % 255;
            Integer B = (random.nextInt(abweichung) +  basisRGB[2]) % 255;
            neunHexWerte.add(String.format("#%02X%02X%02X", R, G, B));
        }


/*        // Hier werden die 7 Farben die zwischen basis und new liegen generiert. unter dem for-loop wird sie in hex umgewandelt und dann der ArrayList zugefügt
        Integer minimalAbweichung = random.nextInt(abweichung/7);
        for (int k = 0; k < 7; k++) {
            int[] rgb = new int[3];
            for (int j = 0; j < basisRGB.length; j++) {
                rgb[j] = basisRGB[j] + minimalAbweichung;
            }
            String rgbHex = String.format("#%02x%02x%02x", rgb[0], rgb[1], rgb[2]);
            neunHexWerte.add(rgbHex);
        }



        // hier wird die neue Farbe aus der basis Farbe und der Abweichung generiert und unter dem for-loop wird diese in hex umgewandelt und dann der ArrayList zugefügt
        int[] newRGB = new int[3];
        for (int j = 0; j < basisRGB.length; j++) {
            newRGB[j] = basisRGB[j] % abweichung;
        }
        String newHex = String.format("#%02x%02x%02x", newRGB[0], newRGB[1], newRGB[2]);
        neunHexWerte.add(newHex);*/

        Log.println(Log.DEBUG, "debugging", String.valueOf(neunHexWerte));

        return neunHexWerte;
    }
}


// https://stackoverflow.com/questions/35459454/java-random-color-string
// https://stackoverflow.com/questions/3607858/convert-a-rgb-color-value-to-a-hexadecimal-string
