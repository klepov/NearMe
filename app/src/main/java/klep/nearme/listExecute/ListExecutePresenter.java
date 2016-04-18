package klep.nearme.listExecute;

import android.content.SharedPreferences;
import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import javax.inject.Inject;

import klep.nearme.Api.Api;
import klep.nearme.OverrideApp;
import klep.nearme.Utils.Const;
import klep.nearme.model.Person;
import klep.nearme.model.Persons;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by klep on 18.04.16 with love.
 */
public class ListExecutePresenter extends MvpBasePresenter<ListExecuteView> {

    @Inject
    Api api;

    @Inject
    SharedPreferences preferences;

    Subscriber<Persons> subscribe;

    public ListExecutePresenter() {
        OverrideApp.getApiComponent().inject(this);
    }

    public void getListExecute() {
        if (getView() != null) {
            getView().showLoading();
        }

        String token = preferences.getString(Const.TOKEN, null);

        subscribe = new Subscriber<Persons>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Persons persons) {
                getView().listFromServer(persons.getItems());
            }
        };

        if (token != null) {

            api.getExecutWishList(token)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscribe);
        }
    }

}
