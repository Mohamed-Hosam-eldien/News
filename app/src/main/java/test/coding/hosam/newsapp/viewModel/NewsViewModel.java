package test.coding.hosam.newsapp.viewModel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import test.coding.hosam.newsapp.model.NewsModel;
import test.coding.hosam.newsapp.repository.Repository;


public class NewsViewModel extends ViewModel {

    private final MutableLiveData<NewsModel> newsMutableLiveData = new MutableLiveData<>();
    private final Repository repository;

    @ViewModelInject
    public NewsViewModel(Repository repository) {
        this.repository = repository;
    }

    @SuppressLint("CheckResult")
    public void getNews(String category){
        repository.getNews(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsMutableLiveData::setValue, e -> Log.d("ERROR", e.getMessage()));
    }

    public MutableLiveData<NewsModel> getNewsList(){
        return newsMutableLiveData;
    }

}
