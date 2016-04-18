package klep.nearme.DI.component;

import dagger.Component;
import klep.nearme.DI.module.ApiModule;
import klep.nearme.DI.scopes.UserScope;
import klep.nearme.VkConnect;
import klep.nearme.getPeople.GetPeoplePresenter;
import klep.nearme.listExecute.ListExecutePresenter;
import klep.nearme.listExecute.ListExecuteView;
import klep.nearme.login.LoginPresenter;
import klep.nearme.profilePerson.ProfilePresenter;
import klep.nearme.settings.SettingPresenter;

/**
 * Created by klep.io on 25.03.16.
 */
@UserScope
@Component(dependencies = NetComponent.class,
        modules = ApiModule.class)
public interface ApiComponent {
    void inject(GetPeoplePresenter presenter);

    void inject(LoginPresenter presenter);

    void inject(VkConnect activity);

    void inject(SettingPresenter presenter);

    void inject(ProfilePresenter presenter);

    void inject(ListExecutePresenter presenter);
}
