package ru.vetukov.cinema.mycinemaviewers.objects;

import android.os.Parcel;
import android.os.Parcelable;

import ru.vetukov.cinema.mycinemaviewers.adapters.CinemaAdapter;

public class Film implements Parcelable {

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

    public Film(Parcel in) {
        String[] data = new String[7];
        in.readStringArray(data);

        mName           = data[0];
        mLocalName      = data[1];
        mReting         = data[2];
        mCommentCount   = Integer.parseInt(data[3]);
        mDate           = data[4];
        mImageSRC       = data[5];
        mDescription    = data[6];

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {mName
                                           ,mLocalName
                                           ,mReting
                                           ,String.format("%d", mCommentCount)
                                           ,mDate
                                           ,mImageSRC
                                           ,mDescription });
    }

    public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>() {

        @Override
        public Film createFromParcel(Parcel source) {
            return new Film(source);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };
}
