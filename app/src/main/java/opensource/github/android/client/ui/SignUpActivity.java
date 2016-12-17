package opensource.github.android.client.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import opensource.github.android.client.R;
import opensource.github.android.client.ui.base.GitHubBaseActivity;
import opensource.github.android.client.ui.repository.RepositoryActivity;
import opensource.github.android.client.utils.Constants;

/**
 * Created by Rajan Maurya on 16/12/16.
 */

public class SignUpActivity extends GitHubBaseActivity {

    @BindView(R.id.rl_sign_up)
    RelativeLayout rl_sign_in;

    @BindView(R.id.et_user)
    EditText et_user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_user)
    public void onClickUser() {
        findViewById(R.id.btn_user).setVisibility(View.GONE);
        rl_sign_in.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_sign_in)
    public void onClickSignIn() {
        Intent repository = new Intent(this, RepositoryActivity.class);
        repository.putExtra(Constants.USER_NAME, et_user.getText().toString());
        startActivity(repository);
    }
}
