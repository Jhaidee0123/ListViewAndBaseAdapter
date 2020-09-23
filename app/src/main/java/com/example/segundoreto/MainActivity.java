package com.example.segundoreto;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.segundoreto.adapters.AnimalAdapter;
import com.example.segundoreto.entity.Animal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView listViewAnimals;
    public SearchView txtSearch;
    private MediaPlayer reproducer;
    private AnimalAdapter animalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewAnimals = findViewById(R.id.listViewAnimals);
        txtSearch = findViewById(R.id.txtSearch);
        animalAdapter = new AnimalAdapter(this, loadInfo());
        listViewAnimals.setAdapter(animalAdapter);
        listViewAnimals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                reproducer = MediaPlayer.create(getApplicationContext(), animalAdapter.getSoundFromFilterList(i));
                reproducer.start();
            }
        });
        txtSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                animalAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private List<Animal> loadInfo() {
        List<Animal> animalsList = new ArrayList<>();
        animalsList.add(new Animal(R.drawable.leon, "León", "El león (Panthera leo) es un mamífero carnívoro de la familia de los félidos y una de las cinco especies del género Panthera. Los leones salvajes viven en poblaciones cada vez más dispersas y fragmentadas del África subsahariana.", R.raw.leonsonido));
        animalsList.add(new Animal(R.drawable.dog, "Perro", "El perro (Canis lupus familiaris), llamado perro doméstico o can, y en algunos lugares coloquialmente llamado chucho, tuso, choco, entre otros; es un mamífero carnívoro de la familia de los cánidos, que constituye una subespecie del lobo.", R.raw.perrosonido));
        animalsList.add(new Animal(R.drawable.rino, "Rinoceronte", "Los rinocerótidos, conocidos con el nombre de rinocerontes, son una familia de mamíferos placentarios del suborden ceratomorfos perteneciente al orden de los perisodáctilos.", R.raw.rinosonido));
        return animalsList;
    }
}