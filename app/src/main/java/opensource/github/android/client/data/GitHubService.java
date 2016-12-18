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

    @GET("users/{username}/repos?client_id=4a9df6f27b10c5ab8f43&client_secret=785c47918f4a8f04955f938f557bfe55064a882e")
    Observable<List<Repository>> getUserRepos(
            @Path("username") String userName,
            @Query("page") Integer page);

}
