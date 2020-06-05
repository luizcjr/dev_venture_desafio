package com.luiz.gallery.di;

import android.util.Log;

import com.luiz.gallery.api.constants.Constants;
import com.luiz.gallery.api.services.ApiDataSource;
import com.luiz.gallery.api.services.ApiRepository;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Classe de módulo onde é criado as refêrencias para acessar o retrofit e fazer as requisições
 */
@Module
public class ApiModule {
    private static final int CONNECTION_TIMEOUT = 20 * 1000;

    /**
     * Variável client onde injeto na instância do retrofit o header Authorization com o Client Id
     */
    private OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {

        Request newRequest = chain.request();

        newRequest = chain.request().newBuilder()
                .addHeader("Authorization", Constants.TYPE_AUTHORIZATION + Constants.CLIENT_ID)
                .build();

        return chain.proceed(newRequest);
    }).connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MINUTES).readTimeout(1, TimeUnit.MINUTES).build();

    @Provides
    ApiDataSource provideApi() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiDataSource.class);
    }

    @Provides
    ApiRepository providesService() {
        return new ApiRepository();
    }
}
