package opensource.github.android.client.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rajan Maurya on 16/12/16.
 */

public class Repository implements Parcelable {

    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("full_name")
    String fullName;

    @SerializedName("owner")
    Owner owner;

    @SerializedName("description")
    String description;

    @SerializedName("url")
    String url;

    @SerializedName("watchers_count")
    Integer watchersCount;

    @SerializedName("language")
    String language;

    @SerializedName("forks_count")
    Integer forksCount;

    @SerializedName("forks")
    Integer forks;

    @SerializedName("watchers")
    Integer watchers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(Integer watchersCount) {
        this.watchersCount = watchersCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getForksCount() {
        return forksCount;
    }

    public void setForksCount(Integer forksCount) {
        this.forksCount = forksCount;
    }

    public Integer getForks() {
        return forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public Integer getWatchers() {
        return watchers;
    }

    public void setWatchers(Integer watchers) {
        this.watchers = watchers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.fullName);
        dest.writeParcelable(this.owner, flags);
        dest.writeString(this.description);
        dest.writeString(this.url);
        dest.writeValue(this.watchersCount);
        dest.writeString(this.language);
        dest.writeValue(this.forksCount);
        dest.writeValue(this.forks);
        dest.writeValue(this.watchers);
    }

    public Repository() {
    }

    protected Repository(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.fullName = in.readString();
        this.owner = in.readParcelable(Owner.class.getClassLoader());
        this.description = in.readString();
        this.url = in.readString();
        this.watchersCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.language = in.readString();
        this.forksCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.forks = (Integer) in.readValue(Integer.class.getClassLoader());
        this.watchers = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Repository> CREATOR =
            new Parcelable.Creator<Repository>() {
                @Override
                public Repository createFromParcel(Parcel source) {
                    return new Repository(source);
                }

                @Override
                public Repository[] newArray(int size) {
                    return new Repository[size];
                }
            };
}
