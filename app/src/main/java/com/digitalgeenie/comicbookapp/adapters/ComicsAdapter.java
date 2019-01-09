package com.digitalgeenie.comicbookapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitalgeenie.comicbookapp.CharacterComicsActivity;
import com.digitalgeenie.comicbookapp.R;
import com.digitalgeenie.comicbookapp.models.ComicsModel;
import com.digitalgeenie.comicbookapp.views.ComicsViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ComicsAdapter extends RecyclerView.Adapter<ComicsViewHolder> {

    private Context context;
    private List<ComicsModel> itemList;

    public ComicsAdapter(Context context, List<ComicsModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ComicsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comics_card, viewGroup, false);
        return new ComicsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicsViewHolder holder, int position) {

        final ComicsModel comicsModel = itemList.get(position);

        holder.title.setText(comicsModel.getTitle());
        Picasso.with(context).load(comicsModel.getThumbnail()).into(holder.thumbnail);
        holder.browseComics.setOnClickListener(view -> {
            Intent intent = new Intent(context, CharacterComicsActivity.class);
            intent.putExtra("characterName", comicsModel.getTitle());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
