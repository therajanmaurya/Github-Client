package opensource.github.android.client.data;

import java.util.List;

import opensource.github.android.client.data.models.Repository;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Rajan Maurya on 16/12/16.
 */

public interface GitHubService {

    @GET("users/{username}/repos")
    Observable<List<Repository>> getUserRepos(
            @Path("username") String userName,
            @Query("page") Integer page);

}
