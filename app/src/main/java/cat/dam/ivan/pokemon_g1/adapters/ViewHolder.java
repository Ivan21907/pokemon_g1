package cat.dam.ivan.pokemon_g1.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import cat.dam.ivan.pokemon_g1.R;

public class ViewHolder extends RecyclerView.ViewHolder
{

    //Aquí obtenim la referència als nostres elements visuals
    private final TextView pokemonName;
    private final ImageView img_delete;
    private final ImageView img_edit;

    //Constructor
    public ViewHolder(View itemView)
    {
        super(itemView);
        pokemonName = itemView.findViewById(R.id.tv_pokemon_name);
        img_delete = itemView.findViewById(R.id.img_delete);
        img_edit = itemView.findViewById(R.id.img_edit);
    }
    //Getters
    public TextView getPokemonName()
    {
        return pokemonName;
    }
    public ImageView getDeleteImg()
    {
        return img_delete;
    }
    public ImageView getEditImg()
    {
        return img_edit;
    }
}
