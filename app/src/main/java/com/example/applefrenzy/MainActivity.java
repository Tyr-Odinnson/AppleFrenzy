package com.example.applefrenzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private Button howButton;
    private Button authorButton;
    private Button scoreButton;
    private Button playButton;
    private Button exitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        howButton = findViewById(R.id.howButton);
        authorButton = findViewById(R.id.authorButton);
        scoreButton = findViewById(R.id.scoreButton);
        playButton = findViewById(R.id.playButton);
        exitButton = findViewById(R.id.exitButton);

        authorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comedyIntent = new Intent(MainActivity.this, AuthorActivity.class);
                startActivity(comedyIntent);
            }
        });
        howButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comedyIntent = new Intent(MainActivity.this, HowToActivity.class);
                startActivity(comedyIntent);
            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comedyIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(comedyIntent);
            }
        });
//        comedyBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent comedyIntent = new Intent(MainActivity.this, ComedyActivity.class);
//                startActivity(comedyIntent);
//            }
//        });
//        comedyBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent comedyIntent = new Intent(MainActivity.this, ComedyActivity.class);
//                startActivity(comedyIntent);
//            }
//        });
    }
}
