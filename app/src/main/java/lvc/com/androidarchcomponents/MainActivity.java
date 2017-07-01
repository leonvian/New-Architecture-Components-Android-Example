package lvc.com.androidarchcomponents;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import lvc.com.androidarchcomponents.model.Pokemon;
import lvc.com.androidarchcomponents.viewmodel.PokemonViewModel;

public class MainActivity extends LifecycleActivity  {


    private static final int BULBASAUR_ID = 1;
    private static final int VENUSAUR_ID = 3;

    private int currentSelectedPokemon = BULBASAUR_ID;

    private TextView textViewData;
    private PokemonViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewData = (TextView) findViewById(R.id.text_view_data);
        viewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        ((App) getApplication()).getNetComponent().inject(viewModel);

        viewModel.getPokemon(currentSelectedPokemon).observe(this, new Observer<Pokemon>() {
            @Override
            public void onChanged(@Nullable Pokemon pokemon) {
                textViewData.setText(pokemon.getName());
            }
        });
    }



}