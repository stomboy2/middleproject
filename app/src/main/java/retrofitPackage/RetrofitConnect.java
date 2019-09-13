package retrofitPackage;

/**
 * Created by stomb on 2018-03-13.
 * retrofit 객체를 한번 만드는
 */


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnect {
    private static Retrofit retrofit = null;

    static public Retrofit getClient() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //logcat 에서 로그를 보기 위해 okHttp를 이용하는 부분 시작
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);    //로그의 레벨을 정읭하는 부분
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        //logcat 에서 로그를 보기 위해 okHttp를 이용하는 부분 종료

        //retrofit 객체 setting 하는부분
        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.98.70/")
                .addConverterFactory(GsonConverterFactory.create(gson)) // convert(넘어온값 변환)은 gson으로 하겠다.
                .client(client)
                .build();

        return retrofit;
    }
}
