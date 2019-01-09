package com.digitalgeenie.comicbookapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitalgeenie.comicbookapp.R;
import com.digitalgeenie.comicbookapp.models.CharacterComicsModel;
import com.digitalgeenie.comicbookapp.views.CharacterComicsViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CharacterComicsAdapter extends RecyclerView.Adapter<CharacterComicsViewHolder> {

    private Context context;
    private List<CharacterComicsModel> itemList;

    public CharacterComicsAdapter(Context context, List<CharacterComicsModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public CharacterComicsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.charater_comics_card, viewGroup, false);
        return new CharacterComicsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterComicsViewHolder holder, int position) {

        final CharacterComicsModel characterComicsModel = itemList.get(position);

        holder.title.setText(characterComicsModel.getTitle());
        holder.price.setText(characterComicsModel.getPrice());
        Picasso.with(context).load(characterComicsModel.getThumbnailUrl()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
