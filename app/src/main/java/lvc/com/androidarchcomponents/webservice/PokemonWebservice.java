package lvc.com.androidarchcomponents.webservice;

import lvc.com.androidarchcomponents.model.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by leonardo2050 on 27/05/17.
 */

public interface PokemonWebservice {

    @GET("/api/v2/pokemon/{pokeID}/")
    Call<Pokemon> getPokemon(@Path("pokeID") String pokeid);
}
