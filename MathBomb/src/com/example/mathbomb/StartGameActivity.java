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

public class StartGameActivity extends Activity {

    private Date mDate;
    private SaveScore mSaveScore;
    private TextView txtShowScore, txtShowHighScore, txtShowTimeLeft,
            txtShowResult, txtfirstRandomNumber, txtsecondRandomNumber,
            txtrandomOperator;
    private int firstRandomInteger, secondRandomInteger, randomOperator, score,
            textColor;
    private final int categoryEasy = 0, categoryNormal = 1, categoryHard = 2,
            timerGameSpan = 31000, timerGameSpeed = 1000,
            timerCheckSpan = 1000, timerCheckSpeed = 500,
            maxIndexOfRandomResults = 3, addOperator = 1, subOperator = 2,
            easyMin = 1, easyMax = 5, easySize = 5, normalMin = 1,
            normalMax = 20, normalSize = 20, hardMin = 10, hardMax = 10,
            hardSize = 40, numberOfResults=4, secondNumber=0;
    private Button[] btnRandomResult = new Button[4];
    private List<Integer> arrayList = new ArrayList<Integer>();
    private String choiceText = "", newScore = "", alertText = "",
            fileName = "";
    public static final String[] category = { "Easy", "Normal", "Hard", };
    private Random random = new Random();

    // create reusable listener instead of anonymous types
    private OnClickListener choiceClicker = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // get view id
            switch (v.getId()) {
            // set apart views
            case (R.id.choice1):
                choiceText = (String) btnRandomResult[0].getText();
                break;
            case (R.id.choice2):
                choiceText = (String) btnRandomResult[1].getText();
                break;
            case (R.id.choice3):
                choiceText = (String) btnRandomResult[2].getText();
                break;
            case (R.id.choice4):
                choiceText = (String) btnRandomResult[3].getText();
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

        txtfirstRandomNumber = (TextView) findViewById(R.id.integer1);
        txtsecondRandomNumber = (TextView) findViewById(R.id.integer2);
        txtrandomOperator = (TextView) findViewById(R.id.operator);
        txtShowScore = (TextView) findViewById(R.id.showscore);
        txtShowScore.setText(Integer.toString(score));
        txtShowResult = (TextView) findViewById(R.id.showresult);
        txtShowResult.setVisibility(View.INVISIBLE);
        txtShowHighScore = (TextView) findViewById(R.id.showhighscore);
        txtShowTimeLeft = (TextView) findViewById(R.id.showtimeleft);

        int index = R.id.choice1;

        for (int i = 0; i <= maxIndexOfRandomResults; i++) {
            btnRandomResult[i] = (Button) findViewById(index);
            index++;
        }

        Bundle getextra = getIntent().getExtras();
        int choices = getextra.getInt("choice");

        try {
            switch (choices) {
            case categoryEasy: {
                SingleRecord easySingleRecord = new SingleRecord(this,
                        SingleRecord.EASYFILENAME);
                easySingleRecord.getDetails();
                Record easyrecord = easySingleRecord.highScores().get(0);
                newScore = easyrecord.getScore();
                txtShowHighScore.setText(newScore);
                break;
            }
            case categoryNormal: {
                SingleRecord normalSingleRecord = new SingleRecord(this,
                        SingleRecord.NORMALFILENAME);
                normalSingleRecord.getDetails();
                Record normalrecord = normalSingleRecord.highScores().get(0);
                newScore = normalrecord.getScore();
                txtShowHighScore.setText(newScore);
                break;
            }
            case categoryHard: {
                SingleRecord hardSingleRecord = new SingleRecord(this,
                        SingleRecord.HARDFILENAME);
                hardSingleRecord.getDetails();
                Record hardrecord = hardSingleRecord.highScores().get(0);
                newScore = hardrecord.getScore();
                txtShowHighScore.setText(newScore);
                break;
            }
            default:
                return;
            }
        } catch (Exception e) {
            txtShowHighScore.setText(R.string.defaultvalue);
            e.printStackTrace();
        }

        resetGame();

        for (int i = 0; i <= maxIndexOfRandomResults; i++) {
            btnRandomResult[i].setOnClickListener(choiceClicker);
        }

        startGameTimer(timerGameSpan, timerGameSpeed);
    }

    private void startGameTimer(int span, int speed){
        if(span == timerGameSpan) {
            new CountDownTimer(timerGameSpan, timerGameSpeed) {
                public void onTick(long millisUntilFinished) {
                    txtShowTimeLeft.setText(millisUntilFinished / timerGameSpeed
                            + getString(R.string.seconds));
                }
    
                public void onFinish() {
                    gameOver();
                }
            }.start();
        } else if (span == timerCheckSpan) {
            new CountDownTimer(timerCheckSpan, timerCheckSpeed) {
                public void onTick(long millisUntilFinished) {
                    txtShowResult.setVisibility(View.VISIBLE);
                    txtShowResult.setTextColor(textColor);
                    txtShowResult.setText(alertText);
                }

                public void onFinish() {
                    txtShowResult.setVisibility(View.INVISIBLE);
                }
            }.start();
        }
    }

    private void resetGame() {
        final int a = random.nextInt(numberOfResults);
        randomOperator = random.nextInt(subOperator) + addOperator;
        Bundle getextra = getIntent().getExtras();
        int resetchoice = getextra.getInt("choice");

        if (resetchoice == categoryEasy) {
            generatingNumbers(easyMin, easyMax);
        } else if (resetchoice == categoryNormal) {
            generatingNumbers(normalMin, normalMax);
        } else if (resetchoice == categoryHard) {
            generatingNumbers(hardMin, hardMax);
        }

        txtfirstRandomNumber.setText(Integer.toString(firstRandomInteger));
        txtsecondRandomNumber.setText(Integer.toString(secondRandomInteger));
        
        generatingNumbers(calculateAnswer(), secondNumber);
        btnRandomResult[a].setText(String.valueOf(calculateAnswer()));
    }

    private int calculateAnswer() {
        if (randomOperator == addOperator) {
            txtrandomOperator.setText("+");
            return firstRandomInteger + secondRandomInteger;
        } else if (randomOperator == subOperator) {
            txtrandomOperator.setText("-");
            return firstRandomInteger - secondRandomInteger;
        }
        return 0;
    }

    private void checkAnswer() {
        if (choiceText == Integer.toString(calculateAnswer())) {
            score++;
            txtShowScore.setText(String.valueOf(score));
            alertText = "Correct!";
            textColor = Color.GREEN;
        } else {
            alertText = "Wrong!";
            textColor = Color.RED;
        }

        startGameTimer(timerCheckSpan, timerCheckSpeed);
        resetGame();
    }

    private void gameOver() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.gameOver)
            .setMessage(getString(R.string.yourscoreis) + " " + txtShowScore.getText())
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                        mDate = new Date();
                        try {
                            Bundle getextra = getIntent().getExtras();
                            int choices = getextra.getInt("choice");
                            if (choices == categoryEasy) {
                                fileName = SingleRecord.EASYFILENAME;
                            } else if (choices == categoryNormal) {
                                fileName = SingleRecord.NORMALFILENAME;
                            } else if (choices == categoryHard) {
                                fileName = SingleRecord.HARDFILENAME;
                            }
                            mSaveScore = new SaveScore(StartGameActivity.this);
                            mSaveScore.saveScore(score, mDate,fileName);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        Intent i = new Intent(StartGameActivity.this, MainMenuActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        }
                }).setCancelable(false).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private List<Integer> generatingNumbers(int num1, int num2) {
        if(num2 == secondNumber){
            // clear arraylist
            arrayList.clear();
    
            Bundle getextra = getIntent().getExtras();
            int choices = getextra.getInt("choice");
    
            int categorySize = 0;
    
            if (choices == categoryEasy) {
                categorySize = easySize;
            } else if (choices == categoryNormal) {
                categorySize = normalSize;
            } else if (choices == categoryHard) {
                categorySize = hardSize;
            }
    
            for (int i = 0; i < categorySize; i++) {
                arrayList.add(i);
            }
    
            // removes num1 in random set if its inside the setasy
            if (arrayList.contains(num1)) {
                arrayList.remove(arrayList.get(num1));
            }
    
            for (int i = 0; i <= maxIndexOfRandomResults; i++) {
                int index = new Random().nextInt(arrayList.size());
                btnRandomResult[i].setText(Integer.toString((Integer) arrayList
                        .get(index)));
                arrayList.remove(index);
            }
        } else {
            firstRandomInteger = random.nextInt(num2) + num1;
            secondRandomInteger = random.nextInt(num2) + num1;
            // avoid negative results
            while (secondRandomInteger > firstRandomInteger) {
                firstRandomInteger = random.nextInt(num2) + num1;
                secondRandomInteger = random.nextInt(num2) + num1;
            }
        }
        return arrayList;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}