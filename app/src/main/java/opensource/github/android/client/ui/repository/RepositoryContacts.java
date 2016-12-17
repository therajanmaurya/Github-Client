package opensource.github.android.client.ui.repository;

import java.util.List;

import opensource.github.android.client.data.models.Repository;
import opensource.github.android.client.ui.base.MvpView;

/**
 * Created by Rajan Maurya on 17/12/16.
 */

public interface RepositoryContacts {

    interface View extends MvpView {

        void showUserInterface();

        void showRepository(List<Repository> repositories);

        void showProgressBar(Boolean show);

        void showError(int message);
    }

    interface Presenter {

        void loadRepository(String userName, int page);
    }
}
