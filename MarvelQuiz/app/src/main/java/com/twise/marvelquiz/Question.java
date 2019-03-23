package com.twise.marvelquiz;

public class Question {

    private int mTextResId;
    private String mAnswer;

    public Question(int textResId, String answer) {
        mTextResId = textResId;
        mAnswer = answer;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public void setAnswer(String answer) {
        mAnswer = answer;
    }

    public String getAnswer() {
        return mAnswer;
    }
}
