package com.example.kaimou.cashmoney;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by redacted on 12/5/15 at 7:39 PM.
 */
public class App extends Application {
    @Override public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            // release debugging
        }
    }
}
