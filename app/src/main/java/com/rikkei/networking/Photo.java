package com.rikkei.networking;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Photo implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("albumId")
    private int albumId;
    @SerializedName("title")
    private String title;
    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;
    @SerializedName("url")
    private String url;

    public Photo(int id, int albumId, String title, String thumbnailUrl, String url) {
        this.id = id;
        this.albumId = albumId;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.url = url;
    }

    protected Photo(Parcel in) {
        id = in.readInt();
        albumId = in.readInt();
        title = in.readString();
        thumbnailUrl = in.readString();
        url = in.readString();
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public int getId() {
        return id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(albumId);
        dest.writeString(title);
        dest.writeString(thumbnailUrl);
        dest.writeString(url);
    }
}
