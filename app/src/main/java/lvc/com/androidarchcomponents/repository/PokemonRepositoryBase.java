package lvc.com.androidarchcomponents.repository;

import android.arch.lifecycle.LiveData;

import lvc.com.androidarchcomponents.model.Pokemon;


public interface PokemonRepositoryBase {

    public LiveData<Pokemon> getPokemon(int pokeID);
}
