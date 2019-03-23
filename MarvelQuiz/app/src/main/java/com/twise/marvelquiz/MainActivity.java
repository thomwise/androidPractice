package com.twise.marvelquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mQuestionTextView;
    private Button mAButton;
    private Button mBButton;
    private Button mCButton;
    private Button mDButton;
    private Button mNextButton;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_1,"D"),
            new Question(R.string.question_2,"B"),
            new Question(R.string.question_3,"C")
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        mAButton = (Button)findViewById(R.id.a_button);
        mAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                areTheyRight("A");
            }
        });

        mBButton = (Button)findViewById(R.id.b_button);
        mBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                areTheyRight("B");
            }
        });

        mCButton = (Button)findViewById(R.id.c_button);
        mCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                areTheyRight("C");
            }
        });

        mDButton = (Button)findViewById(R.id.d_button);
        mDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                areTheyRight("D");
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = ++mCurrentIndex % mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void areTheyRight(String userGuess) {
        String answer = mQuestionBank[mCurrentIndex].getAnswer();
        if(userGuess.equals(answer)) {
            Toast t = Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP,0,30);
            t.show();
        } else {
            Toast t = Toast.makeText(this, R.string.incorrect_toast,Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP,0,20);
            t.show();
        }
    }
}
