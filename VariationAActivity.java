package com.practice.androidoptimizely;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


import com.optimizely.ab.android.sdk.OptimizelyManager;

import java.util.HashMap;
import java.util.Map;

public class VariationAActivity extends AppCompatActivity {

    private MyApplication myApplication;
    private OptimizelyManager optimizelyManager;



    @Override
    public void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_variation_a);

        myApplication = (MyApplication) getApplication();
        optimizelyManager = myApplication.getOptimizelyManager();
    }

    public void featureButtonPressed(View v) {



        //Testing re initializing the optimizely client so I changed the audience for this experiment

        OptimizelyWrappers.initializeOptimizelyClient(optimizelyManager,this);
        check();


        Boolean enabled = OptimizelyWrappers.isFeatureEnabled(optimizelyManager, OptimizelyFeatures.NewScreenFeature,myApplication.getAnonUserId());

        if(enabled) {
            System.out.print("Feature is Enabled!!");
        }
    }

    // Checking if datafile has correctly changed

    public void check() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("user_age",19);
        System.out.println(OptimizelyWrappers.activateForABTesting(optimizelyManager,"user_profile_experiment",myApplication.getAnonUserId(),attributes));
    }




}
