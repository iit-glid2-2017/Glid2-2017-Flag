package com.iit.flagquiz.core;


public class Flag {

    private int mId;
    private int mImgRes;
    private String mTitle;

    public Flag (int id,int imgRes,String title){
        mId =id;
        mImgRes = imgRes;
        mTitle = title;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public int getImgRes() {
        return mImgRes;
    }

    public void setImgRes(int mImgRes) {
        this.mImgRes = mImgRes;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
