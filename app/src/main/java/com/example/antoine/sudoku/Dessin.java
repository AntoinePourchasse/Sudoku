package com.example.antoine.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class Dessin extends View implements View.OnTouchListener {
    int border = 3; // rectBorder
    int borderCadre = 8;
    int largeur;
    int hauteur;
    int posXTouch;
    int posYTouch;
    int nombreSelectionner;
    Paint paintTexte = new Paint();
    Paint paintTexteDepart = new Paint();
    Paint paint = new Paint();
    Paint paintBackgroundBas = new Paint();
    Paint paintCadre = new Paint();
    Paint paintError = new Paint();
    int textSize = 80;
    int BarBas;
    int cote;
    int[][] vGrille = new int[9][9];
    String res;
    GrilleDepart grille = new GrilleDepart();
    Verification verif = new Verification();
    int [][] grilleDepart;
    public Dessin(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(this);
    }

    @Override
    public void onDraw(Canvas canvas) {
        res = Grille.getNiveau();
        grilleDepart = grille.getGrille(res);
        largeur = canvas.getWidth();
        hauteur = canvas.getHeight();
        paintTexte.setColor(Color.BLACK);
        //Log.d("niveau",String.valueOf(niveau));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(border);
        paintCadre.setStyle(Paint.Style.STROKE);
        paintCadre.setStrokeWidth(borderCadre);
        int width = (largeur / 9);
        int height = (hauteur / 9);
        int largeurCadre = largeur/3;
        paintTexte.setTextSize(textSize);
        paintTexteDepart.setTextSize(textSize);
        paintTexteDepart.setColor(Color.BLUE);
        paintError.setTextSize(textSize);
        paintError.setColor(Color.RED);
        cote = width < height ? width : height;

        int bwd = (largeur - (9 * cote)) / 2;

        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {

                canvas.drawRect(bwd + (x - 1 * largeurCadre), border + y - 1 * largeurCadre, bwd + (x * largeurCadre), border + y * largeurCadre, paintCadre);

            }
        }

        for (int x = 1; x < 10; x++) {
            for (int y = 1; y < 10; y++) {

                canvas.drawRect(bwd + (x - 1*  cote), border + y - 1*  cote, bwd + (x * cote), border + y *  cote, paint);
                if(grilleDepart[x-1][y-1] > 0){
                    canvas.drawText(String.valueOf(grilleDepart[x-1][y-1]),(x-1)  * cote/2 + (x*cote/2)-textSize/4, y*cote - textSize + (cote / 2 - textSize/3), paintTexteDepart);
                }
                if (vGrille[x - 1][y - 1] > 0) {


                    if(verif.verifGlobal(grilleDepart,vGrille,vGrille[x-1][y-1],x-1,y-1)) {
                        canvas.drawText(String.valueOf(vGrille[x - 1][y - 1]), (x - 1) * cote / 2 + (x * cote / 2) - textSize / 4, y * cote - textSize + (cote / 2 - textSize / 3), paintTexte);
                    }
                    else{
                        canvas.drawText(String.valueOf(vGrille[x - 1][y - 1]), (x - 1) * cote / 2 + (x * cote / 2) - textSize / 4, y * cote - textSize  + (cote / 2 - textSize / 3), paintError);
                    }



                }
            }

        }
        for (int x = 1; x <= 9; x++) {
            paintBackgroundBas.setColor(Color.BLUE);
            BarBas = hauteur - 100;
            paintBackgroundBas.setStrokeWidth(2);
            paintBackgroundBas.setStyle(Paint.Style.STROKE);


            canvas.drawRect(bwd + ((x - 1) * cote), BarBas - cote, bwd + (x * cote), 2 + BarBas, paintBackgroundBas);
            canvas.drawText(String.valueOf(x), (x - 1) * cote / 2 + (x * cote / 2) - textSize / 4, BarBas - (cote / 2 - textSize / 3), paintTexte);

        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if( BarBas > y && y > BarBas - cote  ) {
                    nombreSelectionner = x / cote + 1;
                    Log.d("Nombre", String.valueOf(nombreSelectionner));
                }
                else if(y < cote*9 ){
                    posXTouch = x/cote + 1;
                    posYTouch = y/cote +1;
                    Log.d("Pos", String.valueOf(posXTouch + " " + posYTouch));
                }
                if(nombreSelectionner > 0 && posYTouch > 0 && verif.verifValeurDepart(grilleDepart,posXTouch-1,posYTouch-1))
                {
                    vGrille[posXTouch-1][posYTouch-1] = nombreSelectionner;
                    nombreSelectionner = 0;
                    posYTouch = 0;
                    posXTouch = 0;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                break;
        }
        this.invalidate();
        return true;
    }



}
