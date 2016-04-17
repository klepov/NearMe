package klep.nearme.engineApplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import klep.nearme.R;
import klep.nearme.getPeople.GetPeopleFragment;
import klep.nearme.settings.SettingsActivity;

/**
 * Created by klep.io on 25.03.16.
 */
public class MainActivity extends FragmentActivity
        implements EngineView, Drawer.OnDrawerItemClickListener, OnMenuTabClickListener {

    private BottomBar bottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        bottomBar = BottomBar.attach(this, savedInstanceState);

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.setting)
                )
                .withOnDrawerItemClickListener(this)
                .build();


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
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

        switch (position){
            case 0:
                startActivity(new Intent(this, SettingsActivity.class));
        }

        return false;
    }

    @Override
    public void onMenuTabSelected(@IdRes int menuItemId) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentPlace, new GetPeopleFragment())
                .commit();
    }

    @Override
    public void onMenuTabReSelected(@IdRes int menuItemId) {
    }
}
