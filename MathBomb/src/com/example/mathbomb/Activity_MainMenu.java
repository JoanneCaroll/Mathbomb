package com.example.mathbomb;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_MainMenu extends Activity {
	public static Date prevEasyDate, prevNormalDate, prevHardDate;
	public Button[] menu = new Button[5];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainmenu);

		menu[0] = (Button)findViewById(R.id.playbutton);
		menu[0].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(Activity_MainMenu.this, Activity_NewGame.class);
				startActivity(i);
			}
		});
		menu[1] = (Button)findViewById(R.id.highscorebutton);
		menu[1].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(Activity_MainMenu.this, Activity_HighScore.class);
				startActivity(i);
			}
		});
		menu[2]	= (Button)findViewById(R.id.howtoplaybutton);
		menu[3]	= (Button)findViewById(R.id.settingsbutton);
		menu[4] = (Button)findViewById(R.id.exitbutton);
		menu[4].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
