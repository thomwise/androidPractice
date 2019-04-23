package com.twise.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private String mSeverity;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
        mTitle = "";
        mSolved = false;
        mSeverity = "Misdemeanor";
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public String getSeverity() {
        return mSeverity;
    }

    public void setSeverity(String severity) {
        mSeverity = severity;
    }

    public void setSolved(boolean solved) {
        this.mSolved = solved;
    }
}
