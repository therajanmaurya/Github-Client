package opensource.github.android.client.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_repository);

        ButterKnife.bind(this);
        Repository repository = (new Gson()).fromJson(getIntent()
                .getStringExtra(Constants.RESPOSITORY), Repository.class);


        Picasso.with(this).load(repository.getOwner().getAvatarUrl()).resize(30, 30).centerCrop().into(profileImage);

    }
}
