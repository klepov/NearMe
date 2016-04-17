package klep.nearme.getPeople;

import android.content.SharedPreferences;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

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
 * Created by klep.io on 26.03.16.
 */
public class GetPeoplePresenter extends MvpBasePresenter<GetPeopleView> {
    Subscriber<Persons> subscriber;
    @Inject
    Api api;

    @Inject
    SharedPreferences preferences;

    public GetPeoplePresenter() {

        OverrideApp
                .getApiComponent().inject(this);

    }

    public void getPeople() {
        if (isViewAttached()) {
            getView().showLoading();
        }

        subscriber = new Subscriber<Persons>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Persons items) {
                Person lol = (Person) items.getItems().get(0);
                getView().showPeople(items);
            }
        };

        String token = preferences.getString(Const.TOKEN, null);


        if (token != null) {
            api.getPeople(token)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        }
    }


    private void cancelSubscribe() {
        if (subscriber != null && !subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            cancelSubscribe();
        }
    }

    @Override
    public void attachView(GetPeopleView view) {
        super.attachView(view);
    }
}
