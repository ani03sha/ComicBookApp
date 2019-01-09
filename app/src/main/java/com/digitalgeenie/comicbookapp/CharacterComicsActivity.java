package com.digitalgeenie.comicbookapp;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;

import com.digitalgeenie.comicbookapp.adapters.CharacterComicsAdapter;
import com.digitalgeenie.comicbookapp.models.CharacterComicsModel;
import com.digitalgeenie.comicbookapp.utils.ToastUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

import static com.digitalgeenie.comicbookapp.utils.navigationdrawer.NavigationDrawerUtil.setupDrawerContent;
import static com.digitalgeenie.comicbookapp.utils.network.Connectivity.isConnected;

public class CharacterComicsActivity extends AppCompatActivity {

    private List<CharacterComicsModel> itemList;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private ActionBarDrawerToggle drawerToggle;
    private DatabaseReference databaseReference;
    private CharacterComicsAdapter characterComicsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_comics);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progressBar);

        String character = getIntent()
                .getStringExtra("characterName")
                .toLowerCase()
                .replaceAll(" ", "");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(drawerToggle);
        NavigationView nvDrawer = findViewById(R.id.nvView);
        setupDrawerContent(mDrawer, this, nvDrawer);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        if (isConnected(this)) {
            itemList = new LinkedList<>();
            prepareView(character);
        } else {
            ToastUtil.longToast("Sorry! Please check your internet connection", this);
        }

        characterComicsAdapter = new CharacterComicsAdapter(this, itemList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new CharacterComicsActivity.GridSpacingItemDecoration(2, dpToPx(), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(characterComicsAdapter);
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx() {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics()));
    }

    private void prepareView(String character) {

        databaseReference.child("comics")
                .orderByChild("character")
                .equalTo(character)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot comicsSnapshot : dataSnapshot.getChildren()) {

                            progressBar.setVisibility(View.INVISIBLE);

                            String title = comicsSnapshot.child("title").getValue(String.class);
                            String price = comicsSnapshot.child("price").getValue(String.class);
                            String thumbnail = comicsSnapshot.child("thumbnailUrl").getValue(String.class);

                            itemList.add(new CharacterComicsModel(title, price, thumbnail));
                        }

                        characterComicsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}
