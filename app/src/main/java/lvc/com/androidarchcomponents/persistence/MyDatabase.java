package lvc.com.androidarchcomponents.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import lvc.com.androidarchcomponents.model.Pokemon;

@Database(entities = {Pokemon.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract PokemonDAO pokemonDAO();

}