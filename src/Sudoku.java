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
}
