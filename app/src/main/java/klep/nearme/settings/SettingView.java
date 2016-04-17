package klep.nearme.settings;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by klep on 17.04.16 with love.
 */
public interface SettingView extends MvpView {

    void showSettingForm();

    void showLoading();

    void showError();

    void showSuccess();
}
