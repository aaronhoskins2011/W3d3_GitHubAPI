package com.example.aaron.w3d3_githubapi;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by aaron on 8/16/17.
 */

public class RepoObject implements Parcelable {
    String name;
    String fullNanme;
    String pushedDate;
    String language;
    

    public RepoObject(String name, String createdDate, String pushedDate, String language) {
        this.name = name;
        this.fullNanme = createdDate;
        this.pushedDate = pushedDate;
        this.language = language;
    }

    protected RepoObject(Parcel in) {
        Log.d("TAG," , "RepoObject: " + in.toString());
        name = in.readString();
        fullNanme = in.readString();
        pushedDate = in.readString();
        language = in.readString();
    }

    public static final Creator<RepoObject> CREATOR = new Creator<RepoObject>() {
        @Override
        public RepoObject createFromParcel(Parcel in) {
            return new RepoObject(in);
        }

        @Override
        public RepoObject[] newArray(int size) {
            return new RepoObject[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedDate() {
        return fullNanme;
    }

    public void setCreatedDate(String createdDate) {
        this.fullNanme = createdDate;
    }

    public String getPushedDate() {
        return pushedDate;
    }

    public void setPushedDate(String pushedDate) {
        this.pushedDate = pushedDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(fullNanme);
        dest.writeString(pushedDate);
        dest.writeString(language);
    }
}
