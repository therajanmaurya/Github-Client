package opensource.github.android.client.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;

import opensource.github.android.client.injection.ApplicationContext;

@Singleton
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "github_app_pref_file";

    private final SharedPreferences mPref;
    private final Gson mGson;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        mGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz")
                .create();
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public int getInt(String preferenceKey, int preferenceDefaultValue) {
        return mPref.getInt(preferenceKey, preferenceDefaultValue);
    }

    public void putInt(String preferenceKey, int preferenceValue) {
        mPref.edit().putInt(preferenceKey, preferenceValue).apply();
    }

    public long getLong(String preferenceKey, long preferenceDefaultValue) {
        return mPref.getLong(preferenceKey, preferenceDefaultValue);
    }

    public void putLong(String preferenceKey, long preferenceValue) {
        mPref.edit().putLong(preferenceKey, preferenceValue).apply();
    }

    public float getFloat(String preferenceKey, float preferenceDefaultValue) {
        return mPref.getFloat(preferenceKey, preferenceDefaultValue);
    }

    public void putFloat(String preferenceKey, float preferenceValue) {
        mPref.edit().putFloat(preferenceKey, preferenceValue).apply();
    }


    public boolean getBoolean(String preferenceKey, boolean preferenceDefaultValue) {
        return mPref.getBoolean(preferenceKey, preferenceDefaultValue);
    }

    public void putBoolean(String preferenceKey, boolean preferenceValue) {
        mPref.edit().putBoolean(preferenceKey, preferenceValue).apply();
    }

    public String getString(String preferenceKey, String preferenceDefaultValue) {
        return mPref.getString(preferenceKey, preferenceDefaultValue);
    }

    public void putString(String preferenceKey, String preferenceValue) {
        mPref.edit().putString(preferenceKey, preferenceValue).apply();
    }
}
