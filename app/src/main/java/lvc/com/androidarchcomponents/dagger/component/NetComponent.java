package lvc.com.androidarchcomponents.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import lvc.com.androidarchcomponents.dagger.module.AppModule;
import lvc.com.androidarchcomponents.dagger.module.DaoModule;
import lvc.com.androidarchcomponents.dagger.module.NetModule;
import lvc.com.androidarchcomponents.viewmodel.PokemonViewModel;

/**
 * Created by leonardo2050 on 28/05/17.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class, DaoModule.class})
public interface NetComponent {

    void inject(PokemonViewModel pokemonViewModel);


}
