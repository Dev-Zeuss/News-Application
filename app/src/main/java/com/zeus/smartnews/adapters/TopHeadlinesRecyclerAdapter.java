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

public class TopHeadlinesRecyclerAdapter extends RecyclerView.Adapter<TopHeadlinesRecyclerAdapter.TopHeadlinesViewHolder> {

    Context context;
    ArrayList<ArticleModel> articleModelArrayList;

    public TopHeadlinesRecyclerAdapter(Context context, ArrayList<ArticleModel> articleModelArrayList) {
        this.context = context;
        this.articleModelArrayList = articleModelArrayList;
    }

    @NotNull
    @Override
    public TopHeadlinesRecyclerAdapter.TopHeadlinesViewHolder onCreateViewHolder( @NotNull ViewGroup parent, int viewType) {
        return new TopHeadlinesViewHolder(LayoutInflater.from(context).inflate(R.layout.top_headlines_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder( @NotNull TopHeadlinesRecyclerAdapter.TopHeadlinesViewHolder holder, int position) {

        ArticleModel articleModel = articleModelArrayList.get(position);

        holder.topHeadlinesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebPageActivity.class);
                intent.putExtra("url", articleModel.getUrl());
                context.startActivity(intent);
                Animatoo.animateSlideLeft(context);

            }
        });

        if (articleModel.getPublishedAt() != null) {
            holder.topHeadlinesDate.setText(articleModel.getPublishedAt());
        } else {
            holder.topHeadlinesDate.setText("Date");
        }
        if (articleModel.getAuthor() != null) {
            holder.topHeadlinesAuthor.setText(articleModel.getAuthor());
        } else  {
            holder.topHeadlinesAuthor.setText("Author");
        }
        if (articleModel.getTitle() != null) {
            holder.topHeadlinesTitle.setText(articleModel.getTitle());
        } else {
            holder.topHeadlinesTitle.setText("Title");
        }
        if (articleModel.getUrlToImage() != null) {
            holder.noImageText.setVisibility(View.GONE);
            Picasso.get().load(articleModel.getUrlToImage()).into(holder.topHeadlinesImage);
        } else {
            holder.noImageText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return articleModelArrayList.size();
    }

    public class TopHeadlinesViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView topHeadlinesCard;
        ImageView topHeadlinesImage;
        TextView topHeadlinesTitle, topHeadlinesAuthor, topHeadlinesDate, noImageText;

        public TopHeadlinesViewHolder( @NotNull View itemView) {
            super(itemView);

            topHeadlinesCard = itemView.findViewById(R.id.topHeadlinesCard);
            topHeadlinesImage = itemView.findViewById(R.id.topHeadlinesNewsImage);
            topHeadlinesTitle = itemView.findViewById(R.id.topHeadlinesNewsTitle);
            noImageText = itemView.findViewById(R.id.noImageText);
            topHeadlinesAuthor = itemView.findViewById(R.id.topHeadlinesNewsAuthor);
            topHeadlinesDate = itemView.findViewById(R.id.topHeadlinesNewsDate);


        }
    }
}
