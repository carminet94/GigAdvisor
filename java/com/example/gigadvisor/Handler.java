package com.example.gigadvisor;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Handler {

    public static Handler mIstance;
    private RequestQueue requestQueue;
    private static Context context;

    private Handler (Context c){
        context = c;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue== null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        }
        return requestQueue;
    }

    public static synchronized Handler getInstance(Context c){
        if(mIstance==null){
            mIstance = new Handler(c);
        }
        return  mIstance;
    }

    public <T> void addToRequestQue(Request<T> request){
        requestQueue.add(request);
    }
}
