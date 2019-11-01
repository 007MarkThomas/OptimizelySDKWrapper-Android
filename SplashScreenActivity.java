package com.practice.androidoptimizely;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.optimizely.ab.android.sdk.OptimizelyClient;
import com.optimizely.ab.android.sdk.OptimizelyManager;
import com.optimizely.ab.android.sdk.OptimizelyStartListener;
import com.optimizely.ab.config.Variation;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class SplashScreenActivity extends AppCompatActivity {

    private OptimizelyManager optimizelyManager;
    private MyApplication myApplication;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // This could also be done via DI framework such as Dagger
        myApplication = (MyApplication) getApplication();
        optimizelyManager = myApplication.getOptimizelyManager();
    }

    @Override
    protected void onStart() {
        super.onStart();

        boolean INITIALIZE_ASYNCHRONOUSLY = false;

        if(INITIALIZE_ASYNCHRONOUSLY) {

            /** Example of using Cache datafile to initialize optimizely client, if file is not present
             in Cache it will be initialized from Raw.datafile.
             **/

            optimizelyManager.initialize(myApplication, R.raw.datafile);
            startVariation();

        } else {
            optimizelyManager.initialize(this, null, new OptimizelyStartListener() {
                @Override
                public void onStart(OptimizelyClient optimizelyClient) { startVariation();}
            });
        }
    }

    /**
     * This method will start the user activity according to provided variation
     */
    private void startVariation() {

        // Activate user and start activity based on the variation we get.
        // You can pass in any string for the user ID. In this example we just use a convenience method to generate a random one.

        String userId = myApplication.getAnonUserId();
        int userAge = myApplication.getUserAge();

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("user_age",19);

        Intent intent = null;

        String variationKey = OptimizelyWrappers.activateForABTesting(optimizelyManager,OptimizelyExperiments.userProfileExperiments,userId,attributes);
        if (variationKey != null) {
            if (variationKey.equals("variation_1")) {
                intent = new Intent(SplashScreenActivity.this, VariationAActivity.class);
                // execute code for variation_1
            } else if (variationKey.equals("variation_2")) {
                // execute code for variation_2
                intent = new Intent(SplashScreenActivity.this, VariationBActivity.class);
            }
        } else {
            // This class is called if the if they dont qualify for the experiment
            intent = new Intent(SplashScreenActivity.this, DefaultActivity.class);
        }

        startActivity(intent);
    }

}
