package com.example.applefrenzy;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    Animation titleAnimation;
    ImageView gameTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        gameTitle = findViewById(R.id.applefrenzylogo);
        titleAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        MainActivityThread mt = new MainActivityThread();
        mt.start();
    }

    @Override
    protected void onStart () {
        super.onStart();

        gameTitle.startAnimation(titleAnimation);
    }

    class MainActivityThread extends Thread {
            public void run() {
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
        }
    }
}
