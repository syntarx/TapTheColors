package com.example.tapthecolors.services;

import android.content.Context;


public class Counter {

    PreferencesData preferencesData = new PreferencesData();

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
}
