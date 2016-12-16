package opensource.github.android.client.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import opensource.github.android.client.BuildConfig;
import opensource.github.android.client.data.GitHubInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rajan Maurya on 16/12/16.
 */
public class BaseApiManager {

    public static final String END_POINT = "https://api.github.com";

    private static Retrofit mRetrofit;

    public BaseApiManager() {
        createService();
    }

    public static void init() {

    }

    private static <T> T createApi(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    public static void createService() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addInterceptor(new GitHubInterceptor())
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        init();
    }
}
