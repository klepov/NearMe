package klep.nearme.profilePerson;

import com.hannesdorfmann.mosby.mvp.MvpView;

import klep.nearme.model.Person;

/**
 * Created by klep on 17.04.16 with love.
 */
public interface ProfileView extends MvpView {

    void showPerson();
    void showLoading();
    void showError();
    void getPerson(Person person);
}
