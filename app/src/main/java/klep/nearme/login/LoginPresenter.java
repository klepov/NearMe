package klep.nearme.login;

import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import klep.nearme.Api.Api;
import klep.nearme.OverrideApp;
import klep.nearme.Utils.Const;
import klep.nearme.model.ErrorCode;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by klep on 16.04.16 with love.
 */

public class LoginPresenter extends MvpBasePresenter<LoginView> {

    Subscriber<ErrorCode> subscriber;
    @Inject
    Api api;

    @Inject
    SharedPreferences preferences;

    public LoginPresenter() {
        OverrideApp.getApiComponent().inject(this);
    }

    public void doLogin(Location locations) {
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
                e.printStackTrace();
            }

            @Override
            public void onNext(ErrorCode code) {
                Log.d("asd","asd");
                switch (code.getCode() ){
                    case 99:
                        getView().showNeedMoreInfo();
                        break;

                    case 777:
                        getView().showLoginSuccessful();
                        break;
                }
            }
        };

        if (token != null && locations != null) {

            api.auth(token, locations.getLatitude(), locations.getLongitude())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        }
    }
}
