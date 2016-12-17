package opensource.github.android.client.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rajan Maurya on 17/12/16.
 */

public class Owner implements Parcelable {

    @SerializedName("login")
    String login;

    @SerializedName("id")
    Integer id;

    @SerializedName("avatar_url")
    String avatarUrl;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.login);
        dest.writeValue(this.id);
        dest.writeString(this.avatarUrl);
    }

    public Owner() {
    }

    protected Owner(Parcel in) {
        this.login = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.avatarUrl = in.readString();
    }

    public static final Parcelable.Creator<Owner> CREATOR = new Parcelable.Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel source) {
            return new Owner(source);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };
}
