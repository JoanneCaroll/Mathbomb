package com.example.mathbomb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StartGame extends Activity {
	public TextView int1, int2, result, operator, score, check;
	public int rand1, opt, rand2, res1, res2, res3, res4, scoreinc=0, intres;
	Button[] choice = new Button[4];
	public  List arrayList = new ArrayList<Integer>();
	public String answer = "";

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startgame);
		
			//generateUnique();
			int1  = (TextView)findViewById(R.id.integer1);
			int2 = (TextView)findViewById(R.id.integer2);
			operator = (TextView)findViewById(R.id.operator);
			
			choice[0] = (Button)findViewById(R.id.choice1);	
			choice[1] = (Button)findViewById(R.id.choice2);
			choice[2] = (Button)findViewById(R.id.choice3);
			choice[3] = (Button)findViewById(R.id.choice4);
			score = (TextView)findViewById(R.id.showscore);
			
			resetGame();
			
			choice[0].setOnClickListener(clicker);
			choice[1].setOnClickListener(clicker);
			choice[2].setOnClickListener(clicker);			
			choice[3].setOnClickListener(clicker);

			
			
	}
	
	public  void generateUnique(){
			for (int i = 0; i < 4; i++) {
				arrayList.add(i);
			}
			
			for(int i = 0;i< 4;i++){				
				int index = new Random().nextInt(arrayList.size());
				switch(i){
				case 0:
					res1 = (Integer) arrayList.get(index);
					Log.i("caroll",res1+"");
					break;
				case 1:
					res2 = (Integer) arrayList.get(index);
					Log.i("caroll",res2+"");
					break;
				case 2:
					res3 = (Integer) arrayList.get(index);
					Log.i("caroll",res3+"");
					break;
				case 3:
					res4 = (Integer) arrayList.get(index);
					Log.i("caroll",res4+"");
					break;
				 }
				arrayList.remove(index);					 	
			}
			choice[0].setText(Integer.toString(res1));
			choice[1].setText(Integer.toString(res2));
	 	    choice[2].setText(Integer.toString(res3));
	 	    choice[3].setText(Integer.toString(res4));
		}
	
	public void calculateAnswer(){
		if(opt == 1)
		{
			operator.setText("+");
			intres = rand1+rand2;
			if (intres==res1&&intres==res2&&intres==res3&&intres==res4)
			{
				generateUnique();
			}
		}
		else if(opt==2)
		{
			operator.setText("-");
			intres=rand1-rand2;
			if (intres==res1&&intres==res2&&intres==res3&&intres==res4)
			{
				generateUnique();
			}
		}
	}
	//create reusable listener instead of anonymous types
	 private OnClickListener clicker = new OnClickListener() {
       @Override
       public void onClick(View v) {
       	//get view id
           switch(v.getId()){
           	//set apart views
               case (R.id.choice1):
                   answer = (String) choice[0].getText();
                   break;
               case (R.id.choice2):
            	   answer = (String)choice[1].getText();
                   break;
               case (R.id.choice3):
            	   answer = (String)choice[2].getText();
                   break;
               case (R.id.choice4):
            	   answer = (String)choice[3].getText();
                   break;
           }
           //checkanswer only when a button is clicked
           checkAnswer();
       }
   };

     private void checkAnswer(){
       if (answer ==Integer.toString(intres)){
           scoreinc++;
           score.setText(String.valueOf(scoreinc));
           Toast.makeText(StartGame.this, "Correct!", Toast.LENGTH_SHORT).show();
           resetGame();
       }
       else{
       	Toast.makeText(StartGame.this, "Wrong!", Toast.LENGTH_SHORT).show();
       	resetGame();
       }
   }
    
   private void resetGame(){
	    Random rm = new Random();
		final int a = rm.nextInt(3)+0;
	    Bundle extra = getIntent().getExtras();
		int choices = extra.getInt("choice");
		if(choices == 0)
	   	{
			opt   = rm.nextInt(2)+1;
			rand1 = rm.nextInt(5)+1;
			rand2 = rm.nextInt(5)+1;
			
			//avoid negative results
			while(rand2>rand1)
			{
				rand1 = rm.nextInt(5)+1;
				rand2 = rm.nextInt(5)+1;	
			}
			
			int1.setText(Integer.toString(rand1));
			int2.setText(Integer.toString(rand2));
			
			generateUnique();
			calculateAnswer();
	   	}
		else if(choices==1)
	   	{			
			opt   = rm.nextInt(2)+1;
			rand1 = rm.nextInt(20)+1;
			rand2 = rm.nextInt(20)+1;
			
			//avoid negative results
			while(rand2>rand1)
			{
				rand1 = rm.nextInt(20)+1;
				rand2 = rm.nextInt(20)+1;	
			}
			
			int1.setText(Integer.toString(rand1));
			int2.setText(Integer.toString(rand2));
			
			generateUnique();
			calculateAnswer();
	   	}
		else if(choices==2)
	   	{			
			opt   = rm.nextInt(2)+1;
			rand1 = rm.nextInt(20)+10;
			rand2 = rm.nextInt(20)+10;
			
			//avoid negative results
			while(rand2>rand1)
			{
				rand1 = rm.nextInt(20)+10;
				rand2 = rm.nextInt(20)+10;	
			}
			
			int1.setText(Integer.toString(rand1));
			int2.setText(Integer.toString(rand2));
			
			generateUnique();
			calculateAnswer();
	   	}
		
		if(intres==res1 || intres==res2 || intres==res3|| intres==res4)
		{
			Log.i("intres comparing", intres+"");
			generateUnique();
		}
		
	    choice[a].setText(Integer.toString(intres));
       
       
   }


}