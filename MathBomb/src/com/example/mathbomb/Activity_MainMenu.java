package com.example.mathbomb;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_MainMenu extends Activity {
    public static Date prevEasyDate, prevNormalDate, prevHardDate;
    public Button playButton, highscoreButton, howtoplayButton, settingsButton, exitButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        playButton = (Button)findViewById(R.id.playbutton);
        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Activity_MainMenu.this, Activity_NewGame.class);
                startActivity(i);
            }
        });
        highscoreButton = (Button)findViewById(R.id.highscorebutton);
        highscoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Activity_MainMenu.this, Activity_HighScore.class);
                startActivity(i);
            }
        });
        howtoplayButton	= (Button)findViewById(R.id.howtoplaybutton);
        settingsButton	= (Button)findViewById(R.id.settingsbutton);
        exitButton = (Button)findViewById(R.id.exitbutton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
