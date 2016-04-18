package klep.nearme.listExecute;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import klep.nearme.model.Person;

/**
 * Created by klep on 18.04.16 with love.
 */
public interface ListExecuteView extends MvpView {

    void showLoading();

    void showError();

    void getList();

    void listFromServer(List<Person> persons);
}
