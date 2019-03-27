package com.twise.marvelquiz;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mQuestionTextView;
    private TextView mScoreTextView;
    private Button mAButton;
    private Button mBButton;
    private Button mCButton;
    private Button mDButton;
    private Button mNextButton;
    private Button mPreviousButton;

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_SCORE = "score";

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_1,"D"),
            new Question(R.string.question_2,"B"),
            new Question(R.string.question_3,"C"),
            new Question(R.string.question_4,"A")
    };

    private int mCurrentIndex = 0;
    private int mScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate() has been called.");

        setContentView(R.layout.activity_main);

        if(savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mScore = savedInstanceState.getInt(KEY_SCORE,0);
        }

        mScoreTextView = (TextView) findViewById(R.id.score_text_view);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = ++mCurrentIndex % mQuestionBank.length;
                updateQuestion();
            }
        });
        updateQuestion();

        mAButton = (Button)findViewById(R.id.a_button);
        mAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                areTheyRight("A");
                mCurrentIndex = ++mCurrentIndex % mQuestionBank.length;
                updateQuestion();
            }
        });

        mBButton = (Button)findViewById(R.id.b_button);
        mBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                areTheyRight("B");
                mCurrentIndex = ++mCurrentIndex % mQuestionBank.length;
                updateQuestion();
            }
        });

        mCButton = (Button)findViewById(R.id.c_button);
        mCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                areTheyRight("C");
                mCurrentIndex = ++mCurrentIndex % mQuestionBank.length;
                updateQuestion();
            }
        });

        mDButton = (Button)findViewById(R.id.d_button);
        mDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                areTheyRight("D");
                mCurrentIndex = ++mCurrentIndex % mQuestionBank.length;
                updateQuestion();
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

        mPreviousButton = (Button) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = --mCurrentIndex % mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart() has been called.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume() has been called.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause() has been called.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop() has been called.");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"onDestroy() has been called.");
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "Saving current index of " + mCurrentIndex);
        outState.putInt(KEY_INDEX, mCurrentIndex);
        outState.putInt(KEY_SCORE, mScore);
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        mScoreTextView.setText(Integer.toString(mScore));
    }

    private void areTheyRight(String userGuess) {
        String answer = mQuestionBank[mCurrentIndex].getAnswer();
        if(userGuess.equals(answer)) {
            Toast t = Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP,0,30);
            t.show();
            mScore++;
        } else {
            Toast t = Toast.makeText(this, R.string.incorrect_toast,Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP,0,20);
            t.show();
            mScore--;
        }
    }
}
