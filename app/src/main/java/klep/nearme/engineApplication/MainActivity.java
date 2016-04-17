package klep.nearme.engineApplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import klep.nearme.R;
import klep.nearme.common.BaseActivity;
import klep.nearme.getPeople.GetPeopleFragment;
import klep.nearme.profilePerson.ProfileFragment;
import klep.nearme.settings.SettingsActivity;

/**
 * Created by klep.io on 25.03.16.
 */
public class MainActivity extends BaseActivity
        implements EngineView, OnMenuTabClickListener, Toolbar.OnMenuItemClickListener {

    private BottomBar bottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.setting_toolbar);
        setSupportActionBar(toolbar);

        if (toolbar != null) {
            toolbar.setOnMenuItemClickListener(this);
        }

        bottomBar = BottomBar.attach(this, savedInstanceState);


        bottomBar.setItemsFromMenu(R.menu.bottom_menu, this);
        bottomBar.setDefaultTabPosition(1);

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void showError() {

    }


    @Override
    public void onMenuTabSelected(@IdRes int menuItemId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (menuItemId) {
            case R.id.bottomBarItemHome:
                transaction.replace(R.id.fragmentPlace, new GetPeopleFragment())
                        .commit();
                break;

            case R.id.bottomBarProfile:
                transaction.replace(R.id.fragmentPlace, new ProfileFragment())
                        .commit();
                break;
        }

    }

    @Override
    public void onMenuTabReSelected(@IdRes int menuItemId) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_setting, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.setting)
            startActivity(new Intent(this, SettingsActivity.class));
        return true;
    }
}
