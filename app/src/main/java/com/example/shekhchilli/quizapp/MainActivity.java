package com.example.shekhchilli.quizapp;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String DEBUG_TAG = "DbuGGing";

    private static final int CORRECT_ANSWER_MARKS = 10;
    private static final String CORRECT_ANSWER_Q1 = "1940";
    private static final String CORRECT_ANSWER_Q2 = "Defence Day";
    private static final String CORRECT_ANSWER_Q3 = "1948";
    private static final String CORRECT_ANSWER_Q4 = "Quaid-e-Azam Muhammad Ali Jinnah";
    private static final String CORRECT_ANSWER_Q5 = "Sialkot";

    String answer = null;
    int marks = 0;
    int questionNimber = 0;
    TextView quesTextview;
    RadioButton R1;
    RadioButton R2;
    RadioButton R3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
        setsVisibility();

        NextClickListener();

    }

    private void setsVisibility() {
        quesTextview = (TextView) findViewById(R.id.question_textview);
        R1 = (RadioButton) findViewById(R.id.R1);
        R2 = (RadioButton) findViewById(R.id.R2);
        R3 = (RadioButton) findViewById(R.id.R3);
        quesTextview.setVisibility(View.INVISIBLE);
        R1.setVisibility(View.INVISIBLE);
        R2.setVisibility(View.INVISIBLE);
        R3.setVisibility(View.INVISIBLE);
    }

    public void NextClickListener() {



        final Button button = (Button) findViewById(R.id.next_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quesTextview.setVisibility(View.VISIBLE);
                R1.setVisibility(View.VISIBLE);
                R2.setVisibility(View.VISIBLE);
                R3.setVisibility(View.VISIBLE);

                questionNimber++;

                switch (questionNimber) {
                    case 1:{
                        button.setText("Next");
                        Displayquestions(R.string.Q1, R.string.Q1R1, R.string.Q1R2, R.string.Q1R3);
                        break;
                    }
                    case 2: {
                        marks += CalculateResult(CORRECT_ANSWER_Q1);
                        Displayquestions(R.string.Q2, R.string.Q2R1, R.string.Q2R2, R.string.Q2R3);
                        Log.e(DEBUG_TAG, answer + " " + marks);
                        break;
                    }
                    case 3: {
                        marks += CalculateResult(CORRECT_ANSWER_Q2);
                        Displayquestions(R.string.Q3, R.string.Q3R1, R.string.Q3R2, R.string.Q3R3);
                        Log.e(DEBUG_TAG, answer + " " + marks);
                        break;
                    }
                    case 4: {
                        marks += CalculateResult(CORRECT_ANSWER_Q3);
                        Displayquestions(R.string.Q4, R.string.Q4R1, R.string.Q4R2, R.string.Q4R3);
                        Log.e(DEBUG_TAG, answer + " " + marks);
                        break;
                    }
                    case 5: {
                        marks += CalculateResult(CORRECT_ANSWER_Q4);
                        Log.e(DEBUG_TAG, answer + " " + marks);
                        Displayquestions(R.string.Q5, R.string.Q5R1, R.string.Q5R2, R.string.Q5R3);
                        button.setText("Submit");
                        Log.e(DEBUG_TAG, answer + " " + marks);
                        break;
                    }
                    default: {
                        setsVisibility();
                        if(questionNimber == 6) {
                            marks += CalculateResult(CORRECT_ANSWER_Q5);
                        }
                        Toast.makeText(MainActivity.this, "You got " + marks + " marks out of 50", Toast.LENGTH_LONG).show();
                        questionNimber = 0;
                        marks = 0;
                        button.setText("Start");
                        break;
                    }
                }
            }
        });
    }


    public void Displayquestions(int quesId, int R1Id, int R2Id, int R3Id) {

        quesTextview = (TextView) findViewById(R.id.question_textview);
        R1 = (RadioButton) findViewById(R.id.R1);
        R2 = (RadioButton) findViewById(R.id.R2);
        R3 = (RadioButton) findViewById(R.id.R3);


        R1.setChecked(false);
        R2.setChecked(false);
        R3.setChecked(false);


        quesTextview.setText(getString(quesId));

        R1.setText(getString(R1Id));
        R2.setText(getString(R2Id));
        R3.setText(getString(R3Id));


    }

    public int CalculateResult(String correctAnswer) {


        int mark = 0;

        if (R1.isChecked()) {
            answer =(String) R1.getText();
        }else if (R2.isChecked()) {
            answer = (String)R2.getText();
        }else if (R3.isChecked()) {
            answer = (String) R3.getText();
        }else{
            answer = null;
        }

        if (answer != null && answer.equals(correctAnswer)) {
            mark += CORRECT_ANSWER_MARKS;
        }else if(answer == null){
            mark = 0;
        }

        return mark;


    }
}




