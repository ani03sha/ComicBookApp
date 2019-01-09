package com.digitalgeenie.comicbookapp.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalgeenie.comicbookapp.R;

public class HomeViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public ImageView thumbnail;

    public HomeViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        thumbnail = itemView.findViewById(R.id.thumbnail);
    }

}
