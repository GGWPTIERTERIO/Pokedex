package com.example.pokeapi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokeapi.Model.Pokemon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class AdapterPokeApi extends RecyclerView.Adapter<AdapterPokeApi.AdapterPokeApiViewHolder> {

    private Context mContext;
    private ArrayList<Pokemon> data;



    public AdapterPokeApi(Context mContext){
        data = new ArrayList<>();
        this.mContext=mContext;
    }


    @NonNull
    @Override
    public AdapterPokeApiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon,parent,false);
        return new AdapterPokeApiViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterPokeApiViewHolder holder, int position) {

        final Pokemon p = data.get(position);

        holder.nombre.setText(p.getName()+" "+p.getNumber());
        Glide.with(mContext)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+p.getNumber()+".png")
                .centerCrop()
                .into(holder.foto);
        holder.foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, ""+p.getName() + " " + p.getNumber(), Toast.LENGTH_SHORT).show();
                Intent lol = new Intent(mContext,PokeDescription.class);
                Pokemon pokemonSeleccionado = p;
                Bundle b = new Bundle();
                b.putSerializable("pokemonS",pokemonSeleccionado);
                lol.putExtras(b);
                mContext.startActivity(lol);
            }
        });

   /*     if(p.getNumber()<10 && p.getNumber()>0) {
            Glide.with(mContext)
                    .load("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/00" + p.getNumber()+".png")
                    .centerCrop()
                    .into(holder.foto);
        }
        if(p.getNumber()<100 && p.getNumber()>9) {
            Glide.with(mContext)
                    .load("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/0" + p.getNumber()+".png")
                    .centerCrop()
                    .into(holder.foto);
        }
        if(p.getNumber()<1000 && p.getNumber()>99) {
            Glide.with(mContext)
                    .load("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/" + p.getNumber()+".png")
                    .centerCrop()
                    .into(holder.foto);
        }*/

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
        data.addAll(listaPokemon);
        notifyDataSetChanged();
    }


    public class AdapterPokeApiViewHolder extends RecyclerView.ViewHolder{

        private TextView nombre;
        private ImageView foto;

        public AdapterPokeApiViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.tvNombrePokeApi);
            foto = itemView.findViewById(R.id.fotoPokeApi);

        }
    }
}
