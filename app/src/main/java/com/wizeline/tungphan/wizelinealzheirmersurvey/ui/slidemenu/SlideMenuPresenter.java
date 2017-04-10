package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.base.BasePresenter;


/**
 * Created by tungphan on 4/9/17.
 */

public class SlideMenuPresenter extends BasePresenter {

    public SlideMenuPresenter() {

    }

    //init Navigation Drawer and Toolbar
    public void initNDrawerAndTbar(AppCompatActivity activity, DrawerLayout drawer
            , Toolbar toolbar) {
        activity.setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawer, toolbar, R.string.navigation_drawer_open
                , R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void initNavigationView(NavigationView navigationView
            , NavigationView.OnNavigationItemSelectedListener listener) {
        navigationView.setNavigationItemSelectedListener(listener);
    }

    public void initFab(FloatingActionButton floatingActionButton) {
        floatingActionButton.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    public void closeDrawer(DrawerLayout drawerLayout) {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public boolean isDrawerOpen(DrawerLayout drawerLayout) {
        return drawerLayout.isDrawerOpen(GravityCompat.START);
    }
}
