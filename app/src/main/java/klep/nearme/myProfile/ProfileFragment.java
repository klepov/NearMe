package klep.nearme.myProfile;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import klep.nearme.R;
import klep.nearme.Utils.Const;
import klep.nearme.common.BaseFragment;
import klep.nearme.model.Person;

/**
 * Created by klep on 17.04.16 with love.
 */
public class ProfileFragment extends BaseFragment {

    @Bind(R.id.detailPhoto)
    ImageView detailPhoto;

    @Bind(R.id.nameAndAgePerson)
    TextView nameAndAgePersonTextEdit;

    @Bind(R.id.personWish)
    TextView personWishTextView;


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_profile;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Person person = getArguments().getParcelable(Const.USER_BUNDLE);
    }
}
