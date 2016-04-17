package klep.nearme.profilePerson;

import android.content.SharedPreferences;
import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import klep.nearme.Api.Api;
import klep.nearme.OverrideApp;
import klep.nearme.Utils.Const;
import klep.nearme.model.Person;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by klep on 17.04.16 with love.
 */
public class ProfilePresenter extends MvpBasePresenter<ProfileView> {

    @Inject
    Api api;

    @Inject
    SharedPreferences preferences;

    Subscriber<Person> myProfileSubscriber;


    public ProfilePresenter() {
        OverrideApp.getApiComponent().inject(this);
    }


    public void addFavorite() {

        if (getView() != null) {
            getView().showLoading();

            String token = preferences.getString(Const.TOKEN, null);
        }

    }

    public void getMyProfile() {

        if (getView() != null) {
            getView().showLoading();
        }
        String token = preferences.getString(Const.TOKEN, null);


        myProfileSubscriber = new Subscriber<Person>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Person person) {
                getView().getPerson(person);
            }
        };

        api.getMyProfile(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myProfileSubscriber);
    }


}
