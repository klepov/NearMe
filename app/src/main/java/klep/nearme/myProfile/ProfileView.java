package klep.nearme.myProfile;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by klep on 17.04.16 with love.
 */
public interface ProfileView extends MvpView {

    void showPerson();
    void showLoading();
    void showError();
}
