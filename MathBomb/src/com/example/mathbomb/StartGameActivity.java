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
import android.widget.Toast;

public class StartGameActivity extends Activity {
    private Date mDate;
    private SaveScore mSaveScore;
    private TextView txtShowScore, txtShowHighScore, txtShowTimeLeft,
            txtShowResult, txtFirstRandomNumber, txtSecondRandomNumber,
            txtRandomOperator;
    private int firstRandomInteger, secondRandomInteger, randomOperator, score,
            answer, textColor;
    private final int categoryEasy = 0, categoryNormal = 1, categoryHard = 2,
            timerGameSpan = 30000, timerGameSpeed = 1000,
            timerCheckSpan = 1000, timerCheckSpeed = 500,
            maxIndexOfRandomResults = 3, addOperator = 1, subOperator = 2,
            easyMin = 1, easyMax = 5, easySize = 5, normalMin = 1,
            normalMax = 20, normalSize = 20, hardMin = 10, hardMax = 10,
            hardSize = 40;
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
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Score not saved", Toast.LENGTH_SHORT).show();
        finish();
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startgame);
        txtFirstRandomNumber = (TextView) findViewById(R.id.integer1);
        txtSecondRandomNumber = (TextView) findViewById(R.id.integer2);
        txtRandomOperator = (TextView) findViewById(R.id.operator);
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
        new CountDownTimer(timerGameSpan, timerGameSpeed) {
            public void onTick(long millisUntilFinished) {
                txtShowTimeLeft.setText(millisUntilFinished / timerGameSpeed
                        + getString(R.string.seconds));
            }

            public void onFinish() {
                gameOver();
            }
        }.start();
    }

    public void generateUniqueChoices(int answer) {
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
        // removes answer in random set if its inside the setasy
        if (arrayList.contains(answer)) {
            arrayList.remove(arrayList.get(answer));
        }
        for (int i = 0; i <= maxIndexOfRandomResults; i++) {
            int index = new Random().nextInt(arrayList.size());
            btnRandomResult[i].setText(Integer.toString((Integer) arrayList
                    .get(index)));
            arrayList.remove(index);
        }
    }

    private void resetGame() {
        final int a = random.nextInt(maxIndexOfRandomResults);
        randomOperator = random.nextInt(subOperator) + addOperator;
        Bundle getextra = getIntent().getExtras();
        int resetchoice = getextra.getInt("choice");
        if (resetchoice == categoryEasy) {
            generateInput(easyMin, easyMax);
        } else if (resetchoice == categoryNormal) {
            generateInput(normalMin, normalMax);
        } else if (resetchoice == categoryHard) {
            generateInput(hardMin, hardMax);
        }
        txtFirstRandomNumber.setText(Integer.toString(firstRandomInteger));
        txtSecondRandomNumber.setText(Integer.toString(secondRandomInteger));
        calculateAnswer();
        generateUniqueChoices(answer);
        btnRandomResult[a].setText(Integer.toString(answer));
    }

    private void calculateAnswer() {
        if (randomOperator == addOperator) {
            txtRandomOperator.setText("+");
            answer = firstRandomInteger + secondRandomInteger;
        } else if (randomOperator == subOperator) {
            txtRandomOperator.setText("-");
            answer = firstRandomInteger - secondRandomInteger;
        }
    }

    private void checkAnswer() {
        if (choiceText == Integer.toString(answer)) {
            score++;
            txtShowScore.setText(String.valueOf(score));
            alertText = "Correct!";
            textColor = Color.GREEN;
        } else {
            alertText = "Wrong!";
            textColor = Color.RED;
        }
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
        resetGame();
    }

    private void gameOver() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Game Over!")
                    .setMessage("Your score is " + txtShowScore.getText())
                    .setPositiveButton(R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int id) {
                                    mDate = new Date();
                                    try {
                                        Bundle getextra = getIntent()
                                                .getExtras();
                                        int choices = getextra.getInt("choice");
                                        if (choices == categoryEasy) {
                                            fileName = SingleRecord.EASYFILENAME;
                                        } else if (choices == categoryNormal) {
                                            fileName = SingleRecord.NORMALFILENAME;
                                        } else if (choices == categoryHard) {
                                            fileName = SingleRecord.HARDFILENAME;
                                        }
                                        mSaveScore = new SaveScore(
                                                StartGameActivity.this);
                                        mSaveScore.saveScore(score, mDate,
                                                fileName);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    Intent i = new Intent(
                                            StartGameActivity.this,
                                            MainMenuActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(i);
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