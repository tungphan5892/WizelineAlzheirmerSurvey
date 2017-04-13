package com.wizeline.tungphan.wizelinealzheirmersurvey.di.module;

import android.content.Context;



import dagger.Module;

/**
 * Created by Tung Phan on 03/17/2017
 */
@Module
public class NetworkModule {
    private final String TAG = NetworkModule.class.getSimpleName();
    //    private static final String ROOT_URL = "https://wizetwitterproxy.herokuapp.com";
    private static final String ROOT_URL = "https://wzimgur.herokuapp.com";
    private static final int OFFLINE_EXPIRE_TIME_DAY = 7;
    private static final int EXPIRE_TIME_MINS = 2;
    private static final String CACHE_CONTROL = "cache_control";
    private static final int CACHE_SIZE = 10 * 1024 * 1024;
    private static final String HTTP_CACHE = "wizeline_http_cache";
    private Context context;

    public NetworkModule(Context context) {
        this.context = context;
    }

//    @Provides
//    @Singleton
//    Retrofit getRetrofitInstance() {
//        return new Retrofit.Builder()
//                .baseUrl(ROOT_URL)
//                .client(provideOkHttpClient())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//    }

//    @Provides
//    @Singleton
//    OkHttpClient provideOkHttpClient() {
//        return new OkHttpClient.Builder()
//                .addInterceptor(provideOfflineCacheInterceptor())
//                .addNetworkInterceptor(provideCacheInterceptor())
//                .cache(provideCache())
//                .build();
//    }

//    @Provides
//    @Singleton
//    Cache provideCache() {
//        Cache cache = null;
//        try {
//            cache = new Cache(new File(context.getCacheDir(), HTTP_CACHE), CACHE_SIZE);
//        } catch (Exception e) {
//            Log.e(TAG, "Could not create Cache!");
//        }
//        return cache;
//    }

//    public static Interceptor provideCacheInterceptor() {
//        return chain -> {
//            Response response = chain.proceed(chain.request());
//            CacheControl cacheControl = new CacheControl.Builder()
//                    .maxAge(EXPIRE_TIME_MINS, TimeUnit.MINUTES)
//                    .build();
//            return response.newBuilder()
//                    .header(CACHE_CONTROL, cacheControl.toString())
//                    .build();
//        };
//    }
//
//    public Interceptor provideOfflineCacheInterceptor() {
//        return chain -> {
//            Request request = chain.request();
//            if (!WizelineApp.getInstance().isConnectToNetwork()) {
//                CacheControl cacheControl = new CacheControl.Builder()
//                        .maxStale(OFFLINE_EXPIRE_TIME_DAY, TimeUnit.DAYS)
//                        .build();
//                request = request.newBuilder()
//                        .cacheControl(cacheControl)
//                        .build();
//            }
//            return chain.proceed(request);
//        };
//    }

//    @Provides
//    @Singleton
//    public Service providesService() {
//        return new Service(providesNetworkService());
//    }
//
//    @Provides
//    @Singleton
//    public NetworkService providesNetworkService() {
//        return getRetrofitInstance().create(NetworkService.class);
//    }
}
