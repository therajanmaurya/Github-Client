package opensource.github.android.client.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import opensource.github.android.client.data.remote.BaseApiManager;
import opensource.github.android.client.injection.ApplicationContext;


/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    BaseApiManager provideBaseApiManager() {
        return new BaseApiManager();
    }
}
