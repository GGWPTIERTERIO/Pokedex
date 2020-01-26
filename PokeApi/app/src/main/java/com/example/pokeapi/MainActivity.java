package com.example.pokeapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.pokeapi.Model.Pokemon;
import com.example.pokeapi.Model.PokemonRespueta;
import com.example.pokeapi.PokeApi.PokeApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "POKEDEX";
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private AdapterPokeApi adapter;
    private int offset=0;
    private boolean aptoCarga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.rViewPokeApi);
        adapter = new AdapterPokeApi(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager= new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0){
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleCount = layoutManager.findFirstVisibleItemPosition();

                    if(aptoCarga){
                        if(visibleItemCount+pastVisibleCount>=totalItemCount){
                            aptoCarga=false;
                            offset+=20;
                            setUpRetrofit(offset);
                        }

                    }
                }
            }
        });
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //offset=0;
        setUpRetrofit(offset);
        aptoCarga=true;
    }

    private void setUpRetrofit(int offset) {
        PokeApiService service = retrofit.create(PokeApiService.class);

        Call<PokemonRespueta> pokemonRespuetaCall = service.obtenerListaPokemon(20,offset);

        pokemonRespuetaCall.enqueue(new Callback<PokemonRespueta>() {
            @Override
            public void onResponse(Call<PokemonRespueta> call, Response<PokemonRespueta> response) {
                if(response.isSuccessful()){
                    aptoCarga=true;
                    PokemonRespueta pokemonRespueta = response.body();
                    final ArrayList<Pokemon> listaPokemon = pokemonRespueta.getResults();
                    Toast.makeText(MainActivity.this, "Estás fino manito", Toast.LENGTH_SHORT).show();
                    adapter.adicionarListaPokemon(listaPokemon);
                }else {
                    Toast.makeText(MainActivity.this, "Estás bajito mano", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PokemonRespueta> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Estás bajito mano x2  "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}


//////Aprende con un proyecto desde cero Angular 8, Angular Material, Spring Boot 2, Spring Security, Servicios REST, JWT, OAUTH, DigitalOcean, Microservicios Spring Cloud y más!