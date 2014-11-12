package com.example.mathbomb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends Activity{

	public Button[] menu = new Button[5];
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		menu[0] = (Button)findViewById(R.id.playbutton);
		menu[0].setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(MainMenu.this, NewGame.class);
				startActivity(i);
			}
		});
		menu[1] = (Button)findViewById(R.id.highscorebutton);
		menu[2]	= (Button)findViewById(R.id.howtoplaybutton);
		menu[3]	= (Button)findViewById(R.id.settingsbutton);
		menu[4] = (Button)findViewById(R.id.exitbutton);
	}
	
	
}