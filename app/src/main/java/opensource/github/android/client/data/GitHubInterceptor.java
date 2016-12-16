package opensource.github.android.client.data;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

public class GitHubInterceptor implements Interceptor {

    public static final String HEADER_ACCEPT = "Accept";

    public GitHubInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request chianrequest = chain.request();
        Builder builder = chianrequest.newBuilder();

        builder.header(HEADER_ACCEPT, "application/vnd.github.v3+json");

        Request request = builder.build();
        return chain.proceed(request);
    }
}