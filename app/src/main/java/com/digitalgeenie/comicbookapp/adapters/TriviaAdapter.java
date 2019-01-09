package com.digitalgeenie.comicbookapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitalgeenie.comicbookapp.R;
import com.digitalgeenie.comicbookapp.models.TriviaModel;
import com.digitalgeenie.comicbookapp.views.TriviaViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TriviaAdapter extends RecyclerView.Adapter<TriviaViewHolder> {

    private Context context;
    private List<TriviaModel> itemList;

    public TriviaAdapter(Context context, List<TriviaModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public TriviaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trivia_card, viewGroup, false);
        return new TriviaViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull TriviaViewHolder holder, int position) {

        try {
            final TriviaModel triviaModel = itemList.get(position);
            holder.title.setText(triviaModel.getTitle());
            holder.shortDescription.setText(triviaModel.getShortDescription());
            Picasso.with(context).load(triviaModel.getThumbnailUrl()).into(holder.thumbnail);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
