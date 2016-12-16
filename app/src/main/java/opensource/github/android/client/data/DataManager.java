package opensource.github.android.client.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import opensource.github.android.client.data.local.DatabaseHelper;
import opensource.github.android.client.data.local.PreferencesHelper;
import opensource.github.android.client.data.remote.BaseApiManager;

/**
 * Created by Rajan Maurya on 16/12/16.
 */
@Singleton
public class DataManager {

    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final BaseApiManager mBaseApiManager;

    @Inject
    public DataManager(BaseApiManager baseApiManager,
            DatabaseHelper databaseHelper, PreferencesHelper preferencesHelper) {
        mBaseApiManager = baseApiManager;
        mDatabaseHelper = databaseHelper;
        mPreferencesHelper = preferencesHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }
}
