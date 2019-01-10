package com.digitalgeenie.comicbookapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitalgeenie.comicbookapp.R;
import com.digitalgeenie.comicbookapp.models.NewArrivalsModel;
import com.digitalgeenie.comicbookapp.views.NewArrivalsViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewArrivalsAdapter extends RecyclerView.Adapter<NewArrivalsViewHolder> {

    private Context context;
    private List<NewArrivalsModel> itemList;

    public NewArrivalsAdapter(Context context, List<NewArrivalsModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public NewArrivalsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.new_arrivals_card, viewGroup, false);
        return new NewArrivalsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewArrivalsViewHolder holder, int position) {

        try {

            NewArrivalsModel newArrivalsModel = itemList.get(position);

            holder.title.setText(newArrivalsModel.getTitle());
            holder.price.setText(newArrivalsModel.getPrice());
            Picasso.with(context).load(newArrivalsModel.getThumbnailUrl()).into(holder.thumbnail);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
