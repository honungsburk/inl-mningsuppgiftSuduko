import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tobbe on 2017-02-12.
 */
public class Sudoku {
    int[][] field;

    public int get(int x, int y) {
        return field[y][x];
    }

    public void set(int x, int y, int value) {
        if (value < 1 || value > 9)
            throw new IllegalArgumentException("Ogiltig input. Endast tal mellan 1 och 9.");
        else field[y][x] = value;
    }

    public boolean rowValid(int y) {
        List<Integer> values = new ArrayList<>();   //lista för att jämföra

        for (int x = 0; x < field[y].length; x++) {
            if (values.contains(field[y][x]))   //Om numret redan hittats i raden en gång ...
                return false;                   //... returnerar vi false.
            else {
                values.add(field[y][x]);        //Annars lägger vi till det i listan så länge.
            }
        }
        return true;    //Inga dubbletter hittades, raden OK!
    }

    public boolean colValid(int x) {
        List<Integer> values = new ArrayList<>();   //lista för att jämföra

        for (int y = 0; y < field.length; y++) {
            if (values.contains(field[y][x]))   //Om numret redan hittats i raden en gång ...
                return false;                   //... returnerar vi false.
            else {
                values.add(field[y][x]);        //Annars lägger vi till det i listan så länge.
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
        List<Integer> values = new ArrayList<>();

        int fieldModX = x / 3;  //Ger värdet 0, 1, eller 2, vilket motsvarar
        int fieldModY = y / 3;  //fältets koordinater. (Tänk 0,1,2 / 3 = 0, osv...)

        for (int i = 0; i < 3; i++) {                                       //Vi går igenom tre gånger tre rutor.
            for (int j = 0; j < 3; j++) {
                int value = field[i + 3 * fieldModX][j + 3 * fieldModY];    //Den exakta koordinaten bestäms av fieldMod.
                if (values.contains(value))         //Om numret redan hittats i raden en gång ...
                    return false;                   //... returnerar vi false.
                else {
                    values.add(value);              //Annars lägger vi till det i listan så länge.
                }
            }
        }
        return true;
    }
}
