package klep.nearme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import javax.inject.Inject;

import klep.nearme.Utils.Const;
import klep.nearme.engineApplication.MainActivity;
import klep.nearme.login.LoginActivity;

public class VkConnect extends AppCompatActivity {
    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OverrideApp.getApiComponent().inject(this);
        if (VKAccessToken.currentToken() == null) {
            VKSdk.login(this, "group");
        } else {
            sharedPreferences.edit().putString(Const.TOKEN, VKAccessToken.currentToken().accessToken)
                    .apply();
            done(new Intent(this, LoginActivity.class));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void done(Intent intent) {
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                sharedPreferences.edit().putString(Const.TOKEN, res.accessToken)
                        .apply();
                done(new Intent(getApplicationContext(), LoginActivity.class));

            }

            @Override
            public void onError(VKError error) {
                Log.d("as", "" + error.errorCode);
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
