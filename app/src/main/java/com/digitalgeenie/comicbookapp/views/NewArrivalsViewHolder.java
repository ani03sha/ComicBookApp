package com.digitalgeenie.comicbookapp.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalgeenie.comicbookapp.R;

public class NewArrivalsViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView price;
    public ImageView thumbnail;


    public NewArrivalsViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        price = itemView.findViewById(R.id.price);
        thumbnail = itemView.findViewById(R.id.thumbnail);
    }
}
