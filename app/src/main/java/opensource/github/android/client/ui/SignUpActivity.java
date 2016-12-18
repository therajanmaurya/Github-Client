package opensource.github.android.client.ui;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
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

    @BindView(R.id.card_view)
    CardView cv_sign_in;

    @BindView(R.id.btn_sign_in)
    Button btn_sign_in;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.btn_user)
    public void onClickUser() {
        findViewById(R.id.btn_user).setVisibility(View.GONE);
        rl_sign_in.setVisibility(View.VISIBLE);

        Animation animation = new TranslateAnimation(0, 0 , 300, 0);
        animation.setDuration(400);
        animation.setFillAfter(true);
        cv_sign_in.startAnimation(animation);
        cv_sign_in.setVisibility(View.VISIBLE);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ObjectAnimator flipanimation = ObjectAnimator.ofFloat(btn_sign_in, "rotationY", 0.0f, 360f);
                flipanimation.setDuration(500);
                flipanimation.setRepeatCount(1);
                flipanimation.setInterpolator(new AccelerateDecelerateInterpolator());
                flipanimation.start();
                btn_sign_in.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @OnClick(R.id.btn_sign_in)
    public void onClickSignIn() {
        Intent repository = new Intent(this, RepositoryActivity.class);
        repository.putExtra(Constants.USER_NAME, et_user.getText().toString());
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                findViewById(R.id.btn_sign_in), Constants.ANIMATION_PROFILE);
        startActivity(repository, options.toBundle());
    }

}
