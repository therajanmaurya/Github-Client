package opensource.github.android.client.ui.repository;

import android.os.Bundle;
import android.support.annotation.Nullable;

import opensource.github.android.client.R;
import opensource.github.android.client.ui.base.GitHubBaseActivity;
import opensource.github.android.client.utils.Constants;

/**
 * Created by Rajan Maurya on 17/12/16.
 */

public class RepositoryActivity extends GitHubBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        String userName = getIntent().getExtras().getString(Constants.USER_NAME);
        replaceFragment(RepositoryFragment.newInstance(userName), false, R.id.container);
    }
}
