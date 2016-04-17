package klep.nearme.login;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

/**
 * Created by klep on 16.04.16 with love.
 */
public class LoginViewState implements ViewState<LoginView> {
    final int STATE_SHOW_LOADING = 0;
    final int STATE_SHOW_ERROR = 1;
    final int STATE_SHOW_LOGIN = 2;

    int state = STATE_SHOW_LOADING;


    @Override
    public void apply(LoginView view, boolean retained) {
        switch (state) {
            case STATE_SHOW_ERROR:
                view.showError();
                break;
            case STATE_SHOW_LOADING:
                view.showLoading();
                break;
            case STATE_SHOW_LOGIN:
                view.showLogin();
                break;


        }
    }

    public void setStateShowLoading() {
        state = STATE_SHOW_LOADING;
    }

    public void setStateShowError() {
        state = STATE_SHOW_ERROR;
    }

    public void setStateShowLogin() {
        state = STATE_SHOW_LOGIN;
    }


}
