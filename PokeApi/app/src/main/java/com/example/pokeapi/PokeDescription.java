package com.example.pokeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pokeapi.Model.PokeRespuestaDescription;
import com.example.pokeapi.Model.Pokemon;
import com.example.pokeapi.Model.PokemonDescription;
import com.example.pokeapi.Model.PokemonRespueta;
import com.example.pokeapi.PokeApi.PokeApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeDescription extends AppCompatActivity {


    private ImageView imageR;
    private TextView titleR,TextDescription;
    private int number=0;
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_description);

        Bundle extras = getIntent().getExtras();



        if(extras!=null){

            final Pokemon pRecibido = (Pokemon) extras.getSerializable("pokemonS");
            titleR= findViewById(R.id.titleD);
            TextDescription = findViewById(R.id.textDescription);
            titleR.setText(pRecibido.getName().toUpperCase());
            imageR = findViewById(R.id.fotoD);

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            setUpDescription(pRecibido.getNumber());

            Glide.with(this)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pRecibido.getNumber()+".png")
                    .centerCrop()
                    .into(imageR);
                imageR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(number==0){
                        Toast.makeText(PokeDescription.this, "Shiny"+number, Toast.LENGTH_SHORT).show();
                    Glide.with(getApplicationContext())
                            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/"+pRecibido.getNumber()+".png")
                            .centerCrop()
                            .into(imageR);
                    number=1;
                    }
                    else {
                        Toast.makeText(PokeDescription.this, "No Shiny"+number, Toast.LENGTH_SHORT).show();
                        Glide.with(getApplicationContext())
                                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pRecibido.getNumber()+".png")
                                .centerCrop()
                                .into(imageR);
                        number=0;
                    }
                }
            });



        }
    }

    private void setUpDescription(int number) {
        PokeApiService service = retrofit.create(PokeApiService.class);
        Call<PokeRespuestaDescription> pokemonsDescriptionCall = service.obtenerDatosPokemon(number);

        pokemonsDescriptionCall.enqueue(new Callback<PokeRespuestaDescription>() {
            @Override
            public void onResponse(Call<PokeRespuestaDescription> call, Response<PokeRespuestaDescription> response) {
                if(response.isSuccessful()){
                    PokeRespuestaDescription resultado = response.body();
                    final ArrayList<PokemonDescription> listaPokemon = resultado.getFlavor_text_entries();
                    for(int i=0;i<listaPokemon.size();i++){
                        if(listaPokemon.get(i).getLanguage().getName().equals("es") &&
                                listaPokemon.get(i).getVersion().getName().equals("alpha-sapphire")){
                            TextDescription.setText(listaPokemon.get(i).getFlavor_text());
                        }
                    }

                }else {
                    Toast.makeText(PokeDescription.this, "Estás bajito mano", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PokeRespuestaDescription> call, Throwable t) {

            }
        });
    }
}
