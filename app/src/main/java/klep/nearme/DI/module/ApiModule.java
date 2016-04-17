package klep.nearme.DI.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import klep.nearme.Api.Api;
import klep.nearme.DI.scopes.UserScope;
import retrofit2.Retrofit;

/**
 * Created by klep.io on 25.03.16.
 */
@Module
public class ApiModule {

    @Provides
    @UserScope
    public Api provideApi(Retrofit retrofit){
        return retrofit.create(Api.class);
    }
}
