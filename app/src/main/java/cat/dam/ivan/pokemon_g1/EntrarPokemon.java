package cat.dam.ivan.pokemon_g1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cat.dam.ivan.pokemon_g1.database.DataBaseHelper;

public class EntrarPokemon extends AppCompatActivity
{
    private EditText etNom, etGeneracio;
    private Spinner spTipus1, spTipus2;
    private Button btnAfegir, btnTornarMenu;
    private DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrada_pokemon);
        initViews();
        initListeners();
        initDB();

    }

    private void initViews() {
        etNom = findViewById(R.id.et_nom);
        spTipus1 = findViewById(R.id.sp_tipus1);
        spTipus2 = findViewById(R.id.sp_tipus2);
        etGeneracio = findViewById(R.id.et_generacio);
        btnAfegir = findViewById(R.id.btn_afegir);
        btnTornarMenu = findViewById(R.id.btn_menuPrincipal);
    }

    private void initListeners() {
        btnAfegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = etNom.getText().toString();
                /*String tipus1 = etTipus1.getText().toString();
                String tipus2 = etTipus2.getText().toString();*/
                String tipus1 = spTipus1.getSelectedItem().toString();
                String tipus2 = spTipus2.getSelectedItem().toString();
                String generacio = etGeneracio.getText().toString();
                Pokemon pokemon = new Pokemon(nom, tipus1, tipus2, generacio);
                dataBaseHelper.addPokemon(pokemon);
                Toast.makeText(EntrarPokemon.this, "Pokemon afegit", Toast.LENGTH_SHORT).show();
            }
        });
        btnTornarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EntrarPokemon.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDB() {
        dataBaseHelper = new DataBaseHelper(this);
    }



}
