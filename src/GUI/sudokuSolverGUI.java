package GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
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

        buttonsContainer.getChildren().addAll(solve, clear);

        //adds Textfields
        for (int i = 1; i < 82; i++) {
            OneNumberTextField temp = new OneNumberTextField();
            temp.setPrefSize(50,40);
            temp.setAlignment(Pos.CENTER);
            temp.setFont(Font.font(20));

            //styling
            int multOfNine = i%9;
            if(i < 28 || i > 54) {
                if (multOfNine < 4 || multOfNine > 6) {
                    temp.setStyle("-fx-control-inner-background: #ff7900; -fx-background-radius: 0");
                }
            } else if(multOfNine > 3 && multOfNine <7){
                temp.setStyle("-fx-control-inner-background: #ff7900; -fx-background-radius: 0");
            } else {
                temp.setStyle("-fx-background-radius: 0");
            }

            sudukoGrid.getChildren().add(temp);
        }

        root.getChildren().addAll(sudukoGrid,buttonsContainer);

        scene = new Scene(root, 600, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
