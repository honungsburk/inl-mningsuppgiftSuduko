package Sudoku;

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

    private int[][] sudokuMatrix;
    private int[][] solvedSudoku;
    boolean[][] userInput;

    /** Main metod */
    public Sudoku() {
        solvedSudoku = new int[9][9];
        sudokuMatrix = new int[9][9];
    }

    public int[][] getSolvedSudoku() {
        return copy(solvedSudoku);
    }


    /**
     * Hämtar en siffra från en viss plats i Solved matrisen matrisen.
     * */
    public int getSolved(int x, int y) { //borde ändra är förvirrande att de är omkastade
        return solvedSudoku[y][x];
    }

    /**
     * Hämtar en siffra från en viss plats i matrisen.
     * */
    public int get(int x, int y) { //borde ändra är förvirrande att de är omkastade
        return sudokuMatrix[y][x];
    }

    /**
     * Placerar en siffra på en viss plats i matrisen.
     * */
    public void set(int x, int y, int value) {
        if (value < 0 || value > 9)
            throw new IllegalArgumentException("Ogiltig input. Endast tal mellan 0 och 9. (" + value + ")");
        else sudokuMatrix[y][x] = value;
    }

    /**
     * Kontrollerar om en rad i matrisen är korrekt, alltså inga dubbletter.
     * @param y radens y koordinat.
     * @return true om raden är korrekt, annars false.
     */
     private boolean rowValid(int y, int[][] matrix) {
        List<Integer> values = new ArrayList<>();   //lista för att jämföra

        for (int x = 0; x < matrix[y].length; x++) {
            if (matrix[y][x] != 0) {
                if (values.contains(matrix[y][x]))   //Om numret redan hittats i raden en gång ...
                    return false;                   //... returnerar vi false.
                else {
                    values.add(matrix[y][x]);        //Annars lägger vi till det i listan så länge.
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
    private boolean colValid(int x, int[][] matrix) {
        List<Integer> values = new ArrayList<>();   //lista för att jämföra

        for (int y = 0; y < matrix.length; y++) {
            if (matrix[y][x] != 0) {
                if (values.contains(matrix[y][x]))   //Om numret redan hittats i raden en gång ...
                    return false;                   //... returnerar vi false.
                else {
                    values.add(matrix[y][x]);        //Annars lägger vi till det i listan så länge.
                }
            }
        }
        return true;    //Inga dubbletter hittades, columnen OK!
    }

    /**
     * Kontrollerar att ett fält (3x3) rutor ej innehåller dubbletter.
     * @param x en rutas (OBS: ej fälts) x-koordinat.
     * @param y en rutas (OBS: ej fälts) y-koordinat.
     * @param matrix matrisen vi vill kontrollera.
     * @return true om fältet ej innehåller dubbletter, annars false.
     */
    private boolean fieldValid(int x, int y, int[][] matrix) {
        Set<Integer> values = new HashSet<>();

        int fieldModX = x / 3;  //Ger värdet 0, 1, eller 2, vilket motsvarar
        int fieldModY = y / 3;  //fältets koordinater. (Tänk 0,1,2 / 3 = 0, osv...)

        for (int i = 0; i < 3; i++) {                                       //Vi går igenom tre gånger tre rutor.
            for (int j = 0; j < 3; j++) {
                int value = matrix[i + 3 * fieldModY][j + 3 * fieldModX];    //Den exakta koordinaten bestäms av fieldMod.
                if (value != 0) {
                    if (!values.add(value))         //Om numret redan hittats i raden en gång ...
                        return false;                   //... returnerar vi false.
                }
            }
        }
        return true;
    }

    /**
     * Checks if placing a value at a specific position would follow the rules.
     * @param x The x position (column).
     * @param y The y position (row).
     * @param value The value the user wants to enter.
     * @return true if the input is valid, otherwise false.
     */
    public boolean userIndexedInputValid(int x, int y, int value){
        int[][] test = copy(sudokuMatrix);
        test[y][x] = value;
        return fieldValid(x, y, test) && colValid(x, test) && rowValid(y, test);
    }


    /**
     * Solves the sudoku puzzle.
     * @return true if the puzzle was solved, otherwise false.
     */
    public boolean solve(){
        userInputs();
        int[][] matrix = copy(sudokuMatrix);
        print(matrix);
        print(sudokuMatrix);
        boolean result = solve(0, matrix);
        solvedSudoku = matrix;
        return result;
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
                    if (colValid(x, matrix) && rowValid(y, matrix) && fieldValid(x, y, matrix))
                        if (solve(pos + 1, matrix)) return true;
                }
                matrix[y][x] = 0;
                System.out.println();
                print(matrix);
                return false;
            }
        } else return true;

    }

    /**
     * creates a matrix with booleans representing if a value is a user input value.
     */
    private void userInputs(){
        userInput = new boolean[9][9];

        for(int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++){
                userInput[y][x] = sudokuMatrix[y][x] != 0;
            }
        }
    }

    /**
     * Makes a copy of a matrix
     * @param original The matrix you wan't to copy.
     * @return A copy of the original.
     */
    private int[][] copy(int[][] original){
        int[][] temp = new int[9][9];

        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                temp[y][x] = original[y][x];
            }
        }
        return  temp;
    }

    /**
     * Clears the sudokuMatrix.
     */
    public void clear(){
        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                sudokuMatrix[y][x] = 0;
            }
        }
    }

    /**
     *  clears a specific index in the sudokuMatrix.
     * @param x the column
     * @param y the row
     */
    public void clearAt(int x, int y){
        sudokuMatrix[y][x] = 0;
    }

    /**
     * Prints out a matrix
     * @param matrix the matrix you want to print
     */
    private void print(int[][] matrix){
        for (int i = 0; i <9; i++){
            for (int j = 0; j < 9; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
