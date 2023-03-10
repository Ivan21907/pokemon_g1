package cat.dam.ivan.pokemon_g1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import cat.dam.ivan.pokemon_g1.Pokemon;

public class DataBaseHelper extends SQLiteOpenHelper
{

    // Database Version
    private	static final int DB_VERSION =	7;
    // Database Name
    private	static final String DB_NAME = "Pokemons";
    // Table Name
    private	static final String TABLE_NAME = "Pokemon";
    // Table Columns
    private static final String COLUMN_POKEMON_NUMBER = "pokemon_number";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TYPE1 = "type1";
    private static final String COLUMN_TYPE2 = "type2";
    private static final String COLUMN_GENERATION = "generation";

    // Constructor
    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Create table if not exists
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COLUMN_POKEMON_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + COLUMN_NAME + " TEXT NOT NULL," + COLUMN_TYPE1 + " TEXT NOT NULL," + COLUMN_TYPE2 + " TEXT NOT NULL," + COLUMN_GENERATION + " TEXT NOT NULL" + " )";
        db.execSQL(query);
    }

    // Drop table if exists
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    //add a pokemon to the database
    public void addPokemon(Pokemon item){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_TYPE1, item.getType1());
        values.put(COLUMN_TYPE2, item.getType2());
        values.put(COLUMN_GENERATION, item.getGeneration());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
    }

    //delete a pokemon from the database
    public void deletePokemon(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_POKEMON_NUMBER + " = " + id);
    }

    //edit a pokemon from the database
    public void editPokemon(Pokemon item){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_TYPE1, item.getType1());
        values.put(COLUMN_TYPE2, item.getType2());
        values.put(COLUMN_GENERATION, item.getGeneration());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, values, COLUMN_POKEMON_NUMBER + " = ?", new String[]{String.valueOf(item.getPokemon_number())});
    }

    //get all pokemons from the database
    public ArrayList<Pokemon> getPokemons(){
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                int number = Integer.parseInt(cursor.getString(0));
                String nom = cursor.getString(1);
                String tipus1 = cursor.getString(2);
                String tipus2 = cursor.getString(3);
                String generacio = cursor.getString(4);
                Pokemon pok = new Pokemon(number, nom, tipus1, tipus2, generacio);
                pokemons.add(pok);
            }while(cursor.moveToNext());
        }
        return pokemons;
    }

    //get all pokemons from the database if the generation is greater than 0
    public ArrayList<Pokemon> getPokemonsByName(){
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_GENERATION + " > '0'";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            int number = Integer.parseInt(cursor.getString(0));
            String nom = cursor.getString(1);
            String tipus1 = cursor.getString(2);
            String tipus2 = cursor.getString(3);
            String generacio = cursor.getString(4);
            Pokemon pok = new Pokemon(number, nom, tipus1, tipus2, generacio);
            pokemons.add(pok);
        }
        return pokemons;
    }
}
