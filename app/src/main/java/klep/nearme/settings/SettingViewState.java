package klep.nearme.settings;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

/**
 * Created by klep on 17.04.16 with love.
 */
public class SettingViewState implements ViewState<SettingView> {
    final int STATE_SHOW_LOADING = 0;
    final int STATE_SHOW_ERROR = 1;
    final int STATE_SHOW_SETTING_FORM = 2;

    int state = STATE_SHOW_SETTING_FORM;


    @Override
    public void apply(SettingView view, boolean retained) {
        switch (state) {
            case STATE_SHOW_ERROR:
                view.showError();
                break;
            case STATE_SHOW_LOADING:
                view.showLoading();
                break;
            case STATE_SHOW_SETTING_FORM:
                view.showSettingForm();
                break;


        }
    }

    public void setStateShowError() {
        state = STATE_SHOW_ERROR;
    }

    public void setStateShowLoading() {
        state = STATE_SHOW_LOADING;
    }

    public void setStateShowSettingForm() {
        state = STATE_SHOW_SETTING_FORM;
    }


}
