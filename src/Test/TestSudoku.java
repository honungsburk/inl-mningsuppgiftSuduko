package Test;

import static org.junit.Assert.*;

import Sudoku.Sudoku;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSudoku {
    Sudoku sudoku;
    @Before
    public void setUp() throws Exception {
        int[][] matrix = new int[][]{
            new int[] {0,0,0,0,0,0,1,8,4},
            new int[] {0,5,0,0,9,0,0,0,0},
            new int[] {0,0,6,0,0,8,0,0,0},
            new int[] {2,0,0,0,5,0,0,9,3},
            new int[] {0,0,7,8,4,0,2,0,0},
            new int[] {1,0,0,0,0,0,0,0,8},
            new int[] {6,0,0,1,0,2,7,0,0},
            new int[] {7,3,0,0,0,0,0,0,5},
            new int[] {0,0,4,6,0,0,0,0,0},
        };
        sudoku = new Sudoku(matrix);
    }

    @After
    public void tearDown() throws Exception {
        sudoku = null;
    }

    @Test
    public void testSolve() {
        System.out.println(sudoku.solve());
        int[][] correctSolve = new int[][]{
                new int[] {3,7,9,5,2,6,1,8,4},
                new int[] {8,5,1,4,9,7,3,2,6},
                new int[] {4,2,6,3,1,8,9,5,7},
                new int[] {2,6,8,7,5,1,4,9,3},
                new int[] {5,9,7,8,4,3,2,6,1},
                new int[] {1,4,3,2,6,9,5,7,8},
                new int[] {6,8,5,1,3,2,7,4,9},
                new int[] {7,3,2,9,8,4,6,1,5},
                new int[] {9,1,4,6,7,5,8,3,2},
        };

        for(int i= 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals("Sudoku är inte korrekt.", correctSolve[i][j], sudoku.getSolved(j, i));
            }
        }
    }

}
