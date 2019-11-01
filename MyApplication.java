package com.practice.androidoptimizely;

import android.app.Application;
import android.support.annotation.NonNull;


import com.optimizely.ab.android.sdk.OptimizelyClient;
import com.optimizely.ab.android.sdk.OptimizelyManager;
import com.optimizely.ab.android.sdk.OptimizelyStartListener;

import java.util.UUID;

public class MyApplication extends Application {

    private OptimizelyManager optimizelyManager;
    public OptimizelyManager getOptimizelyManager() {
        return optimizelyManager;
    }



    // MARK: Generated my own user ID for the purpose of testing

    @NonNull
    public String getAnonUserId() {
        // this is a convenience method that creates an anonymous user id,
        // which we need to pass into the activate and track calls

        String id = UUID.randomUUID().toString();
        return id;
    }

    @NonNull
    public int getUserAge() {

        // this is a convenience method that creates a users age to be used for the audience
        // needed to decide if you eligible for the experiment

        double randomDouble = Math.random();
        randomDouble = randomDouble * 30 + 1;
        int randomInt = (int) randomDouble;
        return randomInt;
    }
    //


    @Override
    public void onCreate() {
        super.onCreate();
        OptimizelyManager.Builder builder = OptimizelyManager.builder();
        optimizelyManager =  builder.withEventDispatchInterval(60L * 10L)
                .withDatafileDownloadInterval(60L * 10L)
                .withSDKKey("RhSxRsZPxCgZxtAEcanpE9") //Please add your sdk key here!!
                .build(getApplicationContext());
    }
}
