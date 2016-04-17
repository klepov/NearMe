package klep.nearme.profilePerson;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

/**
 * Created by klep on 17.04.16 with love.
 */
public class ProfileViewState implements ViewState<ProfileView> {

    final int STATE_SHOW_LOADING = 0;
    final int STATE_SHOW_ERROR = 1;
    final int STATE_SHOW_PERSON = 2;

    int state = STATE_SHOW_PERSON;


    @Override
    public void apply(ProfileView view, boolean retained) {
        switch (state) {
            case STATE_SHOW_ERROR:
                view.showError();
                break;
            case STATE_SHOW_LOADING:
                view.showLoading();
                break;
            case STATE_SHOW_PERSON:
                view.showPerson();
                break;


        }
    }

    public void setStateShowError() {
        state = STATE_SHOW_ERROR;
    }

    public void setStateShowLoading() {
        state = STATE_SHOW_LOADING;
    }

    public void setStateShowPerson() {
        state = STATE_SHOW_PERSON;
    }

}
