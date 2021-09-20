/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author Asus
 */
public class JarrasDeAguaFx extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        VBox containerOptions = new VBox(5);
        StackPane recordRules = new StackPane();
        Pane canvasContainer = new Pane();
        VBox sidebarContainer1 = new VBox(5);
        VBox sidebarContainer2 = new VBox(5);
        VBox sidebarContainer3 = new VBox(5);
        HBox buttonsContainer = new HBox(5);

        ListView<Record> records = new ListView<>();
        
        Button btnPrev = new Button("Anterior");
        btnPrev.setId("btnPrev");
        btnPrev.getStyleClass().add("btn");
        
        
        Button btnNext = new Button("Siguiente");
        btnNext.setId("btnNext");
        btnNext.getStyleClass().add("btn");
        
        
        Button btnAuto = new Button("Automático");
        btnAuto.setId("btnAuto");
        btnAuto.getStyleClass().add("btn");

        buttonsContainer.getChildren().addAll(btnPrev, btnNext, btnAuto);
        buttonsContainer.getStyleClass().add("buttons-container");
        
        Label lblOptions = new Label("OPCIONES");
        lblOptions.getStyleClass().add("h1");

        sidebarContainer1.getChildren().addAll(lblOptions, buttonsContainer);
        sidebarContainer1.getStyleClass().add("buttons-container");
        sidebarContainer1.getStyleClass().add("panels1");

        
        recordRules.getChildren().add(records);

        Label lblRecord = new Label("HISTORIAL");
        lblRecord.getStyleClass().add("h1");

        sidebarContainer2.getChildren().addAll(lblRecord, recordRules);
        sidebarContainer2.getStyleClass().add("panels2");

        Label lblCurrent = new Label("MOVIMIENTO ACTUAL");
        lblCurrent.getStyleClass().add("h1");

        sidebarContainer3.getChildren().addAll(lblCurrent);

        
        canvasContainer.getStyleClass().add("canvas-container");


        containerOptions.getChildren().addAll(sidebarContainer1, sidebarContainer2);
        containerOptions.getStyleClass().add("options-container");
        
        HBox root = new HBox();
        
        root.getChildren().addAll(containerOptions, canvasContainer);
        
        dibujarFondo(canvasContainer);
        
        Scene scene = new Scene(root, 1195, 640);
        scene.getStylesheets().add("Estilos.css");
        
        primaryStage.setTitle("Jarras de Agua");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void dibujarFondo(Pane root){
        Canvas canvas = new Canvas(1200,650);
        root.getChildren().add(canvas);
    }
    

    
}
