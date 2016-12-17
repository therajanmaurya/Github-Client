package opensource.github.android.client.ui.repository;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import opensource.github.android.client.R;
import opensource.github.android.client.data.models.Repository;
import opensource.github.android.client.ui.base.GitHubBaseActivity;
import opensource.github.android.client.ui.base.GitHubBaseFragment;
import opensource.github.android.client.ui.base.RecyclerItemClickListener;

/**
 * Created by Rajan Maurya on 17/12/16.
 */

public class RepositoryFragment extends GitHubBaseFragment implements RepositoryContacts.View,
        RecyclerItemClickListener.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_repository)
    RecyclerView rv_repository;

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    RepositoryAdapter repositoryAdapter;

    @Inject
    RepositoryPresenter repositoryPresenter;

    private View view;

    @Override
    public void onItemClick(View childView, int position) {

    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((GitHubBaseActivity) getActivity()).activityComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_repository, container, false);
        ButterKnife.bind(this, view);
        repositoryPresenter.attachView(this);
        return view;
    }

    @Override
    public void showUserInterface() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_repository.setLayoutManager(layoutManager);
        rv_repository.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), this));
        rv_repository.setHasFixedSize(true);
        rv_repository.setAdapter(repositoryAdapter);
        swipeRefreshLayout.setColorSchemeColors(getActivity()
                .getResources().getIntArray(R.array.swipeRefreshColors));
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void showRepository(List<Repository> repository) {
        repositoryAdapter.setRepositories(repository);
    }

    @Override
    public void showProgressBar(Boolean show) {
        swipeRefreshLayout.setRefreshing(show);
    }

    @Override
    public void showError(int message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        repositoryPresenter.detachView();
    }
}
