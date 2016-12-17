package opensource.github.android.client.ui.repository;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import opensource.github.android.client.R;
import opensource.github.android.client.data.models.Repository;

/**
 * Created by Rajan Maurya on 17/12/16.
 */
public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {

    private List<Repository> repositories;

    @Inject
    public RepositoryAdapter() {
        repositories = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repos, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Repository repository = repositories.get(position);
        holder.tv_repo_title.setText(repository.getFullName());
        holder.tv_repo_description.setText(repository.getDescription());
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_user_profile)
        ImageView iv_user_profile;

        @BindView(R.id.tv_repo_name)
        TextView tv_repo_title;

        @BindView(R.id.tv_repo_description)
        TextView tv_repo_description;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}