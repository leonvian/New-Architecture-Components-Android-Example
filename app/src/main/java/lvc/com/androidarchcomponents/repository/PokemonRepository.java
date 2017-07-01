package lvc.com.androidarchcomponents.repository;

import android.arch.lifecycle.LiveData;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import lvc.com.androidarchcomponents.model.Pokemon;
import lvc.com.androidarchcomponents.persistence.PokemonDAO;
import lvc.com.androidarchcomponents.webservice.PokemonWebservice;
import retrofit2.Response;

/**
 * Created by leonardo2050 on 27/05/17.
 */
@Singleton
public class PokemonRepository implements PokemonRepositoryBase {


    private final Executor executor;
    private final PokemonWebservice service;
    private final PokemonDAO pokemonDAO;

    @Inject
    public PokemonRepository(PokemonWebservice service, PokemonDAO pokemonDAO, Executor executor) {
        this.service = service;
        this.executor = executor;
        this.pokemonDAO = pokemonDAO;
    }

    public LiveData<Pokemon> getPokemon(int pokeID) {
        refreshPokemon(pokeID);
        return pokemonDAO.load(pokeID);
    }


    private void refreshPokemon(final int pokeID) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                boolean userExists = pokemonDAO.countPokemon(pokeID) > 0;
                if (!userExists) {
                    try {
                        Response<Pokemon> response = service.getPokemon(String.valueOf(pokeID)).execute();
                        Pokemon pokemon = response.body();
                        pokemonDAO.save(pokemon);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        executor.execute(runnable);

    }
}
