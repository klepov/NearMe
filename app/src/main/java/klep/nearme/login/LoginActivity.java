package klep.nearme.login;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import klep.nearme.R;
import klep.nearme.common.BaseActivity;

/**
 * Created by klep on 16.04.16 with love.
 */
public class LoginActivity extends BaseActivity{
    private FragmentTransaction transaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
//        if (toolbar != null) {
//            toolbar.setTitle(auth);
//        }
//        setSupportActionBar(toolbar);



        transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.placeLoginFragment, new LoginFragment())
                .commit();

    }

}
