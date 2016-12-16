package opensource.github.android.client;

import android.app.Application;
import android.content.Context;

import opensource.github.android.client.injection.component.ApplicationComponent;
import opensource.github.android.client.injection.component.DaggerApplicationComponent;
import opensource.github.android.client.injection.module.ApplicationModule;

/**
 * Created by Rajan Maurya on 16/12/16.
 */

public class GitHubApplication extends Application {

    private static GitHubApplication instance;

    ApplicationComponent mApplicationComponent;

    public static Context getContext() {
        return instance;
    }

    public static GitHubApplication get(Context context) {
        return (GitHubApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
