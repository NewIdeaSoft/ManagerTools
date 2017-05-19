package com.nisoft.problemlistplus.entity;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/19.
 */

public class Content {
    private String mTitle;
    private String mText;
    private Date mDate;
    private String mAuthor;

    public Content(String mTitle, String mText, Date mDate, String mAuthor) {
        this.mTitle = mTitle;
        this.mText = mText;
        this.mDate = mDate;
        this.mAuthor = mAuthor;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }
}
