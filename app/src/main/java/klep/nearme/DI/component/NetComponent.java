package klep.nearme.DI.component;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import klep.nearme.DI.module.AppModule;
import klep.nearme.DI.module.NetModule;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by klep.io on 25.03.16.
 */
@Singleton
@Component(modules = {AppModule.class,NetModule.class})
public interface NetComponent {
    Retrofit retrofit();

    SharedPreferences sharedPreferences();
}
