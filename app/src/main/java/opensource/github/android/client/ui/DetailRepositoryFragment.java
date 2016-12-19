package opensource.github.android.client.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import opensource.github.android.client.R;
import opensource.github.android.client.data.models.Repository;
import opensource.github.android.client.ui.base.GitHubBaseFragment;
import opensource.github.android.client.utils.Constants;

/**
 * Created by Rajan Maurya on 17/12/16.
 */

public class DetailRepositoryFragment extends GitHubBaseFragment {

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

    private Repository repository;

    public static DetailRepositoryFragment newInstance(Repository repository) {
        Bundle arguments = new Bundle();
        DetailRepositoryFragment fragment = new DetailRepositoryFragment();
        arguments.putParcelable(Constants.RESPOSITORY, repository);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            repository = getArguments().getParcelable(Constants.RESPOSITORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_repository, container, false);
        ButterKnife.bind(this, view);

        tv_repo_name.setText(repository.getName());
        tv_repo_description.setText(repository.getDescription());
        tv_repo_star.setText(String.valueOf(repository.getStarCount()));
        tv_repo_watchers.setText(String.valueOf(repository.getWatchersCount()));
        tv_owner_id.setText(String.valueOf(repository.getOwner().getId()));

        Picasso.with(getActivity()).load(repository.getOwner()
                .getAvatarUrl()).resize(45, 45).centerCrop().into(profileImage);

        return view;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                getActivity().supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
