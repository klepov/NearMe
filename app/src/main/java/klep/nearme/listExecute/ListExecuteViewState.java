package klep.nearme.listExecute;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

/**
 * Created by klep on 18.04.16 with love.
 */
public class ListExecuteViewState implements ViewState<ListExecuteView> {
    final int STATE_SHOW_LOADING = 0;
    final int STATE_SHOW_ERROR = 1;
    final int STATE_SHOW_LIST_EXECUTE = 2;

    int state = STATE_SHOW_LOADING;


    @Override
    public void apply(ListExecuteView view, boolean retained) {
        switch (state) {
            case STATE_SHOW_ERROR:
                view.showError();
                break;
            case STATE_SHOW_LOADING:
                view.showLoading();
                break;
            case STATE_SHOW_LIST_EXECUTE:
                view.getList();
                break;


        }
    }

    public void setStateShowLoading() {
        state = STATE_SHOW_LOADING;
    }

    public void setStateShowError() {
        state = STATE_SHOW_ERROR;
    }

    public void setStateShowListExecute() {
        state = STATE_SHOW_LIST_EXECUTE;
    }
}
