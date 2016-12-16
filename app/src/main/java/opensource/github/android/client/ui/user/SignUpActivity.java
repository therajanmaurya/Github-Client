package opensource.github.android.client.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;

import opensource.github.android.client.R;
import opensource.github.android.client.ui.base.GitHubBaseActivity;

/**
 * Created by Rajan Maurya on 16/12/16.
 */

public class SignUpActivity extends GitHubBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
}
