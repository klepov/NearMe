package klep.nearme.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import klep.nearme.R;
import klep.nearme.common.BaseViewStateFragment;
import klep.nearme.engineApplication.MainActivity;
import klep.nearme.settings.SettingsActivity;


public class LoginFragment extends BaseViewStateFragment<LoginView, LoginPresenter> implements LoginView, GoogleApiClient.ConnectionCallbacks {
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoginSuccessful() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

    @Override
    public void showNeedMoreInfo() {
        startActivity(new Intent(getActivity(), SettingsActivity.class));
        getActivity().finish();
    }


    @Override
    public void showError() {

    }

    @Override
    public void showLogin() {

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addApi(LocationServices.API)
                    .build();
        }

    }

    @Override
    public void onStart() {
        mGoogleApiClient.connect();

        super.onStart();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();

        super.onStop();
    }

    @NonNull
    @Override
    public ViewState createViewState() {
        return new LoginViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        showLogin();
    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

        presenter.doLogin(mLastLocation);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
