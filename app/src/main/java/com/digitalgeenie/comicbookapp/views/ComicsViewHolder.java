package com.digitalgeenie.comicbookapp.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalgeenie.comicbookapp.R;

public class ComicsViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public ImageView thumbnail;
    public Button browseComics;

    public ComicsViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        thumbnail = itemView.findViewById(R.id.thumbnail);
        browseComics = itemView.findViewById(R.id.browseComics);
    }
}
