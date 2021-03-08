package test.coding.hosam.newsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import test.coding.hosam.newsapp.R;
import test.coding.hosam.newsapp.callback.IClickListener;
import test.coding.hosam.newsapp.databinding.CategoryItemBinding;
import test.coding.hosam.newsapp.event.CategorySelectedEvent;
import test.coding.hosam.newsapp.model.CategoryModel;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final List<CategoryModel> categoryList;

    public CategoryAdapter(List<CategoryModel> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //CategoryModel model = categoryList.get(position);

        if(categoryList.get(position).isSelected())
            holder.binding.selectedView.setVisibility(View.VISIBLE);
        else
            holder.binding.selectedView.setVisibility(View.GONE);


        holder.binding.txtName.setText(categoryList.get(position).getName());

        holder.setClickListener((view, position1) -> {
            for(CategoryModel selectedCat : categoryList ) {
                if(selectedCat.getName().equals(categoryList.get(position).getName())) {
                    selectedCat.setSelected(true);
                    holder.binding.selectedView.setVisibility(View.VISIBLE);
                } else {
                    selectedCat.setSelected(false);
                    holder.binding.selectedView.setVisibility(View.INVISIBLE);
                }
            }
            EventBus.getDefault().postSticky(new CategorySelectedEvent(categoryList.get(position)));
            notifyItemChanged(position);
            notifyDataSetChanged();
        });

        //holder.binding.txtName.setTextColor(model.getColorText());
        //holder.binding.card.setCardBackgroundColor(model.getColorBackground());

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final CategoryItemBinding binding;
        private IClickListener clickListener;

        public void setClickListener(IClickListener clickListener) {
            this.clickListener = clickListener;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CategoryItemBinding.bind(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClickListener(view, getAdapterPosition());
        }
    }
}
