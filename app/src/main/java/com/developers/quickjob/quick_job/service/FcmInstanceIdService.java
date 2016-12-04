package com.developers.quickjob.quick_job.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by jhonn_aj on 30/11/2016.
 */

public class FcmInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        String recent_token= FirebaseInstanceId.getInstance().getToken();
        Log.d(FcmInstanceIdService.class.getName(),recent_token);
        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("token",recent_token);
        editor.commit();
    }
}
