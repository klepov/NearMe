package klep.nearme.getPeople;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

/**
 * Created by klep.io on 27.03.16.
 */
public class GetPeopleViewState implements ViewState<GetPeopleView> {

    final int STATE_SHOW_PEOPLE = 0;
    final int STATE_SHOW_LOADING= 1;
    final int STATE_SHOW_ERROR= 2;
    int state = STATE_SHOW_LOADING;

    @Override
    public void apply(GetPeopleView view, boolean retained) {

        switch (state){
            case STATE_SHOW_LOADING:
                view.showLoading();
                break;
            case STATE_SHOW_PEOPLE:
                view.showPeople(null);
                break;
            case STATE_SHOW_ERROR:
                view.showError(0);
                break;
        }
    }

    public void setStateShowAuthForm(){
        state = STATE_SHOW_PEOPLE;
    }

    public void setShowLoading(){
        state = STATE_SHOW_LOADING;
    }
    public void setStateShowError(){
        state = STATE_SHOW_ERROR;
    }
}
