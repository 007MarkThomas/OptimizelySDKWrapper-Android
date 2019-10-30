package com.practice.androidoptimizely;

import android.content.Context;
import android.os.Bundle;

import com.optimizely.ab.android.sdk.OptimizelyClient;
import com.optimizely.ab.android.sdk.OptimizelyManager;
import com.optimizely.ab.android.sdk.OptimizelyStartListener;
import com.optimizely.ab.config.Variation;

import java.util.Map;

public class OptimizelyWrappers {


    private OptimizelyManager optimizelyManager;


    public OptimizelyWrappers(OptimizelyManager optimizelyManager){
        this.optimizelyManager = optimizelyManager;
    }

    public static String activateExperiment(OptimizelyManager optimizelyManager,String experimentKey,String userId,Map<String, Object> attributes)  {
        if(attributes.isEmpty()) {
            Variation v1 = optimizelyManager.getOptimizely().activate(experimentKey, userId, null);
            return v1.getKey();
        } else {
            Variation v1 = optimizelyManager.getOptimizely().activate(experimentKey, userId, attributes);
            return v1.getKey();
        }
    }

    public static String getVariationKey(OptimizelyManager optimizelyManager,String experimentKey,String userId, Map<String, Object> attributes) {
       if(attributes.isEmpty()) {
           Variation variation = optimizelyManager.getOptimizely().getVariation(experimentKey, userId, null);
           return variation.getKey();
       } else {
           Variation variation = optimizelyManager.getOptimizely().getVariation(experimentKey, userId, attributes);
           return variation.getKey();
       }
    }

    public static Boolean isFeatureEnabled(OptimizelyManager optimizelyManager, String featureID, String userID) {
        return optimizelyManager.getOptimizely().isFeatureEnabled(featureID,userID);
    }

    public static void trackEvent(OptimizelyManager optimizelyManager, String eventKey, String userId, Map<String, Object> attributes)  {
        if(attributes.isEmpty()) {
            optimizelyManager.getOptimizely().track(eventKey,userId);
        } else {
            optimizelyManager.getOptimizely().track(eventKey, userId, attributes);
        }
    }

    public static void initializeOptimizelyClient(OptimizelyManager optimizelyManager, Context context) {
        optimizelyManager.initialize(context, null, new OptimizelyStartListener() {
            @Override
            public void onStart(OptimizelyClient optimizelyClient) {
                return;
            }
        });
    }



}
