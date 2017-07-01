package lvc.com.androidarchcomponents.dagger.module;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lvc.com.androidarchcomponents.persistence.PokemonDAO;
import lvc.com.androidarchcomponents.repository.PokemonRepository;
import lvc.com.androidarchcomponents.utils.ThreadPerTaskExecutor;
import lvc.com.androidarchcomponents.webservice.PokemonWebservice;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by leonardo2050 on 28/05/17.
 */

@Module
public class NetModule {

    private static final String BASE_URL = "http://pokeapi.co";

    @Singleton
    @Provides static PokemonRepository providePokemonRepository(PokemonWebservice service, PokemonDAO pokemonDAO, Executor executor) {
        return new PokemonRepository(service, pokemonDAO, executor);
    }

    @Singleton
    @Provides static PokemonWebservice providePokemonWebservice(Retrofit retrofit) {
        PokemonWebservice pokemonWebservice = retrofit.create(PokemonWebservice.class);
        return pokemonWebservice;
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides static Executor provideExecutor() {
        return new ThreadPerTaskExecutor();
    }


}
