package com.example.antoine.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button level1;
    private Button level2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        level1 = (Button) findViewById(R.id.level1);
        level1.setOnClickListener(this);
        level2 = (Button) findViewById(R.id.level2);
        level2.setOnClickListener(this);
    }

    public void onClick(View v){
        Intent intention = new Intent(this, ListeGrille.class);
        Bundle bundle = new Bundle();
        int level = 0 ;
        if(v == level1) {
            level = 1;

        }
        else if(v == level2){
            level = 2;
        }
        bundle.putInt("level", level);
        intention.putExtras(bundle);
        startActivity(intention);
    }
}
