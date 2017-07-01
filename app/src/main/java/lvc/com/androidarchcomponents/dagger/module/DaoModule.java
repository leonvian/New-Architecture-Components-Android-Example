package lvc.com.androidarchcomponents.dagger.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lvc.com.androidarchcomponents.persistence.MyDatabase;
import lvc.com.androidarchcomponents.persistence.PokemonDAO;

/**
 * Created by leonardo2050 on 28/05/17.
 */
@Module
public class DaoModule {

    private static final String DATA_BASE_NAME = "pokemon";


    @Singleton
    @Provides static MyDatabase provideMyDatabase(Application application) {
        Context context  = application.getApplicationContext();
        MyDatabase db = Room.databaseBuilder(context, MyDatabase.class, DATA_BASE_NAME).build();
        return  db;
    }

    @Singleton
    @Provides static PokemonDAO providePokemonDAO(MyDatabase db) {
        return db.pokemonDAO();
    }
}
