package com.example.bipullohia.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Button goButton;
    GridLayout gridLayout;
    TextView timerTextview, question, answerStatus, resultDisplay;
    int locationCorrectAnswer, correctCount =0, totalCount=0;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button button0, button1, button2, button3, playAgain;
    RelativeLayout gameplayRelativeLayout;
    CountDownTimer countDownTimer;

    public void playAgain(View view){

        finish();
        startActivity(getIntent());
    }

    public void startGame(View view) {

        goButton.setVisibility(View.GONE);
        gameplayRelativeLayout.setVisibility(View.VISIBLE);

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {

                timerTextview.setText("00 : " + String.valueOf(l / 1000));
            }

            @Override
            public void onFinish() {


                timerTextview.setText("00 : 00");
                gridLayout.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                answerStatus.setText("Your Score : "+String.valueOf(correctCount)+ " / " + String.valueOf(totalCount));
                playAgain.setVisibility(View.VISIBLE);


            }
        }.start();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = (Button) findViewById(R.id.goButton);
        timerTextview = (TextView) findViewById(R.id.timerTextview);
        answerStatus = (TextView) findViewById(R.id.answerStatus);

        resultDisplay = (TextView) findViewById(R.id.resultDisplay);
        //goButton.setVisibility(View.VISIBLE);
        question = (TextView) findViewById(R.id.question);
        gameplayRelativeLayout = (RelativeLayout) findViewById(R.id.gameplayRelativeLayout);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgain = (Button) findViewById(R.id.playAgain);

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        resultDisplay.setText("0 / 0");
        generateQuesiton();
    }

    public void generateQuesiton() {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        question.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationCorrectAnswer = rand.nextInt(4);
        Log.e("chsak11", String.valueOf(locationCorrectAnswer));

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {

            if (i != locationCorrectAnswer) {

                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a + b) {

                    incorrectAnswer = rand.nextInt(41);

                }

                answers.add(incorrectAnswer);
            } else {
                answers.add(a + b);
            }
        }

        button0.setText(String.valueOf(answers.get(0)));
        button1.setText(String.valueOf(answers.get(1)));
        button2.setText(String.valueOf(answers.get(2)));
        button3.setText(String.valueOf(answers.get(3)));
    }

    public void chooseAnswer(View view) {

        if (view.getTag().toString().equals(String.valueOf(locationCorrectAnswer))) {
            answerStatus.setText("Correct!");
            correctCount++;

        } else {
            answerStatus.setText("Wrong!");

        }

        totalCount++;
        answers.clear();
        generateQuesiton();

        resultDisplay.setText(String.valueOf(correctCount)+ " / " + String.valueOf(totalCount));
    }


}
