package test.coding.hosam.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import test.coding.hosam.newsapp.adapter.CategoryAdapter;
import test.coding.hosam.newsapp.adapter.NewsAdapter;
import test.coding.hosam.newsapp.databinding.ActivityMainBinding;
import test.coding.hosam.newsapp.event.CategorySelectedEvent;
import test.coding.hosam.newsapp.model.CategoryModel;
import test.coding.hosam.newsapp.viewModel.NewsViewModel;


@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private final NewsAdapter adapter = new NewsAdapter(this);
    private ActivityMainBinding binding;
    private NewsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        viewModel.getNews("business");

        initData();

        viewModel.getNewsList().observe(this, newsModel -> {
            adapter.setList(newsModel.getArticles());
            binding.progressBar.setVisibility(View.GONE);
        });

    }

    private void initData() {

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);

        List<CategoryModel> categoryList = new ArrayList<>();
        categoryList.add(new CategoryModel("أعمال", "business"));
        categoryList.add(new CategoryModel("ترفية", "entertainment"));
        categoryList.add(new CategoryModel("علوم", "science"));
        categoryList.add(new CategoryModel("رياضة", "sports"));
        categoryList.add(new CategoryModel("صحة", "health"));
        categoryList.add(new CategoryModel("تكنولوجيا", "technology"));

        categoryList.get(0).setSelected(true);
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList);

        binding.recyclerCategory.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, true));
        binding.recyclerCategory.setAdapter(categoryAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);

        EventBus.getDefault().removeAllStickyEvents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onSelectedCategory(CategorySelectedEvent event) {
        if (event.getModel() != null) {
            binding.progressBar.setVisibility(View.VISIBLE);
            viewModel.getNews(event.getModel().getNameCategory());
            binding.recyclerView.smoothScrollToPosition(0);
        }
    }

}