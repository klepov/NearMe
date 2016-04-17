package klep.nearme.profilePerson;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import klep.nearme.R;
import klep.nearme.Utils.Const;
import klep.nearme.common.BaseViewStateFragment;
import klep.nearme.model.Person;

/**
 * Created by klep on 17.04.16 with love.
 */
public class ProfileFragment extends BaseViewStateFragment<ProfileView, ProfilePresenter>
        implements ProfileView {

    @Bind(R.id.detailPhoto)
    ImageView detailPhoto;

    @Bind(R.id.nameAndAgePerson)
    TextView nameAndAgePersonTextEdit;

    @Bind(R.id.personWish)
    TextView personWishTextView;

    @Bind(R.id.openVk)
    Button openVK;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_profile;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        try {
            Person person = getArguments().getParcelable(Const.USER_BUNDLE);
            getPerson(person);
        } catch (NullPointerException e) {
            presenter.getMyProfile();

        }


    }

    @Override
    public ProfilePresenter createPresenter() {
        return new ProfilePresenter();
    }

    @Override
    public ViewState createViewState() {
        return new ProfileViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        showPerson();
    }

    @Override
    public void showPerson() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void getPerson(Person person) {
        nameAndAgePersonTextEdit.setText(person.getAgeAndName());
        personWishTextView.setText(person.getWish());

        Picasso.with(getActivity())
                .load(person.getPhotoId())
                .into(detailPhoto);

//        openVK.setVisibility(View.VISIBLE);
    }
}
