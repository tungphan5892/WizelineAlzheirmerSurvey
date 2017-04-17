package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.WizeApp;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.base.BaseActivity;

import java.util.ArrayList;


import butterknife.BindView;

/**
 * Created by tungphan on 4/9/17.
 */

public class SlideMenuActivity extends BaseActivity<SlideMenuPresenter>
        implements SlideMenuView, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.fab)
    protected FloatingActionButton floatingActionButton;

    @BindView(R.id.content_layout)
    protected FrameLayout contentLayout;

    protected ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().initInjector(WizeApp.getAppComponent(this));
        initNDrawerAndTbar(this, drawerLayout, toolbar);
        initFab(floatingActionButton);
        initNavigationView(navigationView, this);
    }

    //init Navigation Drawer and Toolbar
    public void initNDrawerAndTbar(AppCompatActivity activity, DrawerLayout drawer
            , Toolbar toolbar) {
        activity.setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                activity, drawer, toolbar, R.string.navigation_drawer_open
                , R.string.navigation_drawer_close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public void initNavigationView(NavigationView navigationView
            , NavigationView.OnNavigationItemSelectedListener listener) {
        navigationView.setNavigationItemSelectedListener(listener);
    }

    public void initFab(FloatingActionButton floatingActionButton) {
        floatingActionButton.setOnClickListener(view -> Snackbar.make(view
                , "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void enableShowNavDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        actionBarDrawerToggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    protected void disableShowNavDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        actionBarDrawerToggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    protected void enableShowHomeAsUp() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void disableShowHomAsUp() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    protected void setBackButtonClickListener() {
        if (getBackButtonInNavigationDrawer() != null) {
            getBackButtonInNavigationDrawer().setOnClickListener(v -> {
                setResult(Activity.RESULT_CANCELED);
                finish();
            });
        }
    }

    private ImageButton getBackButtonInNavigationDrawer() {
        final ArrayList<View> outViews = new ArrayList<>();
        String contentDesc = getResources().getString(R.string.navigate_up);
        toolbar.findViewsWithText(outViews, contentDesc
                , View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
        if (outViews.size() > 0) {
            return (ImageButton) outViews.get(0);
        } else {
            return null;
        }
    }
}
