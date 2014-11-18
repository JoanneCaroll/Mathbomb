
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity_StartGame extends Activity {

    private Date mDate;
    public static final String easy = "easy";
    private Easy_SaveScore mEasySaveScore;	
    private Normal_SaveScore mNormalSaveScore;
    private Hard_SaveScore mHardSaveScore;
    private TextView 
    showScore, showHighScore, showTimeLeft, showResult,
    txtfirstRandomNumber, txtsecondRandomNumber, randomOperator;
    private int 
    firstRandomInteger, secondRandomInteger, randomOpt,
    randomResult1, randomResult2, randomResult3, randomResult4, 
    score, answer;
    private Button[] randomResult = new Button[4];
    private List<Integer> arrayList = new ArrayList<Integer>();
    private String choiceText = "";
    public static final String[] category = {
        "Easy", "Normal", "Hard",
    };
    String alertText = "";
    int textColor;
    private Random random = new Random();

    // create reusable listener instead of anonymous types
    private OnClickListener choiceClicker = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // get view id
            switch (v.getId()) {
            // set apart views
            case (R.id.choice1):
                choiceText = (String) randomResult[0].getText();
            break;
            case (R.id.choice2):
                choiceText = (String) randomResult[1].getText();
            break;
            case (R.id.choice3):
                choiceText = (String) randomResult[2].getText();
            break;
            case (R.id.choice4):
                choiceText = (String) randomResult[3].getText();
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

        mEasySaveScore = new Easy_SaveScore(this);
        mNormalSaveScore = new Normal_SaveScore(this);
        mHardSaveScore = new Hard_SaveScore(this);

        txtfirstRandomNumber = (TextView) findViewById(R.id.integer1);
        txtsecondRandomNumber = (TextView) findViewById(R.id.integer2);
        randomOperator = (TextView) findViewById(R.id.operator);  

        randomResult[0] = (Button) findViewById(R.id.choice1);
        randomResult[1] = (Button) findViewById(R.id.choice2);
        randomResult[2] = (Button) findViewById(R.id.choice3);
        randomResult[3] = (Button) findViewById(R.id.choice4);	

        showScore = (TextView) findViewById(R.id.showscore);
        showScore.setText(Integer.toString(score));

        showResult = (TextView)findViewById(R.id.showresult);
        showResult.setVisibility(View.INVISIBLE);

        Bundle getextra = getIntent().getExtras();
        int choices = getextra.getInt("choice");

        ArrayList<Easy_Record> mEasyRecord = null;
        ArrayList<Normal_Record> mNormalRecord = null;
        ArrayList<Hard_Record> mHardRecord = null;

        try {
            mEasyRecord = Easy_SingleRecord.get(this).getDetails();
            Easy_Record easyrecord = mEasyRecord.get(0);	
            if(choices == 0) {
                showHighScore = (TextView)findViewById(R.id.showhighscore);
                showHighScore.setText(easyrecord.getScore());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            mNormalRecord = Normal_SingleRecord.get(this).getDetails();
            Normal_Record normalrecord = mNormalRecord.get(0);
            if(choices == 1) {
                showHighScore = (TextView)findViewById(R.id.showhighscore);
                showHighScore.setText(normalrecord.getScore());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            mHardRecord = Hard_SingleRecord.get(this).getDetails();
            Hard_Record hardrecord = mHardRecord.get(0);
            if(choices == 2) {
                showHighScore = (TextView)findViewById(R.id.showhighscore);
                showHighScore.setText(hardrecord.getScore());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        resetGame();

        randomResult[0].setOnClickListener(choiceClicker);
        randomResult[1].setOnClickListener(choiceClicker);
        randomResult[2].setOnClickListener(choiceClicker);
        randomResult[3].setOnClickListener(choiceClicker);

        showTimeLeft = (TextView) findViewById(R.id.showtimeleft);

        new CountDownTimer(11000, 1000) {
            public void onTick(long millisUntilFinished) {
                showTimeLeft.setText(millisUntilFinished / 1000 + " sec(s)");
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
        if(arrayList.contains(answer)) {
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

        randomResult[0].setText(Integer.toString(randomResult1));
        randomResult[1].setText(Integer.toString(randomResult2));
        randomResult[2].setText(Integer.toString(randomResult3));
        randomResult[3].setText(Integer.toString(randomResult4));
    }

    private void resetGame() {
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
        txtfirstRandomNumber.setText(Integer.toString(firstRandomInteger));
        txtsecondRandomNumber.setText(Integer.toString(secondRandomInteger));
        calculateAnswer();
        generateUnique(answer);
        randomResult[a].setText(Integer.toString(answer));
    }

    private void calculateAnswer() {
        if (randomOpt == 1) {
            randomOperator.setText("+");
            answer = firstRandomInteger + secondRandomInteger;
        } else if (randomOpt == 2) {
            randomOperator.setText("-");
            answer = firstRandomInteger - secondRandomInteger;
        }
    }

    private void checkAnswer() {
        
        if (choiceText == Integer.toString(answer)) {
            score++;
            showScore.setText(String.valueOf(score));
            alertText = "Correct!";
            textColor = Color.GREEN;
        } else {
            alertText = "Wrong!";
            textColor = Color.RED;
        }
        
        new CountDownTimer(1000, 500) {
            public void onTick(long millisUntilFinished) {
                showResult.setVisibility(View.VISIBLE);
                showResult.setTextColor(textColor);
                showResult.setText(alertText);
            }
            public void onFinish() {
                showResult.setVisibility(View.INVISIBLE);
            }
        }.start();
        
        resetGame();
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
                        Bundle getextra = getIntent().getExtras();
                        int choices = getextra.getInt("choice");
                        if(choices==0)
                        {
                            mEasySaveScore.saveScore(score, mDate);
                        } else if (choices==1)
                        {
                            mNormalSaveScore.saveScore(score, mDate);
                        } else if (choices==2)
                        {
                            mHardSaveScore.saveScore(score, mDate);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Intent i = new Intent(Activity_StartGame.this, Activity_MainMenu.class);
                    startActivity(i);
                    finish();
                }
            }).setCancelable(false).show(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateInput(int min, int max) {
        firstRandomInteger = random.nextInt(max) + min;
        secondRandomInteger = random.nextInt(max) + min;
        // avoid negative results
        while (secondRandomInteger > firstRandomInteger) {
            firstRandomInteger = random.nextInt(max) + min;
            secondRandomInteger = random.nextInt(max) + min;
        }
    }
}