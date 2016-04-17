package klep.nearme;

import android.app.Application;
import android.content.Context;

import com.vk.sdk.VKSdk;

import klep.nearme.DI.component.ApiComponent;
import klep.nearme.DI.component.DaggerApiComponent;
import klep.nearme.DI.component.DaggerNetComponent;
import klep.nearme.DI.component.NetComponent;
import klep.nearme.DI.module.ApiModule;
import klep.nearme.DI.module.AppModule;
import klep.nearme.DI.module.NetModule;
import klep.nearme.Utils.Const;

/**
 * Created by sad klep.io on 16.03.16.
 */
public class OverrideApp extends Application {
    private static NetComponent netComponent;
    private static ApiComponent apiComponent;
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        VKSdk.initialize(getApplicationContext());


        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(Const.IP))
                .build();

        apiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .netComponent(netComponent)
                .apiModule(new ApiModule())
                .build();
    }

    public static ApiComponent getApiComponent() {
        return apiComponent;
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }

}
