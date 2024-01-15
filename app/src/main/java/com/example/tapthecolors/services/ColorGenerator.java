package com.example.tapthecolors.services;

import java.util.Random;

public class ColorGenerator {
    public String Farbe(){
        Random random = new Random();

        int nextInt = random.nextInt(0xffffff + 1);

        String colorCode = String.format("#%06x", nextInt);

        return colorCode;
    }
}


// https://stackoverflow.com/questions/35459454/java-random-color-string
