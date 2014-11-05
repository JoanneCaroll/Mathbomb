package com.example.mathbomb;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StartGame extends Activity {

	
	public TextView int1, int2, result, operator, score;
	public int rand1, opt, rand2, res1, res2, res3, res4, scoreinc=0;
	Button[] choice = new Button[4];
	public  List arrayList = new ArrayList<Integer>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startgame);
	}
	@Override
	public void onStart(){
		super.onStart();
		
		Random rm = new Random();
		final int[] operation = new int[3];
		final int a = rm.nextInt(3)+0;
		opt   = rm.nextInt(2)+1;
		rand1 = rm.nextInt(5)+1;
		rand2 = rm.nextInt(5)+1;	
		generateUnique();
		int1  = (TextView)findViewById(R.id.integer1);
		int1.setText(String.valueOf(rand1));
		
		operation[1]=rand1+rand2;
		operation[2]=rand1-rand2;
		
		operator = (TextView)findViewById(R.id.operator);
		if(opt == 1)
			operator.setText("+");
		else if(opt==2)
			operator.setText("-");
		
		int2 = (TextView)findViewById(R.id.integer2);int2.setText(String.valueOf(rand2));

		choice[0] = (Button)findViewById(R.id.choice1);
		choice[1] = (Button)findViewById(R.id.choice2);
		choice[2] = (Button)findViewById(R.id.choice3);
		choice[3] = (Button)findViewById(R.id.choice4);		
		
		choice[0].setText(String.valueOf(res1));
		choice[1].setText(String.valueOf(res2));
 	    choice[2].setText(String.valueOf(res3));
 	    choice[3].setText(String.valueOf(res4));	 	
   
 	    choice[a].setText(String.valueOf(operation[opt]));
 	    
 	    score = (TextView)findViewById(R.id.showscore);
 	    
 	    choice[0].setOnClickListener(new OnClickListener() {
       	public void onClick(View v)
     	   {	
              	if (choice[0].getText() == String.valueOf(operation[opt])){
    			   scoreinc++;
    			   score.setText(String.valueOf(scoreinc));
    			  onStart();
              	}
              	else 
              		onStart();
     	   }
 	    	 });
 	    choice[1].setOnClickListener(new OnClickListener() {
 	       	public void onClick(View v)
 	     	   {	
 	              	if (choice[1].getText() == String.valueOf(operation[opt])){
 	    			   scoreinc++;
 	    			  score.setText(String.valueOf(scoreinc));
 	    			  onStart();
 	              	}
 	              	else 
 	              		onStart();
 	     	   }
 	         });
 	    choice[2].setOnClickListener(new OnClickListener() {
 	       	public void onClick(View v)
 	     	   {	
 	              	if (choice[2].getText() == String.valueOf(operation[opt])){
 	    			   scoreinc++;
 	    			  score.setText(String.valueOf(scoreinc));
 	    			  onStart();
 	              	}
 	              	else 
 	              		onStart();
 	     	   }
 	         });
 	    choice[3].setOnClickListener(new OnClickListener() {
 	       	public void onClick(View v)
 	     	   {	
 	              	if (choice[3].getText() == String.valueOf(operation[opt])){
 	    			   scoreinc++;
 	    			  score.setText(String.valueOf(scoreinc));
 	    			  onStart();
 	              	}
 	              	else 
 	              		onStart();
 	     	   }
 	         });
	}

	
	public  void generateUnique(){
			for (int i = 0; i < 18; i++) {
				arrayList.add(i);
			}

			for(int i = 0;i< 4;i++){
			int index = new Random().nextInt(arrayList.size());
			switch(i){
			case 0:
				res1 = (Integer) arrayList.get(index);
				break;
			case 1:
				res2 = (Integer) arrayList.get(index);
				break;
			case 2:
				res3 = (Integer) arrayList.get(index);
				break;
			case 3:
				res4 = (Integer) arrayList.get(index);
				break;
			}
			
			arrayList.remove(index);
			}		
		}

}