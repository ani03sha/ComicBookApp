package com.digitalgeenie.comicbookapp.utils.navigationdrawer;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

import com.digitalgeenie.comicbookapp.AboutUsActivity;
import com.digitalgeenie.comicbookapp.ComicsActivity;
import com.digitalgeenie.comicbookapp.NewArrivalsActivity;
import com.digitalgeenie.comicbookapp.R;
import com.digitalgeenie.comicbookapp.SettingsActivity;
import com.digitalgeenie.comicbookapp.TriviaActivity;
import com.digitalgeenie.comicbookapp.WebViewActivity;

import static com.digitalgeenie.comicbookapp.utils.network.Connectivity.isConnected;

public class NavigationDrawerUtil {

    public static void setupDrawerContent(DrawerLayout drawerLayout, Context context, NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    if (isConnected(context)) {
                        selectDrawerItem(drawerLayout, context, menuItem);
                    } else {
                        Toast.makeText(context, "Sorry! Please check your internet connection", Toast.LENGTH_LONG).show();
                    }
                    return true;
                });
    }

    private static void selectDrawerItem(DrawerLayout mDrawer, Context context, MenuItem menuItem) {

        Intent intent;
        if (isConnected(context)) {
            switch (menuItem.getItemId()) {

                case R.id.nav_zero_fragment:
                    intent = new Intent(context, ComicsActivity.class);
                    context.startActivity(intent);
                    break;

                case R.id.nav_first_fragment:
                    intent = new Intent(context, NewArrivalsActivity.class);
                    context.startActivity(intent);
                    break;

                case R.id.nav_second_fragment:
                    intent = new Intent(context, TriviaActivity.class);
                    context.startActivity(intent);
                    break;
                case R.id.nav_third_fragment:
                    intent = new Intent(context, SettingsActivity.class);
                    context.startActivity(intent);
                    break;
                case R.id.nav_fourth_fragment:
                    intent = new Intent(context, AboutUsActivity.class);
                    context.startActivity(intent);
                    break;
                case R.id.nav_fifth_fragment:
                    intent = new Intent(context, WebViewActivity.class);
                    context.startActivity(intent);
                    break;

                default:
            }
            menuItem.setChecked(true);
            mDrawer.closeDrawers();
        } else {
            Toast.makeText(context, "Sorry! Please check your internet connection", Toast.LENGTH_LONG).show();
        }
    }
}
