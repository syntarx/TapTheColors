package com.example.tapthecolors.services;

public class SchwierigkeitsGrad {
    public Integer Schwierigkeit(Integer counter) {

        Integer abweichung = 255;

        if (counter < 10) {
            return abweichung;
        } else if (counter < 15) {
            abweichung = 200;
            return abweichung;
        } else if (counter < 20) {
            abweichung = 150;
            return abweichung;
        } else if (counter < 25) {
            abweichung = 100;
            return abweichung;
        } else if (counter < 30) {
            abweichung = 75;
            return abweichung;
        } else if (counter < 35) {
            abweichung = 50;
            return abweichung;
        }  else if (counter < 40) {
            abweichung = 40;
            return abweichung;
        } else if (counter < 45) {
            abweichung = 30;
            return abweichung;
        } else if (counter < 50) {
            abweichung = 20;
            return abweichung;
        } else if (counter < 55) {
            abweichung = 10;
            return abweichung;
        } else if (counter < 60) {
            abweichung = 5;
            return abweichung;
        } else {
            abweichung = 1;
            return abweichung;
        }
    }
}
