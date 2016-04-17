package klep.nearme.login;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by klep on 16.04.16 with love.
 */
public interface LoginView extends MvpView {

    void showLoading();

    void showLoginSuccessful();

    void showNeedMoreInfo();

    void showError();

    void showLogin();

}
