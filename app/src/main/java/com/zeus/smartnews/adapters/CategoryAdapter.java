package com.zeus.smartnews.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;
import com.zeus.smartnews.R;
import com.zeus.smartnews.WebPageActivity;
import com.zeus.smartnews.models.ArticleModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context mContext;
    private ArrayList<ArticleModel> articleModelsList;


    public CategoryAdapter(Context mContext, ArrayList<ArticleModel> articleModelsList) {
        this.mContext = mContext;
        this.articleModelsList = articleModelsList;
    }

    @NotNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder( @NotNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(mContext).inflate(R.layout.category_items_layout, parent, false));
    }

    @Override
    public void onBindViewHolder( @NotNull CategoryAdapter.CategoryViewHolder holder, int position) {
        ArticleModel myArticleModel = articleModelsList.get(position);

        holder.categoryNewsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebPageActivity.class);
                intent.putExtra("url", myArticleModel.getUrl());
                mContext.startActivity(intent);
                Animatoo.animateSlideLeft(mContext);

            }
        });

        if (myArticleModel.getPublishedAt() != null) {
            holder.categoryNewsDate.setText(myArticleModel.getPublishedAt());
        } else {
            holder.categoryNewsDate.setText("Date");
        }
        if (myArticleModel.getAuthor() != null) {
            holder.categoryNewsAuthor.setText(myArticleModel.getAuthor());
        } else  {
            holder.categoryNewsAuthor.setText("Author");
        }
        if (myArticleModel.getTitle() != null) {
            holder.categoryNewsTitle.setText(myArticleModel.getTitle());
        } else {
            holder.categoryNewsTitle.setText("Title");
        }
        if (myArticleModel.getUrlToImage() != null) {
            holder.noImageText2.setVisibility(View.GONE);
            Picasso.get().load(myArticleModel.getUrlToImage()).into(holder.categoryNewsImage);
        } else {
            holder.noImageText2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return articleModelsList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView categoryNewsCard;
        ImageView categoryNewsImage;
        TextView categoryNewsTitle, categoryNewsAuthor, categoryNewsDate, noImageText2;

        public CategoryViewHolder( @NotNull View itemView) {
            super(itemView);

            categoryNewsCard = itemView.findViewById(R.id.categoryNewsCard);
            categoryNewsImage = itemView.findViewById(R.id.categoryNewsImage);
            categoryNewsTitle = itemView.findViewById(R.id.categoryNewsTitle);
            noImageText2 = itemView.findViewById(R.id.noImageText2);
            categoryNewsAuthor = itemView.findViewById(R.id.categoryNewsAuthor);
            categoryNewsDate = itemView.findViewById(R.id.categoryNewsDate);

        }
    }
}
