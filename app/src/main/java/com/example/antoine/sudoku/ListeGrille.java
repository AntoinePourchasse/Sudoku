package com.example.antoine.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

class monObject {
    int level;
    int reussite;
    int niveau;

    public monObject(int niveau, int level) {
        this.niveau = niveau;
        this.level = level;
    }

    public String toString() {
        return "niveau " + niveau + " level " + level;
    }
}


public class ListeGrille extends AppCompatActivity implements ListView.OnItemClickListener {
    ArrayList<String> levels = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_grille);
        Bundle bundle = this.getIntent().getExtras();
        int level = bundle.getInt("level");

        String fichier = "toto";
        int longFichier = 0;

        if (level == 1) {
            try {
                InputStream myInput = getResources().getAssets().open("0.txt");
                fichier = readFileAsString(myInput);
                levels = new ArrayList<>(Arrays.asList(fichier.split("\r\n")));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                InputStream myInput = getResources().getAssets().open("1.txt");
                fichier = readFileAsString(myInput);
                levels = new ArrayList<>(Arrays.asList(fichier.split("\r\n")));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        final ListView listeNiveaux = (ListView) findViewById(R.id.listeNiveaux);
        final ArrayList<monObject> values = new ArrayList<>();
        int index = 0;
        for (String lvl : levels) {
            values.add(new monObject(index++, level));
        }
        ArrayAdapter<monObject> adapter = new ArrayAdapter<monObject>(this,
                android.R.layout.simple_list_item_1, values);
        listeNiveaux.setAdapter(adapter);

        listeNiveaux.setOnItemClickListener(this);
    }

    public String readFileAsString(InputStream filePath)
            throws java.io.IOException {
        StringBuilder donneFichier = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(filePath));
        char[] buf = new char[2];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != 1) {
            donneFichier.append(buf, 0, numRead);
        }
        reader.close();
        return donneFichier.toString();

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intention = new Intent(ListeGrille.this, Grille.class);
        String gDepart;
        gDepart = levels.get(position);
        Bundle bundle = new Bundle();
        Log.d("gDepart", gDepart);
        bundle.putString("level", gDepart.toString());
        intention.putExtras(bundle);
        startActivity(intention);
    }
}
