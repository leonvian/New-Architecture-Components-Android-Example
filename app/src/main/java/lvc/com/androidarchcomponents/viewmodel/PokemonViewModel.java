package lvc.com.androidarchcomponents.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import lvc.com.androidarchcomponents.model.Pokemon;
import lvc.com.androidarchcomponents.repository.PokemonRepository;

public class PokemonViewModel extends ViewModel {

    @Inject
    PokemonRepository pokemonRepository;

    public LiveData<Pokemon> getPokemon(int pokemonID) {
        LiveData<Pokemon> data = pokemonRepository.getPokemon(pokemonID);
        return data;
    }

}
