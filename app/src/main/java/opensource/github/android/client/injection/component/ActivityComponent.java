package opensource.github.android.client.injection.component;

import dagger.Subcomponent;
import opensource.github.android.client.injection.PerActivity;
import opensource.github.android.client.injection.module.ActivityModule;


/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

}
