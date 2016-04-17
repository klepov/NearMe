package klep.nearme.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.appyvet.rangebar.RangeBar;
import com.github.glomadrian.materialanimatedswitch.MaterialAnimatedSwitch;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.OnClick;
import klep.nearme.R;
import klep.nearme.common.BaseViewStateFragment;
import klep.nearme.engineApplication.MainActivity;
import klep.nearme.model.request.SettingsRequest;


public class SettingFragment extends BaseViewStateFragment<SettingView, SettingPresenter>
        implements SettingView, RangeBar.OnRangeBarChangeListener, MaterialAnimatedSwitch.OnCheckedChangeListener {

    @Bind(R.id.myAgeRangeBar)
    RangeBar myAgeRangeBar;

    @Bind(R.id.needAgeRangeBar)
    RangeBar needAgeRangeBar;

    @Bind(R.id.pinSex)
    MaterialAnimatedSwitch pinSex;

    @Bind(R.id.ageNeedChange)
    TextView ageNeedChangeTextEdit;

    @Bind(R.id.myAgeChange)
    TextView myAgeChangeTextEdit;

    @Bind(R.id.needSexTextEdit)
    TextView needSexTextEdit;

    @BindString(R.string.girls)
    String girls;

    @BindString(R.string.boys)
    String boys;

    int sex;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myAgeRangeBar.setOnRangeBarChangeListener(this);
        needAgeRangeBar.setOnRangeBarChangeListener(this);
        pinSex.setOnCheckedChangeListener(this);

        myAgeRangeBar.setRangePinsByValue(18,18);
        needAgeRangeBar.setRangePinsByValue(14,87);
//        pinSex.isChecked();
    }

    protected int getLayoutRes() {
        return R.layout.settings;
    }

    @NonNull
    @Override
    public ViewState createViewState() {
        return new SettingViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        showSettingForm();
    }

    @NonNull
    @Override
    public SettingPresenter createPresenter() {
        return new SettingPresenter();
    }

    @Override
    public void showSettingForm() {
        SettingViewState vs = (SettingViewState) viewState;
        vs.setStateShowSettingForm();
    }

    @Override
    public void showLoading() {
        SettingViewState vs = (SettingViewState) viewState;
        vs.setStateShowLoading();
    }

    @Override
    public void showError() {
        SettingViewState vs = (SettingViewState) viewState;
        vs.setStateShowError();

    }

    @Override
    public void showSuccess() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

    @OnClick(R.id.FAB_setting)
    public void click() {
        String[] ageNeed = ageNeedChangeTextEdit.getText().toString().split(" - ");
        int ageFrom = Integer.parseInt(ageNeed[0]);
        int ageTo = Integer.parseInt(ageNeed[1]);
        int myAge = Integer.parseInt(myAgeChangeTextEdit.getText().toString());

        presenter.sendSetting(new SettingsRequest(ageFrom, ageTo, myAge, sex));
    }


    @Override
    public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
        switch (rangeBar.getId()) {

            case R.id.myAgeRangeBar:
                myAgeChangeTextEdit.setText(rightPinValue);
                break;

            case R.id.needAgeRangeBar:
                ageNeedChangeTextEdit.setText(leftPinValue + " - " + rightPinValue);
                break;

        }
    }


    @Override
    public void onCheckedChanged(boolean isPush) {
        needSexTextEdit.setText(isPush ? girls : boys);
        sex = Integer.parseInt(String.valueOf(isPush ? 1 : 2));
    }


}
