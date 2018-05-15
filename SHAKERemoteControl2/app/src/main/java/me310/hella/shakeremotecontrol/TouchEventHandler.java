package me310.hella.shakeremotecontrol;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

public class TouchEventHandler {

    private View view;

    public TouchEventHandler(View view){
        this.view = view;
    }

    public void triggerTouch(float x, float y){
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis() + 100;

        System.out.println("x: " + x);
        System.out.println("y: " + y);

        // List of meta states found here:     developer.android.com/reference/android/view/KeyEvent.html#getMetaState()
        int metaState = 0;
        MotionEvent touchEvent = MotionEvent.obtain(
                downTime,
                eventTime,
                MotionEvent.ACTION_UP,
                x,
                y,
                metaState
        );

        // Dispatch touch event to view
        view.dispatchTouchEvent(touchEvent);
    }
    public View getView(){return view;}
}
