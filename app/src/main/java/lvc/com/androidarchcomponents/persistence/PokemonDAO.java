package lvc.com.androidarchcomponents.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import lvc.com.androidarchcomponents.model.Pokemon;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface PokemonDAO {

    @Insert(onConflict = REPLACE)
    void save(Pokemon user);

    @Query("SELECT * FROM pokemon WHERE id = :pokeID")
    LiveData<Pokemon> load(int pokeID);

    @Query("SELECT COUNT(*) FROM pokemon WHERE id = :pokeID")
    int countPokemon(int pokeID);

}
