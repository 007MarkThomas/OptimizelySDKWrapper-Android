package com.practice.androidoptimizely;

import android.content.Context;
import android.os.Bundle;

import com.optimizely.ab.android.sdk.OptimizelyClient;
import com.optimizely.ab.android.sdk.OptimizelyManager;
import com.optimizely.ab.android.sdk.OptimizelyStartListener;
import com.optimizely.ab.config.Variation;

import java.util.Map;

public class OptimizelyWrappers {


    //Initialize Optimizely Client

    public static void initializeOptimizelyClient(OptimizelyManager optimizelyManager, Context context) {

        optimizelyManager.initialize(context, null, new OptimizelyStartListener() {
            @Override
            public void onStart(OptimizelyClient optimizelyClient) {
                return;
            }
        });
    }

    //Activate single experiment

    public static String activateForABTesting(OptimizelyManager optimizelyManager,String experimentKey,String userId,Map<String, Object> attributes)  {
        if(attributes.isEmpty()) {
            Variation v1 = optimizelyManager.getOptimizely().activate(experimentKey, userId, null);
            return v1.getKey();
        } else {
            Variation v1 = optimizelyManager.getOptimizely().activate(experimentKey, userId, attributes);
            return v1.getKey();
        }
    }

    //Get Feature Variables

    public static int getFeatureVariableInteger(OptimizelyManager optimizelyManager, String featureKey,String variableKey,String userId, Map<String, Object> attributes) {
        return optimizelyManager.getOptimizely().getFeatureVariableInteger(featureKey, variableKey, userId, attributes);
    }

    public static double getFeatureVariableDouble(OptimizelyManager optimizelyManager, String featureKey,String variableKey,String userId, Map<String, Object> attributes) {
        return optimizelyManager.getOptimizely().getFeatureVariableDouble(featureKey, variableKey, userId, attributes);
    }

    public static String getFeatureVariableString(OptimizelyManager optimizelyManager, String featureKey,String variableKey,String userId, Map<String, Object> attributes) {
        return optimizelyManager.getOptimizely().getFeatureVariableString(featureKey, variableKey, userId, attributes);
    }


    //Get Variation Key

    public static String getVariationKey(OptimizelyManager optimizelyManager,String experimentKey,String userId, Map<String, Object> attributes) {
       if(attributes.isEmpty()) {
           Variation variation = optimizelyManager.getOptimizely().getVariation(experimentKey, userId, null);
           return variation.getKey();
       } else {
           Variation variation = optimizelyManager.getOptimizely().getVariation(experimentKey, userId, attributes);
           return variation.getKey();
       }
    }

    //Is feature enabled

    public static Boolean isFeatureEnabled(OptimizelyManager optimizelyManager, String featureID, String userID) {
        return optimizelyManager.getOptimizely().isFeatureEnabled(featureID,userID);
    }

    //Track Event

    public static void trackEvent(OptimizelyManager optimizelyManager, String eventKey, String userId, Map<String, Object> attributes)  {
        if(attributes.isEmpty()) {
            optimizelyManager.getOptimizely().track(eventKey,userId);
        } else {
            optimizelyManager.getOptimizely().track(eventKey, userId, attributes);
        }
    }





}
