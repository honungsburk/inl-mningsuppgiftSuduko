package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Frank Hampus Weslien on 2017-02-12.
 */
public class sudukoSolverGUI extends Application {

    TilePane sudukoGrid;
    VBox root;
    HBox buttonsContainer;
    Button solve;
    Button clear;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("SudukoSolver");

        root = new VBox();

        sudukoGrid = new TilePane();
        sudukoGrid.setPrefColumns(9);
       // sudukoGrid.setPrefRows(9);
        //sudukoGrid.se
        sudukoGrid.setHgap(5);
        sudukoGrid.setVgap(5);

        buttonsContainer = new HBox();


        //buttons
        solve = new Button("Solve");
        clear = new Button("Clear");
        buttonsContainer.getChildren().addAll(solve, clear);

        //add Textfields
        for (int i = 0; i < 81; i++) {
            OneNumberTextField temp = new OneNumberTextField();
            temp.setPrefSize(50,40);
            sudukoGrid.getChildren().add(temp);
        }

        root.getChildren().addAll(sudukoGrid,buttonsContainer);

        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }
}
