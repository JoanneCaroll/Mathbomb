
package com.example.mathbomb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StartGame extends Activity {
	
	private Date mDate;
	private SaveScore mSaveScore;
    public TextView num1, num2, operator, score, timer;
    public int rand1, opt, rand2, res1, res2, res3, res4, scoreinc, intres;
    public Button[] choice = new Button[4];
    public List arrayList = new ArrayList<Integer>();
    public String answer = "";
    public Random rm = new Random();
    public SingleRecord record;
    // create reusable listener instead of anonymous types
    private OnClickListener clicker = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // get view id
            switch (v.getId()) {
            // set apart views
            case (R.id.choice1):
                answer = (String) choice[0].getText();
                break;
            case (R.id.choice2):
                answer = (String) choice[1].getText();
                break;
            case (R.id.choice3):
                answer = (String) choice[2].getText();
                break;
            case (R.id.choice4):
                answer = (String) choice[3].getText();
                break;
            }
            // checkanswer only when a button is clicked
            checkAnswer();
        }
    };
    @SuppressLint("SimpleDateFormat") 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startgame);		
        mSaveScore = new SaveScore(this);
        ArrayList<Record> mRecord = null;
        try {
			mRecord = SingleRecord.get(this).getDetails();
		} catch (Exception e) {
			e.printStackTrace(); 
		}
        Record record = mRecord.get(0);      
        num1 = (TextView) findViewById(R.id.integer1);
        num2 = (TextView) findViewById(R.id.integer2);
        operator = (TextView) findViewById(R.id.operator);
        
        choice[0] = (Button) findViewById(R.id.choice1);
        choice[1] = (Button) findViewById(R.id.choice2);
        choice[2] = (Button) findViewById(R.id.choice3);
        choice[3] = (Button) findViewById(R.id.choice4);
        
        score = (TextView) findViewById(R.id.showscore);
        score.setText(Integer.toString(scoreinc));
        resetGame();
        choice[0].setOnClickListener(clicker);
        choice[1].setOnClickListener(clicker);
        choice[2].setOnClickListener(clicker);
        choice[3].setOnClickListener(clicker);
        timer = (TextView) findViewById(R.id.showtime);
        new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText(millisUntilFinished / 1000 + " seconds");
            }
            public void onFinish() {    
            	gameOver();
            }
        }.start();  
        
    }
    public void generateUnique(int intres) {
    	
    	//clear arraylist
        arrayList.clear();
        
        Bundle extra = getIntent().getExtras();
		int choices = extra.getInt("choice");
		if(choices==0)
        {
			for (int i = 0; i < 5; i++) {
	            arrayList.add(i);
			}
        }
		else if(choices==1)
        {
			for (int i = 0; i < 20; i++) {
	            arrayList.add(i);
			}
        }
		else if(choices==2)
        {
			for (int i = 0; i < 40; i++) {
	            arrayList.add(i);
			}
        }
        
        //removes answer in random set if its inside the set
        if(arrayList.contains(intres)){
            arrayList.remove(arrayList.get(intres));
            Log.i("generateUnique()", intres + "has");
        }  
            
        Log.i("generateUnique()", arrayList.size()+"-ish");

        for (int i = 0; i < 4; i++) {
            int index = new Random().nextInt(arrayList.size());
            switch (i) {
            case 0:
                res1 = (Integer) arrayList.get(index);
                Log.i("generateUnique()", res1 + "");
                break;
            case 1:
                res2 = (Integer) arrayList.get(index);
                Log.i("generateUnique()", res2 + "");
                break;
            case 2:
                res3 = (Integer) arrayList.get(index);
                Log.i("generateUnique()", res3 + "");
                break;
            case 3:
                res4 = (Integer) arrayList.get(index);
                Log.i("generateUnique()", res4 + "");
                break;
            }
            arrayList.remove(index);

        }
        choice[0].setText(Integer.toString(res1));
        choice[1].setText(Integer.toString(res2));
        choice[2].setText(Integer.toString(res3));
        choice[3].setText(Integer.toString(res4));
    }
    private void resetGame() {
        
        final int a = rm.nextInt(3);
        
        opt = rm.nextInt(2) + 1;
        Bundle extra2 = getIntent().getExtras();
		int resetchoice = extra2.getInt("choice");
		
		
		if(resetchoice == 0)
		{
			generateInput(1,5);
		}
		else if(resetchoice == 1)
		{
			generateInput(1,20);
		}
		else if(resetchoice == 2)
		{
			generateInput(10,10);
		}

		num1.setText(Integer.toString(rand1));
        num2.setText(Integer.toString(rand2));

        calculateAnswer();
        generateUnique(intres);
        choice[a].setText(Integer.toString(intres));
    }
    private void calculateAnswer() {
        if (opt == 1) 
        {
            operator.setText("+");
            intres = rand1 + rand2;
        } 
        else if (opt == 2) 
        {
            operator.setText("-");
            intres = rand1 - rand2;
        }
    }
    private void checkAnswer() {
        if (answer == Integer.toString(intres)) {
            scoreinc++;
            score.setText(String.valueOf(scoreinc));
            resetGame();
        } else {
            resetGame();
        }
    }
    private void gameOver() {        
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	{
	    	builder.setTitle("Game Over!")
	        .setMessage("Your score is " + score.getText() + "\nNEW HIGH SCORE!")
	        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int id) {
	    			try {
	    					mSaveScore.saveScore(scoreinc, mDate);   
	    					Log.i("SAVING",scoreinc+"" );		    					
	    			} 
	    			catch (Exception e) {
	    				e.printStackTrace();
	    				Log.e("error"," saving score");
	    			}
	            	finish();
	            }
	        }).setCancelable(false).show(); 
    	}
    }
   private void generateInput(int min, int max){
    	rand1 = rm.nextInt(max) + min;
		rand2 = rm.nextInt(max) + min;
        // avoid negative results
        while (rand2 > rand1) {
            rand1 = rm.nextInt(max) + min;
            rand2 = rm.nextInt(max) + min;
        }
    }
}