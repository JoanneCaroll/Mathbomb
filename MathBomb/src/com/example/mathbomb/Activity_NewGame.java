package com.example.mathbomb;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
public class Activity_NewGame extends Activity {

	ListView listcategory;

	public static final String choice = "choice";
	String[] category = {
			"Easy (1-5)",
			"Normal (1-20)",
			"Hard (10-20)",
	};
	Integer[] image = {
			R.drawable.cat1,
			R.drawable.cat2,
			R.drawable.cat3
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listcategory);
		ListCategory adapter = new
				ListCategory(Activity_NewGame.this, category, image);
		listcategory=(ListView)findViewById(R.id.list_category);
		listcategory.setAdapter(adapter);
		listcategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i = new Intent(Activity_NewGame.this, Activity_StartGame.class);
				i.putExtra(choice, position);
				startActivity(i);
			}
		});
	}
}