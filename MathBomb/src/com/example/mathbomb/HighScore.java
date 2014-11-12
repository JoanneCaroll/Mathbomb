package com.example.mathbomb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
public class HighScore extends Activity {
  ListView list;
  public static final String choice = "choice";
  String[] score = {
	  "1",
      "2",
      "3",
  } ;
  
  String[] date = {
		  "date1",
	      "date2",
	      "date3",
  };
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.list_main);
    CustomListHighScore adapter = new
    		CustomListHighScore(HighScore.this, score, date);
    	list=(ListView)findViewById(R.id.ListView01);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                		Intent i = new Intent(HighScore.this, StartGame.class);
                    	i.putExtra(choice, position);
                		startActivity(i);
 
                	
                }
            });
  }
}