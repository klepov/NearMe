package klep.nearme.profilePerson;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindString;
import klep.nearme.R;
import klep.nearme.common.BaseActivity;
import klep.nearme.model.Person;

/**
 * Created by klep on 17.04.16 with love.
 */
public class ProfileActivity extends BaseActivity {

    @BindString(R.string.back)
    String back;
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.setting_toolbar);
        toolbar.setTitle(back);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Person person = getIntent().getExtras().getParcelable("Person");

        Bundle bundle = new Bundle();
        bundle.putParcelable("Person", person);
        ProfileFragment fragment = ProfileFragment.getInstance(bundle);

        if (savedInstanceState == null) {
            transaction.replace(R.id.placeProfileFragment, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
