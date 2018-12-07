package ru.vetukov.kino.mykinoinfo.objects;

public class Film {

    private String  mLocalName;
    private String  mName;
    private String  mDate;
    private String  mImageSRC;
    private String  mDescription;
    private String  mReting;
    private int     mCommentCount;

    public Film(String localName, String name, String reting, int commentCount) {
        mLocalName = localName;
        mName = name;
        mReting = reting;
        mCommentCount = commentCount;
    }

    public Film(String localName, String name, String date, String imageSRC, String description, String reting, int commentCount) {
        this(localName, name, reting, commentCount);
        mDate = date;
        mImageSRC = imageSRC;
        mDescription = description;
    }

    public String getLocalName() {
        return mLocalName;
    }

    public void setLocalName(String localName) {
        mLocalName = localName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getImageSRC() {
        return mImageSRC;
    }

    public void setImageSRC(String imageSRC) {
        mImageSRC = imageSRC;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getReting() {
        return mReting;
    }

    public void setReting(String reting) {
        mReting = reting;
    }

    public String getCommentCount() {
        return String.format("%d", mCommentCount);
    }

    public void setCommentCount(int commentCount) {
        mCommentCount = commentCount;
    }

}
