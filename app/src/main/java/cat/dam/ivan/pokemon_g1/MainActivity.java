package cat.dam.ivan.pokemon_g1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    private Button btnEntrarPokemon, btnCercarPokemon, btnCercarPokemonItv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
    }

    private void initViews() {
        btnEntrarPokemon = findViewById(R.id.btn_entrada);
        btnCercarPokemon = findViewById(R.id.btn_cercar);
        btnCercarPokemonItv = findViewById(R.id.btn_cercar_tipus);
    }

    private void initListeners()
    {
        btnEntrarPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EntrarPokemon.class);
                startActivity(intent);
            }
        });
        btnCercarPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CercarPokemon.class);
                startActivity(intent);
            }
        });
        btnCercarPokemonItv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConsultaPokemons.class);
                startActivity(intent);
            }
        });
    }


}