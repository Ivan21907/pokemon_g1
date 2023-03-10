package cat.dam.ivan.pokemon_g1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cat.dam.ivan.pokemon_g1.adapters.CustomRecyclerView;
import cat.dam.ivan.pokemon_g1.database.DataBaseHelper;

public class CercarPokemon extends AppCompatActivity
{
    //Atributs
    private Button btnBack;
    private CustomRecyclerView mAdapter;
    private RecyclerView rc_nameList;
    private DataBaseHelper dataBaseHelper;

    //metode onCreate que s'executa quan s'obre l'activitat
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cercar_pokemon);
        initViews();
        initListeners();
        initRecyclerView();
        initDataBaseHelper();
    }

    //metode que inicialitza els views
    private void initViews() {
        rc_nameList = findViewById(R.id.rc_nameList);
        btnBack = findViewById(R.id.btn_menuPrincipal);
    }

    //metode que inicialitza els listeners
    private void initListeners() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CercarPokemon.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //metode que inicialitza el RecyclerView
    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rc_nameList.setLayoutManager(linearLayoutManager);
        rc_nameList.setHasFixedSize(true);

    }

    //metode que inicialitza la base de dades i mostra els pokemons
    private void initDataBaseHelper() {
        dataBaseHelper = new DataBaseHelper(this);
        ArrayList<Pokemon> pok = dataBaseHelper.getPokemons();
        if(pok.size() > 0){
            rc_nameList.setVisibility(View.VISIBLE);
            mAdapter = new CustomRecyclerView(this, pok);
            rc_nameList.setAdapter(mAdapter);
        }else{
            rc_nameList.setVisibility(View.GONE);
            Toast.makeText(this, "No hi ha pokemons en la bdd", Toast.LENGTH_SHORT).show();

        }


    }


}
