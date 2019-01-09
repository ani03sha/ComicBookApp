package com.digitalgeenie.comicbookapp.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalgeenie.comicbookapp.R;

public class TriviaViewHolder extends RecyclerView.ViewHolder {

    public TextView title, shortDescription;
    public ImageView thumbnail;

    public TriviaViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        shortDescription = itemView.findViewById(R.id.shortDescription);
        thumbnail = itemView.findViewById(R.id.thumbnail);
    }

}
