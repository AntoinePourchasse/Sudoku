package com.example.antoine.sudoku;


public class Verification {

        public boolean verifValeurDepart(int[][] grilleDepart, int posX, int posY) {


            return grilleDepart[posX][posY] == 0;
        }

        public boolean verifGlobal(int[][] grilleDepart, int[][] vGrille, int nombre, int posX, int posY) {
            return verifLigne(grilleDepart, vGrille, posX, posY, nombre);
        }


        public boolean verifLigne(int[][] grilleDepart, int[][] vGrille, int posX, int posY, int nombreSelectionne) {

            boolean possible = true;
            int compteur = 0;

            while (possible && compteur < 9) {
                if (grilleDepart[compteur][posY] == nombreSelectionne
                        || grilleDepart[posX][compteur] == nombreSelectionne
                        || vGrille[compteur][posY] == nombreSelectionne && compteur != posX
                        || vGrille[posX][compteur] == nombreSelectionne && compteur != posY
                        ) {
                    possible = false;
                }
                compteur += 1;
            }

            return possible;
        }

}
