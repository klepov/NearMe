package klep.nearme.engineApplication;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by klep.io on 31.03.16.
 */
public interface EngineView extends MvpView {

    void showLoading();
    void showError();
}
