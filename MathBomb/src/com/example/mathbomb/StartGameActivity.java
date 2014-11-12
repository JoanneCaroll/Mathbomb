
package com.example.mathbomb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StartGameActivity extends Activity {
	
	private static final String TAG = "StartGameActivity";
	private Date mDate;
	private SaveScore mSaveScore;	
    private TextView 
    	showScore, showHighScore, showTimeLeft, showResult,
    	randomInteger1, randomInteger2, randomOperator;    
    private int 
    	randomInt1, randomInt2, randomOpt,
    	randomResult1, randomResult2, randomResult3, randomResult4, 
    	score, answer;
    private Button[] choice = new Button[4];
    private List<Integer> arrayList = new ArrayList<Integer>();
    private String choiceText = "";
//    private SingleRecord record;
    private Random random = new Random();
    
    // create reusable listener instead of anonymous types
    private OnClickListener choiceClicker = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // get view id
            switch (v.getId()) {
            // set apart views
            case (R.id.choice1):
            	choiceText = (String) choice[0].getText();
                break;
            case (R.id.choice2):
            	choiceText = (String) choice[1].getText();
                break;
            case (R.id.choice3):
            	choiceText = (String) choice[2].getText();
                break;
            case (R.id.choice4):
            	choiceText = (String) choice[3].getText();
                break;
            }
            // checkanswer only when a button is clicked
            checkAnswer();
        }
    };
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startgame);
        
        mSaveScore = new SaveScore(this);
        
        randomInteger1 = (TextView) findViewById(R.id.integer1);
		randomInteger2 = (TextView) findViewById(R.id.integer2);
		randomOperator = (TextView) findViewById(R.id.operator);  
		
		choice[0] = (Button) findViewById(R.id.choice1);
		choice[1] = (Button) findViewById(R.id.choice2);
		choice[2] = (Button) findViewById(R.id.choice3);
		choice[3] = (Button) findViewById(R.id.choice4);	
		
		showScore = (TextView) findViewById(R.id.showscore);
		showScore.setText(Integer.toString(score));
		
		showResult = (TextView)findViewById(R.id.showresult);
		showResult.setVisibility(View.INVISIBLE);
		
		ArrayList<Record> mRecord = null;		
		try {
				mRecord = SingleRecord.get(this).getDetails();   
				//get the highest score
				Record record = mRecord.get(0);							
				showHighScore = (TextView)findViewById(R.id.showhighscore);
				showHighScore.setText(record.getScore().toString());
		} catch (Exception e) {
				e.printStackTrace(); 	
		        showHighScore = (TextView)findViewById(R.id.showhighscore);
		        showHighScore.setText("0");
		}
        
        resetGame();
        
        choice[0].setOnClickListener(choiceClicker);
        choice[1].setOnClickListener(choiceClicker);
        choice[2].setOnClickListener(choiceClicker);
        choice[3].setOnClickListener(choiceClicker);
        
        showTimeLeft = (TextView) findViewById(R.id.showtimeleft);
        
        new CountDownTimer(31000, 1000) {
            public void onTick(long millisUntilFinished) {
            	showTimeLeft.setText(millisUntilFinished / 1000 + " seconds");               
            }
            public void onFinish() {
            	gameOver();
            }
        }.start();          
        
    }
    
    public void generateUnique(int answer) {    	
    	//clear arraylist
        arrayList.clear();
        
        Bundle getextra = getIntent().getExtras();
		int choices = getextra.getInt("choice");
		
		if(choices==0) {
			for (int i = 0; i < 5; i++) {
	            arrayList.add(i);
			}
        } else if(choices==1) {
			for (int i = 0; i < 20; i++) {
	            arrayList.add(i);
			}
        } else if(choices==2)
        {
			for (int i = 0; i < 40; i++) {
	            arrayList.add(i);
			}
        }
        
        //removes answer in random set if its inside the set
        if(arrayList.contains(answer)){
            arrayList.remove(arrayList.get(answer));
        }  
        
        for (int i = 0; i < 4; i++) {
            int index = new Random().nextInt(arrayList.size());
            switch (i) {
	            case 0:
	            	randomResult1 = (Integer) arrayList.get(index);
	                break;
	            case 1:
	            	randomResult2 = (Integer) arrayList.get(index);
	                break;
	            case 2:
	            	randomResult3 = (Integer) arrayList.get(index);
	                break;
	            case 3:
	            	randomResult4 = (Integer) arrayList.get(index);
	                break;
            }
            arrayList.remove(index);
        }
        
        choice[0].setText(Integer.toString(randomResult1));
        choice[1].setText(Integer.toString(randomResult2));
        choice[2].setText(Integer.toString(randomResult3));
        choice[3].setText(Integer.toString(randomResult4));
    }
    
    private void resetGame() {
        try {
	        final int a = random.nextInt(3);	        
	        randomOpt = random.nextInt(2) + 1;
	        Bundle getextra = getIntent().getExtras();
			int resetchoice = getextra.getInt("choice");
					
			if(resetchoice == 0) {
				generateInput(1,5);
			} else if(resetchoice == 1) {
				generateInput(1,20);
			} else if(resetchoice == 2) {
				generateInput(10,10);
			}
	
			randomInteger1.setText(Integer.toString(randomInt1));
			randomInteger2.setText(Integer.toString(randomInt2));
	
	        calculateAnswer();
	        generateUnique(answer);
	        
	        choice[a].setText(Integer.toString(answer));
        } catch (Exception e) {
        	Log.e(TAG, "resetGame()");
        }
    }
    
    private void calculateAnswer() {
    	try {
	        if (randomOpt == 1) {
	        	randomOperator.setText("+");
	        	answer = randomInt1 + randomInt2;
	        } else if (randomOpt == 2) {
	        	randomOperator.setText("-");
	        	answer = randomInt1 - randomInt2;
	        }
    	} catch (Exception e) {
    		Log.e(TAG, "calculateAnswer()");
    	}
    }
    
    private void checkAnswer() {
    	try {
	        if (choiceText == Integer.toString(answer)) {
	        	score++;
	            showScore.setText(String.valueOf(score));
	            new CountDownTimer(1000, 500) {
	                public void onTick(long millisUntilFinished) {
	                	showResult.setVisibility(View.VISIBLE);
	                	showResult.setTextColor(Color.GREEN);
	                	showResult.setText("Correct!");              
	                }
	                public void onFinish() {
	                	showResult.setVisibility(View.INVISIBLE);
	                }
	            }.start();           
	            resetGame();
	        } else {
	        	new CountDownTimer(1000, 500) {
	                public void onTick(long millisUntilFinished) {
	                	showResult.setVisibility(View.VISIBLE);
	                	showResult.setTextColor(Color.RED);
	                	showResult.setText("Wrong!");              
	                }
	                public void onFinish() {
	                	showResult.setVisibility(View.INVISIBLE);
	                }
	            }.start();         
	            resetGame();
	        }
    	} catch (Exception e) {
    		Log.e(TAG, "checkAnswer()");
    	}
    }    
    
    private void gameOver() {
    	try {
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
		    	builder.setTitle("Game Over!")
		        .setMessage("Your score is " + showScore.getText())
		        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int id) {
		    			mDate = new Date();	    			
		            	try {
		    					mSaveScore.saveScore(score, mDate);   	
		    					Log.i(TAG,"Score saved.");
		    			} catch (Exception e) {
		    				e.printStackTrace();
		    				Log.e(TAG,"Error saving score");
		    			}
		            	Intent i = new Intent(StartGameActivity.this, MainMenuActivity.class);
		            	startActivity(i);
		            	finish();
		            }
		        }).setCancelable(false).show(); 
    	} catch(Exception e){
    		Log.e(TAG, "gameOver()");
    	}
    }
    
   private void generateInput(int min, int max){
	   try {
		   randomInt1 = random.nextInt(max) + min;
		   randomInt2 = random.nextInt(max) + min;		   
	        // avoid negative results
	       while (randomInt2 > randomInt1) {
	        	randomInt1 = random.nextInt(max) + min;
	        	randomInt2 = random.nextInt(max) + min;
	        }
	   } catch (Exception e) {
		   Log.e(TAG, "generateInput()");
	   }
    }
}