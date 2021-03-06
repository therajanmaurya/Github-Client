package opensource.github.android.client.injection.component;

import dagger.Subcomponent;
import opensource.github.android.client.injection.PerActivity;
import opensource.github.android.client.injection.module.ActivityModule;
import opensource.github.android.client.ui.repository.RepositoryActivity;
import opensource.github.android.client.ui.repository.RepositoryFragment;


/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(RepositoryActivity repositoryFragment);

    void inject(RepositoryFragment repositoryFragment);
}
