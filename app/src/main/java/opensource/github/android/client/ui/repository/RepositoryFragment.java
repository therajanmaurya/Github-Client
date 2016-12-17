package opensource.github.android.client.ui.repository;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import opensource.github.android.client.R;
import opensource.github.android.client.data.models.Repository;
import opensource.github.android.client.ui.DetailRepositoryActivity;
import opensource.github.android.client.ui.base.EndlessRecyclerViewScrollListener;
import opensource.github.android.client.ui.base.GitHubBaseActivity;
import opensource.github.android.client.ui.base.GitHubBaseFragment;
import opensource.github.android.client.ui.base.RecyclerItemClickListener;
import opensource.github.android.client.utils.Constants;

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
    private String userName;
    private LinearLayoutManager layoutManager;
    private List<Repository> repositoryList;

    @Override
    public void onItemClick(View childView, int position) {
        Intent intent = new Intent(getActivity(), DetailRepositoryActivity.class);
        intent.putExtra(Constants.RESPOSITORY, (new Gson()).toJson(repositoryList.get(position)));
        startActivity(intent);
    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }

    public static RepositoryFragment newInstance(String userName) {
        Bundle arguments = new Bundle();
        RepositoryFragment fragment = new RepositoryFragment();
        arguments.putString(Constants.USER_NAME, userName);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((GitHubBaseActivity) getActivity()).activityComponent().inject(this);
        repositoryList = new ArrayList<>();
        if (getArguments() != null) {
            userName = getArguments().getString(Constants.USER_NAME, userName);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_repository, container, false);
        ButterKnife.bind(this, view);
        repositoryPresenter.attachView(this);
        showUserInterface();
        repositoryPresenter.loadRepository(userName, 1);

        rv_repository.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                //repositoryPresenter.loadRepository(userName, page);
            }
        });
        return view;
    }

    @Override
    public void showUserInterface() {
        layoutManager = new LinearLayoutManager(getActivity());
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
        repositoryPresenter.loadRepository(userName, 1);
    }

    @Override
    public void showRepository(List<Repository> repository) {
        this.repositoryList.addAll(repository);
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
