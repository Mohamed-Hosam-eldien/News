package test.coding.hosam.newsapp.api;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import test.coding.hosam.newsapp.model.NewsModel;

public interface NewsInterface {

    @GET("top-headlines")
    Observable<NewsModel> getNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String api
    );

}
