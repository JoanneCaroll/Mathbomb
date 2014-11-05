package com.example.mathbomb;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
public class NewGame extends Activity {
  ListView list;
  public static final String choice = "choice";
  String[] web = {
	  "Easy (1-5)",
      "Normal (1-20)",
      "Hard (10-20)",
  } ;
  
  Integer[] imageId = {
      R.drawable.cat2,
      R.drawable.cat3,
      R.drawable.cat4
  };
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.list_main);
    CustomList adapter = new
        CustomList(NewGame.this, web, imageId);
    	list=(ListView)findViewById(R.id.ListView01);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                		Intent i = new Intent(NewGame.this, StartGame.class);
                    	i.putExtra(choice, position);
                		startActivity(i);

                	
                }
            });
  }
}