package com.example.segundoreto.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.segundoreto.R;
import com.example.segundoreto.entity.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalAdapter extends BaseAdapter implements Filterable {

    private List<Animal> animalList;
    private Context context;
    CustomFilter filter;
    private List<Animal> filterList;

    public AnimalAdapter(Context context, List<Animal> animalList){
        this.context = context;
        this.animalList = animalList;
        this.filterList = animalList;
    }

    @Override
    public int getCount() {
        return animalList.size();
    }

    @Override
    public Object getItem(int i) {
        return animalList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public int getSoundFromFilterList(int i) {
        return animalList.get(i).getSound();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Animal item = (Animal) getItem(i);

        view = LayoutInflater.from(context).inflate(R.layout.animal_item_layout, null);
        ImageView image = view.findViewById(R.id.image);
        TextView txtDescription = view.findViewById(R.id.txtDescription);
        TextView txtName = view.findViewById(R.id.txtName);

        image.setImageResource(item.getImage());
        txtDescription.setText(item.getDescription());
        txtName.setText(item.getName());
        return view;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter();
        }
        return filter;
    }

    class CustomFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();

            if (charSequence != null && charSequence.length() > 0) {
                charSequence = charSequence.toString().toUpperCase();

                List<Animal> filters = new ArrayList<Animal>();

                for (int i = 0; i < filterList.size(); i++) {
                    if (filterList.get(i).getName().toUpperCase().contains(charSequence)) {
                        Animal animal = new Animal(filterList.get(i).getImage(), filterList.get(i).getName(), filterList.get(i).getDescription(), filterList.get(i).getSound());

                        filters.add(animal);
                    }
                }

                results.count = filters.size();
                results.values = filters;
            } else {
                results.count = filterList.size();
                results.values = filterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            animalList = (List<Animal>) results.values;
            notifyDataSetChanged();
        }
    }
}
