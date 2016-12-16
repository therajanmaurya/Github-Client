package opensource.github.android.client.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import opensource.github.android.client.data.DataManager;
import opensource.github.android.client.data.local.DatabaseHelper;
import opensource.github.android.client.data.local.PreferencesHelper;
import opensource.github.android.client.injection.ApplicationContext;
import opensource.github.android.client.injection.module.ApplicationModule;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    DatabaseHelper databaseHelper();

    DataManager dataManager();

    PreferencesHelper preferencesHelper();

}
