package opensource.github.android.client.ui.repository;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
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
import opensource.github.android.client.ui.base.RecyclerItemClickListener;
import opensource.github.android.client.utils.Constants;

/**
 * Created by Rajan Maurya on 17/12/16.
 */

public class RepositoryActivity extends GitHubBaseActivity implements RepositoryContacts.View,
        RecyclerItemClickListener.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_repository)
    RecyclerView rv_repository;

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    RepositoryAdapter repositoryAdapter;

    @Inject
    RepositoryPresenter repositoryPresenter;

    private String userName;
    private LinearLayoutManager layoutManager;
    private List<Repository> repositoryList;

    @Override
    public void onItemClick(View childView, int position) {
        Intent intent = new Intent(this, DetailRepositoryActivity.class);
        intent.putExtra(Constants.RESPOSITORY, (new Gson()).toJson(repositoryList.get(position)));
        startActivity(intent);
    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.fragment_repository);

        ButterKnife.bind(this);
        repositoryPresenter.attachView(this);

        repositoryList = new ArrayList<>();
        userName = getIntent().getExtras().getString(Constants.USER_NAME);

        showUserInterface();
        repositoryPresenter.loadRepository(userName, 1);

        rv_repository.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                repositoryPresenter.loadRepository(userName, ++page);
            }
        });
    }

    @Override
    public void showUserInterface() {
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_repository.setLayoutManager(layoutManager);
        rv_repository.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
        rv_repository.setHasFixedSize(true);
        rv_repository.setAdapter(repositoryAdapter);
        swipeRefreshLayout.setColorSchemeColors(this
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        repositoryPresenter.detachView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
