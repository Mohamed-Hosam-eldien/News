package test.coding.hosam.newsapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import test.coding.hosam.newsapp.R;
import test.coding.hosam.newsapp.callback.IClickListener;
import test.coding.hosam.newsapp.databinding.NewsItemBinding;
import test.coding.hosam.newsapp.model.ArticleModel;
import test.coding.hosam.newsapp.utils.Utils;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<ArticleModel> articleList = new ArrayList<>();
    private final Context context;
    private final RequestOptions requestOptions;


    public NewsAdapter(Context context) {
        this.context = context;
        requestOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, null));
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        ArticleModel model = articleList.get(position);

        Glide.with(context)
                .load(model.getUrlToImage())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.binding.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.binding.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                }).into(holder.binding.image);


        holder.binding.txtTitle.setText(model.getTitle());
        holder.binding.txtDescription.setText(model.getDescription());
        holder.binding.txtSource.setText(model.getSource().getName());

        holder.binding.txtAuthor.setText(ellipsize(model.getAuthor()));
        holder.binding.txtTime.setText(Utils.DateToTimeFormat(model.getPublishedAt()));
        holder.binding.publishAt.setText(Utils.DateFormat(model.getPublishedAt()));

        holder.setClickListener((view, position1) ->
                openWebURL(articleList.get(position).getUrl()));

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void setList(List<ArticleModel> list){
        this.articleList = list;
        notifyDataSetChanged();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final NewsItemBinding binding;
        private IClickListener clickListener;

        public void setClickListener(IClickListener clickListener) {
            this.clickListener = clickListener;
        }

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = NewsItemBinding.bind(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClickListener(view, getAdapterPosition());
        }
    }

    private String ellipsize(String input){

        if(input == null || input.length() < 18)
            return input;

        return input.substring(0,18) + "..." ;

    }

    public void openWebURL( String inURL ) {
        Intent browse = new Intent(Intent.ACTION_VIEW , Uri.parse(inURL));
        context.startActivity( browse );
    }

}
