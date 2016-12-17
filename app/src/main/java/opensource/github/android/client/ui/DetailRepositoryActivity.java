package opensource.github.android.client.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import opensource.github.android.client.R;
import opensource.github.android.client.data.models.Repository;
import opensource.github.android.client.ui.base.GitHubBaseActivity;
import opensource.github.android.client.utils.Constants;

/**
 * Created by Rajan Maurya on 17/12/16.
 */

public class DetailRepositoryActivity extends GitHubBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_repository);

        Repository repository = (new Gson()).fromJson(getIntent()
                .getStringExtra(Constants.RESPOSITORY), Repository.class);



    }
}
