package cat.dam.ivan.pokemon_g1;

public class Pokemon
{
    //Atributs
    private int pokemon_number;
    private String name;
    private String type1;
    private String type2;
    private String generation;

    //Constructors
    public Pokemon(int pokemon_number, String name, String type1, String type2, String generation) {
        this.pokemon_number = pokemon_number;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.generation = generation;
    }

    public Pokemon(String name, String type1, String type2, String generation) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.generation = generation;
    }

    public Pokemon() {
    }

    //Getters and Setters
    public int getPokemon_number() {
        return pokemon_number;
    }

    public void setPokemon_number(int pokemon_number) {
        this.pokemon_number = pokemon_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }
}
