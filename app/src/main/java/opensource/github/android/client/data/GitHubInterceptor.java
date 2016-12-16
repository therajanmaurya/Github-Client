package opensource.github.android.client.data;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

public class GitHubInterceptor implements Interceptor {

    public static final String HEADER_ACCEPT = "Accept";
    public static final String CLIENT_ID = "4a9df6f27b10c5ab8f43";
    public static final String CLIENT_SECRET = "785c47918f4a8f04955f938f557bfe55064a882e";

    public GitHubInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request chianrequest = chain.request();
        Builder builder = chianrequest.newBuilder();

        HttpUrl originalHttpUrl = chianrequest.url();
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("client_id", CLIENT_ID)
                .addQueryParameter("client_secret", CLIENT_SECRET)
                .build();
        builder.url(url);

        builder.header(HEADER_ACCEPT, "application/vnd.github.v3+json");

        Request request = builder.build();
        return chain.proceed(request);
    }
}