package com.example.applefrenzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener ,Runnable{
    ConstraintLayout layout;
    AnimationDrawable drawable;
    GestureDetector gestureDetector;
    private ImageView apple;
    private ImageView basket;
    TextView scoreval,missval;
    int layoutw,layouth;
    int speedy =10;
    int applexpos = 10;
    int score =0, missed =0;
    Random r;
    boolean appleXstate = false,appleYstate = false;
    boolean score_state = false, miss_state = false;

//    Rect r1 = new Rect(100,100,100,100);
//    Rect r2 = new Rect(95,95,95,95);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        layout = findViewById(R.id.myLayout);
        scoreval = findViewById(R.id.scoreval);
        missval = findViewById(R.id.missingval);
        scoreval.setText(Integer.toString(score));
        missval.setText(Integer.toString(missed));
        gestureDetector = new GestureDetector(this);
        drawable = (AnimationDrawable) layout.getBackground();
        drawable.setEnterFadeDuration(1000);
        drawable.setExitFadeDuration(1000);
        drawable.start();

         Display display = getWindowManager().getDefaultDisplay();
         Point windsize = new Point();

          display.getSize(windsize);
          layoutw = windsize.x;
          layouth = windsize.y;

          r = new Random();

        apple = findViewById(R.id.appleDrop);
        basket = findViewById(R.id.basket);



//        apple.getHitRect(r1);
//        basket.getHitRect(r2);

        basket.setOnTouchListener(this);

        basket.setMaxWidth(95);

        new Thread(this).start();




//        Toast.makeText(getApplication(),"rect size  "+r1.width(),Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStart() {
        super.onStart();

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

//        if(motionEvent1.getX()+basket.getWidth() <= layoutw )
//            basket.animate().translationXBy(motionEvent1.getX()).setDuration(1000);
//        if(basket.getX() >= layoutw)
//        {
//            basket.animate().translationXBy(-100).setDuration(1000);
//        }
//        Log.d("Test"," "+ motionEvent1.getX());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {


//            basket.animate().translationXBy(motionEvent1.getX()).setDuration(1000);
//        Log.d("Test"," "+ motionEvent1.getX());
        return true;
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        gestureDetector.onTouchEvent(motionEvent);

        if(motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            if (motionEvent.getX() + basket.getWidth() <= layoutw)
               basket.animate().translationXBy(motionEvent.getX()).setDuration(1000);
             //   basket.animate().translationX(motionEvent.getX()).setDuration(1000);

            if (basket.getX() >= layoutw) {
               basket.setX(100);
               // basket.animate().translationX(basket.getWidth()).setDuration(1000);
            }
          collision();
//            if ( apple.getY() >= basket.getY() && apple.getY() + apple.getHeight() >= basket.getY() + basket.getHeight()){
//                score++;
//                scoreval.setText(Integer.toString(score));
//                Log.d("Test","Intersects"+ apple.getWidth()+" basket width "+basket.getWidth());
//            }
        }
        return true;
    }

    @Override
    public void run() {



        while (true) {
            try {

                Thread.sleep(100);

                if (apple.getY()+apple.getHeight() <= layouth) {
                    speedy += 20;
                    apple.setY(speedy);
                    collision();
                   }
                else
                {
                    appleYstate = true;
                    speedy = 10;
                    updatex();
               }
                miss_apple();
              //  Log.d("Test", "run called " + speedy);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


    }


    public void updatex()
    {
        if(appleYstate)
        {
            applexpos = r.nextInt(layoutw-apple.getWidth());
            apple.setY(10);
            apple.setX(applexpos);
        }
        appleYstate = false;
    }

    public void collision(){

//        if ( apple.getX() >= basket.getX() && apple.getX() + apple.getWidth() <= basket.getX() + basket.getWidth()){
//             score++;
//             scoreval.setText(Integer.toString(score));
//            Log.d("Test","Intersects");
//        }

        if ( apple.getY() >= basket.getY() && apple.getY() + apple.getHeight() >= basket.getY() && apple.getX() >= basket.getX() && apple.getX() + apple.getWidth() >= basket.getX()){
            score++;
            scoreval.setText(Integer.toString(score));
            score_state= true;
            miss_state = false;
            Log.d("Test","Intersects"+ apple.getWidth()+" basket width "+basket.getWidth());
        }

    }

public void miss_apple()
{
    if(miss_state)
    {
        missed++;
        missval.setText(Integer.toString(missed));
        miss_state = false;
    }

}

}
