package com.example.pokeapi.PokeApi;
import com.example.pokeapi.Model.PokeRespuestaDescription;
import com.example.pokeapi.Model.PokemonRespueta;

import java.lang.reflect.Array;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeApiService {

    @GET("pokemon")
    Call<PokemonRespueta> obtenerListaPokemon(@Query("limit") int limit,@Query("offset") int offset);

    @GET("pokemon-species/{id}")
    Call<PokeRespuestaDescription> obtenerDatosPokemon(@Path("id") int itemId);
}
