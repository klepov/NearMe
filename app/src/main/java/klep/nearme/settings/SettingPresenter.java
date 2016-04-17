package klep.nearme.settings;

import android.content.SharedPreferences;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import klep.nearme.Api.Api;
import klep.nearme.OverrideApp;
import klep.nearme.Utils.Const;
import klep.nearme.model.ErrorCode;
import klep.nearme.model.request.SettingsRequest;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by klep on 17.04.16 with love.
 */
public class SettingPresenter extends MvpBasePresenter<SettingView> {

    Subscriber<ErrorCode> subscriber;

    @Inject
    Api api;

    @Inject
    SharedPreferences preferences;

    public SettingPresenter() {
        OverrideApp.getApiComponent().inject(this);
    }


    public void sendSetting(SettingsRequest request) {
        if (getView() != null) {
            getView().showLoading();
        }
        String token = preferences.getString(Const.TOKEN, null);

        subscriber = new Subscriber<ErrorCode>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ErrorCode code) {
                if (code.getCode() == 777){
                    getView().showSuccess();
                }
            }
        };

        if (token != null) {

            api.updateFilter(
                    token,
                    request.getAgeFrom(),
                    request.getAgeTo(),
                    request.getMyAge(),
                    request.getSexNeed())

                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        }
    }
}
