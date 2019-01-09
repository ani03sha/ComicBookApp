package com.digitalgeenie.comicbookapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitalgeenie.comicbookapp.ComicsActivity;
import com.digitalgeenie.comicbookapp.NewArrivalsActivity;
import com.digitalgeenie.comicbookapp.R;
import com.digitalgeenie.comicbookapp.StoreLocatorActivity;
import com.digitalgeenie.comicbookapp.TriviaActivity;
import com.digitalgeenie.comicbookapp.models.HomeModel;
import com.digitalgeenie.comicbookapp.views.HomeViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {

    private Context context;
    private List<HomeModel> itemList;

    public HomeAdapter(Context context, List<HomeModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card, parent, false);
        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeViewHolder holder, int position) {

        try {
            final HomeModel homeModel = itemList.get(position);

            holder.title.setText(homeModel.getTitle());

            Picasso.with(context).load(homeModel.getThumbnail()).into(holder.thumbnail);

            holder.thumbnail.setOnClickListener(view -> {
                Intent intent;
                switch (homeModel.getTitle()) {
                    case "Trivia":
                        intent = new Intent(context, TriviaActivity.class);
                        context.startActivity(intent);
                        break;

                    case "Comics":
                        intent = new Intent(context, ComicsActivity.class);
                        context.startActivity(intent);
                        break;

                    case "New Arrivals":
                        intent = new Intent(context, NewArrivalsActivity.class);
                        context.startActivity(intent);
                        break;

                    case "Store Locator":
                        intent = new Intent(context, StoreLocatorActivity.class);
                        context.startActivity(intent);
                        break;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
