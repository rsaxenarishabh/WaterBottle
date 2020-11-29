package com.waterbottle.Fragment.dashboard;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.waterbottle.R;
import com.waterbottle.utils.Constants;

import java.util.List;

public class HomeItemAdapter extends RecyclerView.Adapter<HomeItemAdapter.MyViewHolder> {

    onItemClickData onItemClickData;

    public void setOnItemClickData(HomeItemAdapter.onItemClickData onItemClickData) {
        this.onItemClickData = onItemClickData;
    }

    Context context;
    List<HomeResponse.Data.Category> categories;

    public HomeItemAdapter(Context context, List<HomeResponse.Data.Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public HomeItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_list_item, parent, false);
        return new HomeItemAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeItemAdapter.MyViewHolder holder, int position) {
        final HomeResponse.Data.Category category = categories.get(position);
        holder.textViewItemName.setText("" + category.getName());
        Log.d("GroceryItemAdapter", "onBindViewHolder: " + Constants.BASE_URL_IMAGE + category.getImage());
        if (!TextUtils.isEmpty(category.getImage())) {
            Glide.with(context)
                    .load(Uri.parse(Constants.BASE_URL_IMAGE + category.getImage()))
                    .addListener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    }).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(true)

                    .into(holder.imageViewItem);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickData != null) {
                    onItemClickData.onCategoryDataUsingId(category.getId(),category.getName());
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        if (categories != null && categories.size() > 0)
            return categories.size();
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewItem;
        private TextView textViewItemName;
        private CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewItem = itemView.findViewById(R.id.imghomeShop);
            textViewItemName = itemView.findViewById(R.id.txthomeTitle);
            cardView = itemView.findViewById(R.id.cardView);


        }
    }

    public interface onItemClickData {
        public void onCategoryDataUsingId(int id,String categoryName);
    }

}
