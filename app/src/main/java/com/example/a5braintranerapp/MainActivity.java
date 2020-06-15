package com.example.a5braintranerapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goBtn, btn1, btn2, btn3, btn4, playAgainBtn;
    TextView timeTextView, scoreTextView, examTextView, winTextView;
    CountDownTimer timer;
    boolean isFinished = false;
    int rightAnswer = 0, wrongAnswer = 0, answer = 0;

    public void makeQuestion() {
        int firstNumber = new Random().nextInt(90) + 10;
        int secondNumber = new Random().nextInt(90) + 10;
        answer = firstNumber + secondNumber;
        examTextView.setText(firstNumber + " + " + secondNumber);

    }

    public void setValueOfButtons() {

        int first = new Random().nextInt(90) + 10;
        int second = new Random().nextInt(90) + 10;
        int third = new Random().nextInt(90) + 10;
        int fourth = answer;

        int rand = new Random().nextInt(4);

        if (rand == 0) {
            btn1.setText(String.valueOf(fourth));
            btn2.setText(String.valueOf(second));
            btn3.setText(String.valueOf(third));
            btn4.setText(String.valueOf(first));
        } else if (rand == 1) {
            btn1.setText(String.valueOf(first));
            btn2.setText(String.valueOf(fourth));
            btn3.setText(String.valueOf(third));
            btn4.setText(String.valueOf(second));
        } else if (rand == 2) {
            btn1.setText(String.valueOf(first));
            btn2.setText(String.valueOf(second));
            btn3.setText(String.valueOf(fourth));
            btn4.setText(String.valueOf(third));
        } else {
            btn1.setText(String.valueOf(first));
            btn2.setText(String.valueOf(second));
            btn3.setText(String.valueOf(third));
            btn4.setText(String.valueOf(fourth));
        }
    }

    public void itemClicked(View view) {
        Button clicked = null;
        if (view instanceof Button) {
            clicked = (Button) view;
        }
        if (clicked.getText().toString().equals(String.valueOf(answer))){
            scoreTextView.setText((++rightAnswer) + "/" + wrongAnswer);
            Toast.makeText(this, "Right !!!", Toast.LENGTH_SHORT).show();
            makeQuestion();
            setValueOfButtons();
        } else {
            scoreTextView.setText(rightAnswer + "/" + (++wrongAnswer));
            Toast.makeText(this, "Wrong !!!", Toast.LENGTH_SHORT).show();
            makeQuestion();
            setValueOfButtons();
        }

    }


    public void init() {

        goBtn = findViewById(R.id.goButton);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        playAgainBtn = findViewById(R.id.playAgainButton);

        timeTextView = findViewById(R.id.timeTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        examTextView = findViewById(R.id.qsTextView);
        winTextView = findViewById(R.id.winTextView);
    }

    public void initUI() {

        btn1.setVisibility(View.GONE);
        btn2.setVisibility(View.GONE);
        btn3.setVisibility(View.GONE);
        btn4.setVisibility(View.GONE);

        timeTextView.setVisibility(View.GONE);
        scoreTextView.setVisibility(View.GONE);
        scoreTextView.setText(rightAnswer + "/" + wrongAnswer);
        examTextView.setVisibility(View.GONE);
        winTextView.setVisibility(View.GONE);

        playAgainBtn.setVisibility(View.GONE);
        goBtn.setVisibility(View.VISIBLE);
        goBtn.setTranslationY(-1500);
        goBtn.animate().translationYBy(1500).rotation(3600).setDuration(3000).start();

    }

    public void downToUpAnimateBtn(View view) {
        Button test = (Button) view;
        test.animate().translationX(3000).rotation(-3600).setDuration(3000).start();
    }


    public void goBtnOnClicked(View view) {
        goBtn.setVisibility(View.GONE);

        btn1.setVisibility(View.VISIBLE);
        btn2.setVisibility(View.VISIBLE);
        btn3.setVisibility(View.VISIBLE);
        btn4.setVisibility(View.VISIBLE);

        timeTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        examTextView.setVisibility(View.VISIBLE);

        makeQuestion();
        setValueOfButtons();

        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int x = (int) (millisUntilFinished)/1000;
                timeTextView.setText(x + " S");
            }

            @Override
            public void onFinish() {
                isFinished = true;
                timer.cancel();
                winTextView.setText("right Answers : " + rightAnswer + "\n" + "Wrong Answers : " + wrongAnswer);
                winTextView.setVisibility(View.VISIBLE);
                playAgainBtn.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void playAgainClicked(View view){
        isFinished = false;
        initUI();
        answer = 0;
        rightAnswer = 0;
        wrongAnswer = 0;
        scoreTextView.setText(0 + "/" + 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initUI();
    }

}
