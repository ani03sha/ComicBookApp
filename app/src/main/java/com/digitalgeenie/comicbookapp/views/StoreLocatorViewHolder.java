package com.digitalgeenie.comicbookapp.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalgeenie.comicbookapp.R;

public class StoreLocatorViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView address;
    public TextView phone;
    public ImageView thumbnail;

    public StoreLocatorViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        address = itemView.findViewById(R.id.address);
        phone = itemView.findViewById(R.id.phone);
        thumbnail = itemView.findViewById(R.id.thumbnail);
    }
}
