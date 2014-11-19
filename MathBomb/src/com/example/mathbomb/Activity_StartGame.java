
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
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity_StartGame extends Activity {

    private Date mDate;
    private Normal_SaveScore mEasySaveScore, mNormalSaveScore, mHardSaveScore;
    private TextView 
    txtshowScore, txtshowHighScore, txtshowTimeLeft, txtshowResult,
    txtfirstRandomNumber, txtsecondRandomNumber, txtrandomOperator;
    private int 
    firstRandomInteger, secondRandomInteger, randomOperator,
    score, answer,textColor;
    private final int 
    button1=0, button2=1, button3=2, button4=3,
    categoryEasy=0, categoryNormal=1, categoryHard=2,
    timerGameSpan=30000, timerGameSpeed=1000,
    timerCheckSpan=1000, timerCheckSpeed=500, maxIndexOfRandomResults=3,
    addOperator=1, subOperator=2,
    easyMin=1, easyMax=5, easySize=5,
    normalMin=1, normalMax=20, normalSize=20,
    hardMin=10, hardMax=10, hardSize=40;
    private Button[] btnrandomResult = new Button[4];
    private List<Integer> arrayList = new ArrayList<Integer>();
    private String choiceText = "", newScore = "", alertText = "";
    public static final String[] category = {
        "Easy", "Normal", "Hard",
    };
    private Random random = new Random();

    // create reusable listener instead of anonymous types
    private OnClickListener choiceClicker = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            // set apart views
                case (R.id.choice1):
                    choiceText = (String) btnrandomResult[button1].getText();
                break;
                case (R.id.choice2):
                    choiceText = (String) btnrandomResult[button2].getText();
                break;
                case (R.id.choice3):
                    choiceText = (String) btnrandomResult[button3].getText();
                break;
                case (R.id.choice4):
                    choiceText = (String) btnrandomResult[button4].getText();
                break;
                default:
                    return;
            }
            // checkanswer only when a button is clicked
            checkAnswer();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_startgame);
        
        txtfirstRandomNumber = (TextView) findViewById(R.id.integer1);
        txtsecondRandomNumber = (TextView) findViewById(R.id.integer2);
        txtrandomOperator = (TextView) findViewById(R.id.operator);  

        btnrandomResult[button1] = (Button) findViewById(R.id.choice1);
        btnrandomResult[button2] = (Button) findViewById(R.id.choice2);
        btnrandomResult[button3] = (Button) findViewById(R.id.choice3);
        btnrandomResult[button4] = (Button) findViewById(R.id.choice4);	

        txtshowScore = (TextView) findViewById(R.id.showscore);
        txtshowScore.setText(Integer.toString(score));

        txtshowResult = (TextView)findViewById(R.id.showresult);
        txtshowResult.setVisibility(View.INVISIBLE);

        txtshowHighScore = (TextView)findViewById(R.id.showhighscore);
        
        Bundle getextra = getIntent().getExtras();
        int choices = getextra.getInt("choice");
        
        try {
            switch(choices) {
                case categoryEasy :
                {
                    Normal_SingleRecord easySingleRecord = new Normal_SingleRecord(this, Normal_SingleRecord.EASYFILENAME);
                    easySingleRecord.getDetails();
                    Normal_Record easyrecord = easySingleRecord.highScores().get(0);
                    newScore = easyrecord.getScore();
                    txtshowHighScore.setText(newScore);
                    break;
                }
                case categoryNormal :
                {
                    Normal_SingleRecord normalSingleRecord = new Normal_SingleRecord(this, Normal_SingleRecord.NORMALFILENAME);
                    normalSingleRecord.getDetails();
                    Normal_Record normalrecord = normalSingleRecord.highScores().get(0);
                    newScore = normalrecord.getScore();
                    txtshowHighScore.setText(newScore);
                    break;
                }
                case categoryHard :
                {
                    Normal_SingleRecord hardSingleRecord = new Normal_SingleRecord(this, Normal_SingleRecord.HARDFILENAME);
                    hardSingleRecord.getDetails();
                    Normal_Record hardrecord =hardSingleRecord.highScores().get(0);
                    newScore = hardrecord.getScore();
                    txtshowHighScore.setText(newScore);
                    break;
                }
                default:
                    return;
            }
        } catch (Exception e) {
            txtshowHighScore.setText("0");
            e.printStackTrace();
        }
        

        resetGame();

        btnrandomResult[button1].setOnClickListener(choiceClicker);
        btnrandomResult[button2].setOnClickListener(choiceClicker);
        btnrandomResult[button3].setOnClickListener(choiceClicker);
        btnrandomResult[button4].setOnClickListener(choiceClicker);

        txtshowTimeLeft = (TextView) findViewById(R.id.showtimeleft);

        new CountDownTimer(timerGameSpan, timerGameSpeed) {
            public void onTick(long millisUntilFinished) {
                txtshowTimeLeft.setText(millisUntilFinished / timerGameSpeed + " sec(s)");
            }
            public void onFinish() {
                gameOver();
            }
        }.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    
    public void generateUnique(int answer) {
        //clear arraylist
        arrayList.clear();

        Bundle getextra = getIntent().getExtras();
        int choices = getextra.getInt("choice");

        if(choices==categoryEasy) {
            for (int i = 0; i < easySize; i++) {
                arrayList.add(i);
            }
        } else if(choices==categoryNormal) {
            for (int i = 0; i < normalSize; i++) {
                arrayList.add(i);
            }
        } else if(choices==categoryHard) {
            for (int i = 0; i < hardSize; i++) {
                arrayList.add(i);
            }
        }

        //removes answer in random set if its inside the setasy
        if(arrayList.contains(answer)) {
            arrayList.remove(arrayList.get(answer));
        }  

        for (int i = 0; i <= maxIndexOfRandomResults; i++) {
            int index = new Random().nextInt(arrayList.size());
            btnrandomResult[i].setText(Integer.toString((Integer) arrayList.get(index)));
            arrayList.remove(index);
        }
    }

    private void resetGame() {
        final int a = random.nextInt(maxIndexOfRandomResults);
        randomOperator = random.nextInt(subOperator) + addOperator;
        Bundle getextra = getIntent().getExtras();
        int resetchoice = getextra.getInt("choice");
        if(resetchoice == categoryEasy) {
            generateInput(easyMin,easyMax);
        } else if(resetchoice == categoryNormal) {
            generateInput(normalMin,normalMax);
        } else if(resetchoice == categoryHard) {
            generateInput(hardMin,hardMax);
        }
        txtfirstRandomNumber.setText(Integer.toString(firstRandomInteger));
        txtsecondRandomNumber.setText(Integer.toString(secondRandomInteger));
        calculateAnswer();
        generateUnique(answer);
        btnrandomResult[a].setText(Integer.toString(answer));
    }

    private void calculateAnswer() {
        if (randomOperator == addOperator) {
            txtrandomOperator.setText("+");
            answer = firstRandomInteger + secondRandomInteger;
        } else if (randomOperator == subOperator) {
            txtrandomOperator.setText("-");
            answer = firstRandomInteger - secondRandomInteger;
        }
    }

    private void checkAnswer() {
        if (choiceText == Integer.toString(answer)) {
            score++;
            txtshowScore.setText(String.valueOf(score));
            alertText = "Correct!";
            textColor = Color.GREEN;
        } else {
            alertText = "Wrong!";
            textColor = Color.RED;
        }
        
        new CountDownTimer(timerCheckSpan, timerCheckSpeed) {
            public void onTick(long millisUntilFinished) {
                txtshowResult.setVisibility(View.VISIBLE);
                txtshowResult.setTextColor(textColor);
                txtshowResult.setText(alertText);
            }
            public void onFinish() {
                txtshowResult.setVisibility(View.INVISIBLE);
            }
        }.start();
        
        resetGame();
    }    

    private void gameOver() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Game Over!")
            .setMessage("Your score is " + txtshowScore.getText())
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    mDate = new Date();	    			
                    try {
                        Bundle getextra = getIntent().getExtras();
                        int choices = getextra.getInt("choice");
                        if(choices==categoryEasy)
                        {
                            mEasySaveScore = new Normal_SaveScore(Activity_StartGame.this);
                            mEasySaveScore.saveScore(score, mDate, Normal_SingleRecord.EASYFILENAME);
                        } else if (choices==categoryNormal)
                        {
                            mNormalSaveScore = new Normal_SaveScore(Activity_StartGame.this);
                            mNormalSaveScore.saveScore(score, mDate, Normal_SingleRecord.NORMALFILENAME);
                        } else if (choices==categoryHard)
                        {
                            mHardSaveScore = new Normal_SaveScore(Activity_StartGame.this);
                            mHardSaveScore.saveScore(score, mDate, Normal_SingleRecord.HARDFILENAME);
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