package com.example.gigadvisor.data.adapter;

import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.gigadvisor.MainActivity;

public class DetectorSwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static int MIN_SWIPE_DISTANCE_X = 100;
    private static int MIN_SWIPE_DISTANCE_Y = 100;

    private static int MAX_SWIPE_DISTANCE_X = 1000;
    private static int MAX_SWIPE_DISTANCE_Y = 1000;

    private MainActivity activity= null;

    public MainActivity getActivity(){
        return activity;
    }

    public void setActivity(MainActivity activity){
        this.activity = activity;
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
        float deltaX = e1.getX() - e2.getX();
        float deltaY = e1.getY() - e2.getY();

        float deltaXAbs = Math.abs(deltaX);
        float deltaYAbs = Math.abs(deltaY);

        /*if(deltaXAbs >= MIN_SWIPE_DISTANCE_X && deltaXAbs<= MAX_SWIPE_DISTANCE_X){
            if(deltaX>0){
                this.activity.displayMessage("Swipe Left");
            }else{
                this.activity.displayMessage("Swipe Right");
            }
        }

        if(deltaYAbs >= MIN_SWIPE_DISTANCE_Y && deltaYAbs<= MAX_SWIPE_DISTANCE_Y){
            if(deltaY>0){
                this.activity.displayMessage("Swipe Up");
            }else{
                this.activity.displayMessage("Swipe Bottom");
            }
        }*/
        return true;
    }

    public boolean onSingleTapConfirmed(MotionEvent e){
        //this.activity.displayMessage("Single Tap");
        return true;
    }

    public boolean onDoubleTap(MotionEvent event){
        //this.activity.displayMessage("Double Tap");
        return true;
    }
}
