package opensource.github.android.client.ui.repository;

import java.util.List;

import javax.inject.Inject;

import opensource.github.android.client.R;
import opensource.github.android.client.data.DataManager;
import opensource.github.android.client.data.models.Repository;
import opensource.github.android.client.ui.base.BasePresenter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Rajan Maurya on 17/12/16.
 */

public class RepositoryPresenter extends BasePresenter<RepositoryContacts.View>
        implements RepositoryContacts.Presenter {

    private DataManager dataManager;
    private CompositeSubscription subscriptions;

    @Inject
    public RepositoryPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
        subscriptions = new CompositeSubscription();
    }

    @Override
    public void attachView(RepositoryContacts.View mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void loadRepository(String userName) {
        checkViewAttached();
        getMvpView().showProgressBar(true);
        subscriptions.add(dataManager.getUserRepository(userName, 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repository>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showError(R.string.error_fetching_user_repos);
                        getMvpView().showProgressBar(false);
                    }

                    @Override
                    public void onNext(List<Repository> repositories) {
                        getMvpView().showRepository(repositories);
                        getMvpView().showProgressBar(false);
                    }
                })
        );
    }
}
