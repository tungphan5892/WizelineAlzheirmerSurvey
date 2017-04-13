package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by tungphan on 4/9/17.
 */

public class SlideMenuActivity extends BaseActivity
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

    private SlideMenuPresenter slideMenuPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        slideMenuPresenter = new SlideMenuPresenter();
        setContentView(R.layout.slide_menu_activity);
        slideMenuPresenter.initNDrawerAndTbar(this, drawerLayout, toolbar);
        slideMenuPresenter.initFab(floatingActionButton);
        slideMenuPresenter.initNavigationView(navigationView, this);
    }

    @Override
    public void onBackPressed() {
        if (slideMenuPresenter.isDrawerOpen(drawerLayout)) {
            slideMenuPresenter.closeDrawer(drawerLayout);
        } else {
            super.onBackPressed();
        }
    }


    //TODO: clean those two methods below. Bring the logic to presenter maybe.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
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
        slideMenuPresenter.closeDrawer(drawerLayout);
        return true;
    }
}
