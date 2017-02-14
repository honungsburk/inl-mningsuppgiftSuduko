package GUI;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by Frank Hampus Weslien on 2017-02-12.
 */
public class sudokuSolverGUI extends Application {

    Sudoku.Sudoku sudoku = new Sudoku.Sudoku();

    TilePane sudukoGrid;
    VBox root;
    HBox buttonsContainer;
    Button solve;
    Button clear;
    Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("SudokuSolver");

        root = new VBox();
        scene = new Scene(root, 600, 600);

        //SudokuGrud
        sudukoGrid = new TilePane();
        sudukoGrid.setPrefColumns(9);
        sudukoGrid.setPrefRows(9);
        sudukoGrid.setMaxSize(500,500);
        sudukoGrid.setMinSize(500,500);
        sudukoGrid.setHgap(5);
        sudukoGrid.setVgap(5);

        //bottonContainer
        buttonsContainer = new HBox();
        buttonsContainer.setAlignment(Pos.CENTER);
        buttonsContainer.setSpacing(20);

        //fix root
        root.setAlignment(Pos.CENTER);

        //buttons
        solve = new Button("Solve");
        clear = new Button("Clear");
        solve.setPrefSize(80, 50);
        clear.setPrefSize(80, 50);
        solve.setFont(Font.font(20));
        clear.setFont(Font.font(20));

        //set button actions
        clear.setOnAction(x -> {
            for (Node t : sudukoGrid.getChildren()) {
                ((OneNumberTextField) t).setText("");
            }
            sudoku.clear();
        });

        solve.setOnAction(x -> {

           sudoku.solve();
            int pos = 0;
            for(Node t: sudukoGrid.getChildren()){
                ((OneNumberTextField) t).setText(sudoku.getSolved(pos%9,pos/9) + "");
                pos++;
            }

        });

        //add buttons to container
        buttonsContainer.getChildren().addAll(solve, clear);

        //create a property so the size of the buttons change //work in progress men tanken är å få saker å ting att rezisas automatiskt
        IntegerProperty scaleFactorX = new SimpleIntegerProperty(1);
        IntegerProperty scaleFactorY = new SimpleIntegerProperty(1);


        //adds Textfields
        for (int i = 0; i < 81; i++) {
            OneNumberTextField temp = new OneNumberTextField();
            temp.setPrefSize(50,40);
            temp.setAlignment(Pos.CENTER);
            temp.setFont(Font.font(20));

            int x = i % 9;
            int y = i / 9;

            //styling
            String style = "";
            if(i < 27 || i > 53) {
                if (x < 3 || x > 5) {
                    style = "-fx-control-inner-background: #ff7900; -fx-background-radius: 0";
                    temp.setStyle(style);
                }
            } else if(x > 2 && x <6){
                style = "-fx-control-inner-background: #ff7900; -fx-background-radius: 0";
                temp.setStyle(style);
            } else {
                style = "-fx-background-radius: 0";
                temp.setStyle(style);
            }

            //Placerar inputen från detta textfält på motsvarande plats i sudokuobjektets matris.
            String finalStyle = style;
            temp.setOnKeyReleased(e -> { //borde göras till sin egen klass så den kan anropas från clear och fixa färgerna
                int value;
                try {
                    value = Integer.parseInt(temp.getText());
                } catch (NumberFormatException err) {
                    //Tom ruta lästes, sätt in noll.
                    value = 0;
                }
                if(sudoku.userIndexedInputValid(x, y, value)) {
                    sudoku.set(x, y, value);
                    System.out.println(sudoku.get(x, y));
                    temp.setStyle(finalStyle);
                } else{
                    temp.setStyle("-fx-control-inner-background: #FF0000; -fx-background-radius: 0");
                }
            });
            sudukoGrid.getChildren().add(temp);
        }

        root.getChildren().addAll(sudukoGrid,buttonsContainer);


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
