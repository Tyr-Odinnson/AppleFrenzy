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
    private Button storyButton;
    private Button exitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        howButton = findViewById(R.id.howButton);
        authorButton = findViewById(R.id.authorButton);
        scoreButton = findViewById(R.id.scoreButton);
        playButton = findViewById(R.id.playButton);
        storyButton = findViewById(R.id.storyButton);
        exitButton = findViewById(R.id.exitButton);

        authorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent authorIntent = new Intent(MainActivity.this, AuthorActivity.class);
                startActivity(authorIntent);
            }
        });
        howButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent howtoIntent = new Intent(MainActivity.this, HowToActivity.class);
                startActivity(howtoIntent);
            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(playIntent);
            }
        });
        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoreIntent = new Intent(MainActivity.this, ScoreActivity.class);
                startActivity(scoreIntent);
            }
        });
        storyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent storyIntent = new Intent(MainActivity.this, StoryActivity.class);
                startActivity(storyIntent);
            }
        });
        exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                moveTaskToBack(true);
            }
        });

    }
}
