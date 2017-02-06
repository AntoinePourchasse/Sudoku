package com.example.antoine.sudoku;

/**
 * Created by Antoine on 04/02/2017.
 */

public class GrilleDepart {

    public int[][] getGrille(String string){
        int[][] grille = new int[9][9];
        int compteur = 0;


        for(int i = 0; i < 9; i++) {
            for(int o = 0; o < 9; o++){

                char test = string.charAt(compteur);


                grille[i][o] = Integer.valueOf(Integer.parseInt(String.valueOf(test)));
                compteur += 1;

            }

        }
        return grille;

    }

}
