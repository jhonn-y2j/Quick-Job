package com.developers.quickjob.quick_job.restapi;

/**
 * Created by jhonn_aj on 27/11/2016.
 */

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private static Context context;

    private VolleySingleton(Context context){
        VolleySingleton.context=context;

    }

    public static synchronized VolleySingleton getInstance(Context context){
        if (volleySingleton==null){
            volleySingleton= new VolleySingleton(context);
        }
        return volleySingleton;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue==null){
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public void addRequestQueue(Request request){
        getRequestQueue().add(request);
    }

}

