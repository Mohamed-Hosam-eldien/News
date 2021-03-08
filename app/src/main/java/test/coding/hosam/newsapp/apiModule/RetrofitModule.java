package test.coding.hosam.newsapp.apiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import test.coding.hosam.newsapp.api.NewsInterface;

@Module
@InstallIn(ApplicationComponent.class)
public class RetrofitModule {

    public static final String BASE_URL = "http://newsapi.org/v2/";


    @Provides
    @Singleton
    public static NewsInterface builder(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(NewsInterface.class);
    }


}
