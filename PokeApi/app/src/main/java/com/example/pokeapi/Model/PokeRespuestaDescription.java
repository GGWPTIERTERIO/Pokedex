package com.example.pokeapi.Model;

import java.util.ArrayList;

public class PokeRespuestaDescription {

    private ArrayList<PokemonDescription> flavor_text_entries;

    public ArrayList<PokemonDescription> getFlavor_text_entries() {
        return flavor_text_entries;
    }

    public void setFlavor_text_entries(ArrayList<PokemonDescription> flavor_text_entries) {
        this.flavor_text_entries = flavor_text_entries;
    }
}
