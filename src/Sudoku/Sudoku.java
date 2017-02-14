package Sudoku;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Abstratktion av ett Sudokubräde
 * Kan innehålla värden mellan 0 och 9 där 0 representerar en tom ruta.
 * 0or ignoreras därför av rad och kolumn (etc) - metoder.
 */
public class Sudoku {
    int[][] field;

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();

         System.out.println(sudoku.solve());
        sudoku.print();
    }

    /** Main metod */
    public Sudoku() {
        field = new int[9][9];
    }

    /**
     * Hämtar en siffra från en viss plats i matrisen.
     * */
    public int get(int x, int y) {
        return field[y][x];
    }

    /**
     * Placerar en siffra på en viss plats i matrisen.
     * */
    public void set(int x, int y, int value) {
        if (value < 0 || value > 9)
            throw new IllegalArgumentException("Ogiltig input. Endast tal mellan 0 och 9. (" + value + ")");
        else field[y][x] = value;
    }

    /**
     * Kontrollerar om en rad i matrisen är korrekt, alltså inga dubbletter.
     * @param y radens y koordinat.
     * @return true om raden är korrekt, annars false.
     */
    public boolean rowValid(int y) {
        List<Integer> values = new ArrayList<>();   //lista för att jämföra

        for (int x = 0; x < field[y].length; x++) {
            if (field[y][x] != 0) {
                if (values.contains(field[y][x]))   //Om numret redan hittats i raden en gång ...
                    return false;                   //... returnerar vi false.
                else {
                    values.add(field[y][x]);        //Annars lägger vi till det i listan så länge.
                }
            }
        }
        return true;    //Inga dubbletter hittades, raden OK!
    }

    /**
     * Kontrollerar att en column har korrekta värden i sig.
     * @param x columnens x-koordinat.
     * @return true om det inte finns några dubbletter, annars false.
     */
    public boolean colValid(int x) {
        List<Integer> values = new ArrayList<>();   //lista för att jämföra

        for (int y = 0; y < field.length; y++) {
            if (field[y][x] != 0) {
                if (values.contains(field[y][x]))   //Om numret redan hittats i raden en gång ...
                    return false;                   //... returnerar vi false.
                else {
                    values.add(field[y][x]);        //Annars lägger vi till det i listan så länge.
                }
            }
        }
        return true;    //Inga dubbletter hittades, columnen OK!
    }
    /**
     * Returnerar true om alla rutor i ett "fält" innehåller unika värden.
     * Returnerar false om det finns dubbletter.
     * Parametrar x och y är en enskild rutas (OBS ej fälts) koordinater.
     * */
    public boolean fieldValid(int x, int y) {
        Set<Integer> values = new HashSet<>();

        int fieldModX = x / 3;  //Ger värdet 0, 1, eller 2, vilket motsvarar
        int fieldModY = y / 3;  //fältets koordinater. (Tänk 0,1,2 / 3 = 0, osv...)

        for (int i = 0; i < 3; i++) {                                       //Vi går igenom tre gånger tre rutor.
            for (int j = 0; j < 3; j++) {
                int value = field[i + 3 * fieldModY][j + 3 * fieldModX];    //Den exakta koordinaten bestäms av fieldMod.
                if (value != 0) {
                    if (!values.add(value))         //Om numret redan hittats i raden en gång ...
                        return false;                   //... returnerar vi false.
                }
            }
        }
        return true;
    }
    boolean[][] userInput;

    public boolean solve(){
        userMatch();
        int[][] matrix = field; // kanske inte kopierar som vi vill
        return solve(0, matrix);
    }

    private boolean solve(int pos, int[][] matrix){
        int x = pos%9;
        int y = pos/9;

        if(y < 9){
            if(userInput[y][x]){
                return solve(pos + 1, matrix);
            } else {
                for (int i = 1; i <= 9; i++) {
                    matrix[y][x] = i;
                    if (colValid(x) && rowValid(y) && fieldValid(x, y))
                        if (solve(pos + 1, matrix)) return true;
                }
                matrix[y][x] = 0;
                System.out.println();
                print();
                return false;
            }
        } else return true;

    }

    private void userMatch(){
        userInput = new boolean[9][9];

        for(int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++){
                userInput[y][x] = field[y][x] != 0;
            }
        }
    }


    private void print(){

        for (int i = 0; i <9; i++){
            for (int j = 0; j < 9; j++){
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }
}
