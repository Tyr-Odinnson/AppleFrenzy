package com.example.applefrenzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener{
    RelativeLayout relativeLayout;
    ConstraintLayout layout;
    AnimationDrawable drawable;

    public static final int swipeThreshold = 100;
    public static final int swipeVelocityThreshold = 500;
    GestureDetector gestureDetector;
    float GestureStartX, GestureEndX, GestureStartY, GestureEndY, XMovement,YMovement;
    int gestureType;

    private TextView scoreLabel;
    private TextView missingLabel;

    private ImageView imgApple;
    private ImageView imgGround;
    private ImageView imgBasket;
    private ImageView pauseButton;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;

    int applesCounter =0;
    int missedCounter =0;
    boolean applesdropped = false;
    int applexpos = 10;
    Random r;
    boolean appleXstate = false,appleYstate = false;
    boolean score_state = false, miss_state = false;

    Rect apple = new Rect();
    Rect ground = new Rect();
    Rect rectBasket = new Rect();

    private float appleX;
    private float appleY;
    private float groundX;
    private float groundY;
    private float basketX;
    private float basketY;
    int score = 0;
    int missed = 0;

    private int layoutWidth;
    private int layoutHeight;

    private Handler handler = new Handler();
    private Timer timer = new Timer();
  //  private SoundPlayer sound;
    

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

       // sound = new SoundPlayer(this);
        imgApple = findViewById(R.id.appleDrop);
        imgGround = findViewById(R.id.ground);
        imgBasket = findViewById(R.id.basket);
        scoreLabel = (TextView) findViewById(R.id.scoretxt);
        missingLabel = (TextView) findViewById(R.id.missingapple);
        gestureDetector = new GestureDetector(this);
        r = new Random();

//        pauseButton = findViewById(R.id.pause);
//        relativeLayout = (RelativeLayout) findViewById(R.id.pause_menu);
//        pauseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
//                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.pause_menu,null);
//                popupWindow = new PopupWindow(container, 350, 350, true);
//                popupWindow.showAtLocation(relativeLayout,Gravity.NO_GRAVITY,500, 500);
//
//                container.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View view, MotionEvent motionEvent) {
//                        popupWindow.dismiss();
//                        return true;
//                    }
//                });
//            }
//        });

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        layoutWidth = size.x;
        layoutHeight = size.y;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        change_apple_pos();
                        collision();
                        changePos();
                        speed();
                    }
                });
            }
        }, 0, 10);

        scoreLabel.setText("Score : 0");
        missingLabel.setText("Missed : 0");
    }

    public void collision () {
        imgApple.getHitRect(apple);
        imgGround.getHitRect(ground);
        imgBasket.getHitRect(rectBasket);

        if (Rect.intersects(apple,rectBasket)){
            appleX = (float)Math.floor(Math.random() * (layoutWidth -  imgApple.getWidth()));
            appleY = -100.0f;
            imgApple.setX(appleX);
            imgApple.setY(appleY);
            score+=27;
            scoreLabel.setText("Score : " + score);
            //    sound.playHitSound();
        }

        if (Rect.intersects(apple,ground)){
            appleX = (float)Math.floor(Math.random() * (layoutHeight -  imgGround.getWidth()));
            appleY = -100.0f;
            imgApple.setX(groundX);
            imgApple.setY(groundY);
            //    sound.playHitSound();

        }
    }

    public void changePos() {
        appleY += 10;
        if (imgApple.getY() >= layoutHeight -100) {
            applexpos = r.nextInt(layoutWidth - imgApple.getWidth());
            appleY = -100;
            applesCounter++;
        }
        imgApple.setX(appleX);
        imgApple.setY(appleY);


    }


    public void change_apple_pos() {
        imgApple.getHitRect(apple);
        imgGround.getHitRect(ground);
        imgBasket.getHitRect(rectBasket);
        if (Rect.intersects(apple, ground)) {
            appleX = (float)r.nextInt(layoutWidth - imgApple.getWidth());
            appleY = -100.0f;
            imgApple.setX(appleX);
            imgApple.setY(appleY);
            missed+=1;
            missingLabel.setText("Missed : " + missed);
            Log.d(" change apple position","change apple position");
        }
    }

    public void speed(){
        basketX = imgBasket.getX();
        switch (gestureType){
            case 1:

                if (basketX <= layoutWidth - imgBasket.getWidth()) {
                    basketX = basketX +10;
                    imgBasket.setX(basketX);
                }
                else
                {
                    appleYstate = true;
                }


                break;

            case 2:
                if (basketX >=0) {
                    basketX = basketX -10;
                    imgBasket.setX(basketX);
                }


                break;
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
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
//                if(motionEvent1.getX() <= layout.getWidth() )
//            imgBasket.animate().translationXBy(motionEvent1.getX()).setDuration(1000);
//        Log.d("Test"," "+ motionEvent1.getX());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        GestureStartX = e1.getX();
        GestureEndX = e2.getX();
        GestureStartY = e1.getY();
        GestureEndY = e2.getY();
        XMovement = GestureEndX - GestureStartX;
        YMovement = GestureEndY - GestureStartY;

        if (Math.abs(XMovement) > Math.abs(YMovement)){
            if (Math.abs(XMovement) > swipeThreshold && Math.abs(velocityX) > swipeVelocityThreshold) {
                if (XMovement > 0){
                    Log.d("Testing", "Swipe Right");
                    gestureType = 1;
                }else {
                    Log.d("Testing", "Swipe Left");
                    gestureType = 2;
                }

            }

        }
        return true;
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
//         Log.d("testing", "onTouch");
//        gestureDetector.onTouchEvent(motionEvent);
//
//        if(motionEvent.getAction() == MotionEvent.ACTION_MOVE)
//        {
//
//            imgBasket.animate().translationXBy(motionEvent.getX()).setDuration(100);
//
//        }
        return true;
    }
}
