package opensource.github.android.client.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import opensource.github.android.client.R;
import opensource.github.android.client.data.models.Repository;
import opensource.github.android.client.ui.base.GitHubBaseActivity;
import opensource.github.android.client.utils.Constants;

/**
 * Created by Rajan Maurya on 17/12/16.
 */

public class DetailRepositoryActivity extends GitHubBaseActivity {

    @BindView(R.id.profileImageView)
    ImageView profileImage;

    @BindView(R.id.tv_title_repo)
    TextView tv_repo_name;

    @BindView(R.id.tv_repo_description)
    TextView tv_repo_description;

    @BindView(R.id.tv_repo_star)
    TextView tv_repo_star;

    @BindView(R.id.tv_repo_watchers)
    TextView tv_repo_watchers;

    @BindView(R.id.tv_owner_id)
    TextView tv_owner_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_repository);

        ButterKnife.bind(this);
        Repository repository = (new Gson()).fromJson(getIntent()
                .getStringExtra(Constants.RESPOSITORY), Repository.class);

        tv_repo_name.setText(repository.getName());
        tv_repo_description.setText(repository.getDescription());
        tv_repo_star.setText(String.valueOf(repository.getStarCount()));
        tv_repo_watchers.setText(String.valueOf(repository.getWatchersCount()));
        tv_owner_id.setText(String.valueOf(repository.getOwner().getId()));

        Picasso.with(this).load(repository.getOwner().getAvatarUrl()).resize(45, 45).centerCrop().into(profileImage);

    }
}
