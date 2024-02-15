package com.example.tapthecolors.services;

public class DemoSchwierigkeit {
    public Integer schwierigkeit(Integer counter) {

        Integer abweichung = 255;

        if (counter < 3) {
            return abweichung;
        } else if (counter < 6) {
            abweichung = 200;
            return abweichung;
        } else if (counter < 9) {
            abweichung = 150;
            return abweichung;
        } else if (counter < 12) {
            abweichung = 100;
            return abweichung;
        } else if (counter < 15) {
            abweichung = 75;
            return abweichung;
        } else if (counter < 18) {
            abweichung = 50;
            return abweichung;
        } else if (counter < 21) {
            abweichung = 40;
            return abweichung;
        } else if (counter < 24) {
            abweichung = 30;
            return abweichung;
        } else if (counter < 27) {
            abweichung = 20;
            return abweichung;
        } else if (counter < 30) {
            abweichung = 10;
            return abweichung;
        } else if (counter < 33) {
            abweichung = 5;
            return abweichung;
        } else {
            abweichung = 1;
            return abweichung;
        }
    }
}
