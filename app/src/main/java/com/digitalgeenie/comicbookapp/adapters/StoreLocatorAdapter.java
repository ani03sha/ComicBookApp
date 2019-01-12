package com.digitalgeenie.comicbookapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitalgeenie.comicbookapp.R;
import com.digitalgeenie.comicbookapp.models.StoreLocatorModel;
import com.digitalgeenie.comicbookapp.views.StoreLocatorViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StoreLocatorAdapter extends RecyclerView.Adapter<StoreLocatorViewHolder> {

    private Context context;
    private List<StoreLocatorModel> itemList;

    public StoreLocatorAdapter(Context context, List<StoreLocatorModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public StoreLocatorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.store_locator_card, viewGroup, false);
        return new StoreLocatorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreLocatorViewHolder holder, int position) {

        try {

            StoreLocatorModel storeLocatorModel = itemList.get(position);

            holder.name.setText(storeLocatorModel.getName());
            holder.address.setText(storeLocatorModel.getAddress());
            holder.phone.setText(storeLocatorModel.getPhone());
            Picasso.with(context).load(storeLocatorModel.getThumbnail()).into(holder.thumbnail);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
