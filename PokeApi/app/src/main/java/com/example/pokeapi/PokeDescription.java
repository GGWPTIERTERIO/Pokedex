package com.example.pokeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pokeapi.Model.Pokemon;

public class PokeDescription extends AppCompatActivity {


    private ImageView imageR;
    private TextView titleR;
    private int number=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_description);

        Bundle extras = getIntent().getExtras();

        if(extras!=null){

            final Pokemon pRecibido = (Pokemon) extras.getSerializable("pokemonS");
            titleR= findViewById(R.id.titleD);
            titleR.setText(pRecibido.getName().toUpperCase());
            imageR = findViewById(R.id.fotoD);

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
}
