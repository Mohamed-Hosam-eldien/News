package test.coding.hosam.newsapp.repository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import test.coding.hosam.newsapp.api.NewsInterface;
import test.coding.hosam.newsapp.model.NewsModel;


public class Repository {

    private final NewsInterface newsInterface;

    @Inject
    public Repository(NewsInterface newsInterface) {
        this.newsInterface = newsInterface;
    }


    public Observable<NewsModel> getNews(String category){
        return newsInterface.getNews("eg", category,"f94f9b60dd4b44afb842707ac36e629c");
    }

}
