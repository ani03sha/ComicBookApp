package com.digitalgeenie.comicbookapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalgeenie.comicbookapp.models.TriviaModel;
import com.squareup.picasso.Picasso;

import static com.digitalgeenie.comicbookapp.utils.navigationdrawer.NavigationDrawerUtil.setupDrawerContent;

public class TriviaDetailsActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_details);

        Intent intent = getIntent();
        TriviaModel triviaModel = (TriviaModel) intent.getSerializableExtra("triviaItem");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(drawerToggle);
        NavigationView nvDrawer = findViewById(R.id.nvView);
        setupDrawerContent(mDrawer, this, nvDrawer);

        TextView title = findViewById(R.id.title);
        TextView details = findViewById(R.id.details);
        ImageView thumbnail = findViewById(R.id.thumbnail);

        title.setText(triviaModel.getTitle());
        details.setText(triviaModel.getDetails());
        Picasso.with(this).load(triviaModel.getThumbnailUrl()).into(thumbnail);
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
}
