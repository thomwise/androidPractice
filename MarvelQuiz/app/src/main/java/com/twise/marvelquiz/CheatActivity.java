package com.twise.marvelquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String KEY_ANSWER = "com.twise.android.marvelquiz.answer";
    private static final String KEY_CHEATER = "com.twise.android.marvelquiz.cheater";

    private String answer;

    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    public static Intent newIntent(Context c, String answer) {
        Intent i = new Intent(c, CheatActivity.class);
        i.putExtra(KEY_ANSWER, answer);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        answer = getIntent().getStringExtra(KEY_ANSWER);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer.equals("A")) {
                    mAnswerTextView.setText(R.string.a_button);
                } else if(answer.equals("B")) {
                    mAnswerTextView.setText(R.string.b_button);
                } else if(answer.equals("C")) {
                    mAnswerTextView.setText(R.string.c_button);
                } else if(answer.equals("D")) {
                    mAnswerTextView.setText(R.string.d_button);
                }
                setCheaterResult(true);
            }
        });

    }

    private void setCheaterResult(boolean areTheyACheater) {
        Intent data = new Intent();
        data.putExtra(KEY_CHEATER, areTheyACheater);
        setResult(RESULT_OK, data);
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(KEY_CHEATER, true);
    }
}
