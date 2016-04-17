package klep.nearme.getPeople;

import com.hannesdorfmann.mosby.mvp.MvpView;

import klep.nearme.model.Person;
import klep.nearme.model.Persons;

/**
 * Created by klep.io on 26.03.16.
 */
public interface GetPeopleView extends MvpView {
    void showLoading();
    void showError(int error);
    void showPeople(Persons person);
}
