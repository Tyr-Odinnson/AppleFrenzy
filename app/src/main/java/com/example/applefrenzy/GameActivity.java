package com.example.applefrenzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    ConstraintLayout layout;
    AnimationDrawable drawable;
    GestureDetector gestureDetector;
    private ImageView apple;
    private ImageView basket;
    float deltaX;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        layout = findViewById(R.id.myLayout);
        gestureDetector = new GestureDetector(this);
        drawable = (AnimationDrawable) layout.getBackground();
        drawable.setEnterFadeDuration(1000);
        drawable.setExitFadeDuration(1000);
        drawable.start();

        apple = findViewById(R.id.appleDrop);
        basket = findViewById(R.id.basket);
        basket.setOnTouchListener(this);



    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if (motionEvent1.getX() > 0 && motionEvent1.getX() <= layout.getWidth())
        basket.animate().translationXBy(motionEvent1.getX()).setDuration(1000);
        Log.d("Testing", "Scroll movement");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.d("testing", "onTouch");
        gestureDetector.onTouchEvent(motionEvent);

//        if(motionEvent.getAction() == MotionEvent.ACTION_MOVE)
//        {
//            basket.animate().translationXBy(motionEvent.getX()).setDuration(100);
//        }
        return true;
    }
}
