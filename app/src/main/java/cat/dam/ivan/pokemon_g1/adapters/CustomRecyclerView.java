package cat.dam.ivan.pokemon_g1.adapters;


import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import cat.dam.ivan.pokemon_g1.Pokemon;
import cat.dam.ivan.pokemon_g1.R;
import cat.dam.ivan.pokemon_g1.database.DataBaseHelper;

public class CustomRecyclerView extends RecyclerView.Adapter<ViewHolder> implements Filterable{
    //Aquesta classe és la que s'encarrega de mostrar els items del RecyclerView
    private Context context;
    //Aquesta és la llista de items que mostrarem
    private ArrayList<Pokemon> dataSet;
    private ArrayList<Pokemon> itemList;
    //Aquesta és la classe que s'encarrega de gestionar la base de dades
    private DataBaseHelper databaseHelper;

    //Constructor, aquí passem els ítems que mostrarem, és a dir, el model de dades
    public CustomRecyclerView(Context context, ArrayList<Pokemon> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
        this.itemList = dataSet;
        databaseHelper = new DataBaseHelper(context);
    }

    //metode que filtrara pel nom del pokemon
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    dataSet = itemList;
                } else {
                    ArrayList<Pokemon> filteredList = new ArrayList<>();
                    for (Pokemon row : itemList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    dataSet = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dataSet;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataSet = (ArrayList<Pokemon>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_pokemon, parent, false);
        return new ViewHolder(view);
    }

    //
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //Donem valor als views de cada card mitjançant el ViewHolder
        final Pokemon pok = dataSet.get(position);
        holder.getPokemonName().setText(pok.getName());
        holder.getEditImg().setOnClickListener(view -> showEditionDialog(pok));
        holder.getDeleteImg().setOnClickListener(view -> {
            //elimina fila de la base de dades
            databaseHelper.deletePokemon(pok.getPokemon_number());
            //refresca la pàgina de l'activity per veure canvis
            ((Activity)context).finish();
            context.startActivity(((Activity) context).getIntent());
        });

    }

    //Retorna el número d'items que hi ha a la llista
    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    //Mostra el diàleg d'edició que permet editar un pokemon
    private void showEditionDialog(final Pokemon item){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.dialog_update_pokemon, null);
        final EditText et_name = subView.findViewById(R.id.et_name);
        final Spinner spTipus1 = subView.findViewById(R.id.sp_tipus1);
        final Spinner spTipus2 = subView.findViewById(R.id.sp_tipus2);
        /*final EditText et_type1 = subView.findViewById(R.id.et_type1);
        final EditText et_type2 = subView.findViewById(R.id.et_typesecond);*/
        final EditText et_generation = subView.findViewById(R.id.et_generation);
        if(item != null)
        {
            et_name.setText(item.getName());
            spTipus1.setSelection(Integer.parseInt(item.getType1()));
            spTipus2.setSelection(Integer.parseInt(item.getType2()));
            /*et_type1.setText(item.getType1());
            et_type2.setText(item.getType2());*/
            et_generation.setText(item.getGeneration());
        }
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Edit Pokemon");
        alertDialog.setView(subView);
        alertDialog.create();
        alertDialog.setPositiveButton("Edicio de Pokemon", (dialog, which) -> {
            final String name = et_name.getText().toString();
            /*final String type1 = et_type1.getText().toString();
            final String type2 = et_type2.getText().toString();*/
            final String type1 = String.valueOf(spTipus1.getSelectedItemPosition());
            final String type2 = String.valueOf(spTipus2.getSelectedItemPosition());
            final String generation = et_generation.getText().toString();

            if(TextUtils.isEmpty(name)){
                Toast.makeText(context, "no s'ha pogut afegir el pokemon", Toast.LENGTH_LONG).show();
            }
            else{
                if (item != null) {
                    databaseHelper.editPokemon((new Pokemon(item.getPokemon_number(), name, type1, type2, generation)));
                    //refresh the activity
                    ((Activity) context).finish();
                    context.startActivity(((Activity) context).getIntent());
                }
                else {
                    Toast.makeText(context, "Pokemon Null", Toast.LENGTH_LONG).show();
                }
            }
        });
        alertDialog.setNegativeButton("Cancelar", (dialog, which) -> Toast.makeText(context, "Tasca Cancelada", Toast.LENGTH_LONG).show());
        alertDialog.show();
    }

}