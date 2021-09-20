/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;




/**
 *
 * @author Asus
 */
public class JarrasDeAguaFx extends Application {
    
    File X0 = new File("img/X0.png");
    private final Image imgX0 = new Image(X0.toURI().toString());

    File X1 = new File("img/X1.png");
    private final Image imgX1 = new Image(X1.toURI().toString());

    File X2 = new File("img/X2.png");
    private final Image imgX2 = new Image(X2.toURI().toString());

    File X3 = new File("img/X3.png");
    private final Image imgX3 = new Image(X3.toURI().toString());

    File X4 = new File("img/X4.png");
    private final Image imgX4 = new Image(X4.toURI().toString());

    File Y0 = new File("img/Y0.png");
    private final Image imgY0 = new Image(Y0.toURI().toString());
    
    File Y1 = new File("img/Y1.png");
    private final Image imgY1 = new Image(Y1.toURI().toString());
    
    File Y2 = new File("img/Y2.png");
    private final Image imgY2 = new Image(Y2.toURI().toString());
    
    File Y3 = new File("img/Y3.png");
    private final Image imgY3 = new Image(Y3.toURI().toString());


    private int contentX = 0;
    private int contentY = 0;
    private int ruleNumber;
    ToggleGroup group = new ToggleGroup();  
    ToggleButton btnAuto = new ToggleButton("Automático");
    ImageView ivX;
    ImageView ivY;
    Slider slider;

    ListView<Record> records = new ListView<>();

    Runnable nextStepInAnotherThread;
    
    @Override
    public void start(Stage primaryStage) {

        VBox containerOptions = new VBox(5);
        StackPane recordRules = new StackPane();
        Pane canvasContainer = new Pane();
        VBox sidebarContainer1 = new VBox(5);
        VBox sidebarContainer2 = new VBox(5);
        VBox sidebarContainer3 = new VBox(15);
        HBox buttonsContainer = new HBox(5);
        
        Button btnPrev = new Button("Anterior");
        btnPrev.setId("btnPrev");
        btnPrev.getStyleClass().add("btn");
        
        
        Button btnNext = new Button("Siguiente");
        btnNext.setId("btnNext");
        btnNext.getStyleClass().add("btn");
        
      
        btnAuto.setId("btnAuto");
        btnAuto.getStyleClass().add("btn");
        btnAuto.setToggleGroup(group);

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

        Label lblCurrentRule = new Label("APLICANDO REGLA: N/A");
        lblCurrentRule.setId("lblCurrentRule");
        lblCurrentRule.getStyleClass().add("h2");

        Label lblContentX = new Label("CONTENIDO X: 0");
        lblContentX.setId("lblCurrentRule");
        lblContentX.getStyleClass().add("h2");

        Label lblContentY = new Label("CONTENIDO Y: 0");
        lblContentY.setId("lblCurrentRule");
        lblContentY.getStyleClass().add("h2");
        
        Label lblVelocity = new Label("VELOCIDAD: ");
        lblVelocity.getStyleClass().add("h2");
        slider = new Slider(0, 2950, 2000);

        sidebarContainer3.getChildren().addAll(lblCurrent, lblCurrentRule, lblContentX, lblContentY, lblVelocity, slider);
        sidebarContainer3.getStyleClass().add("panels3");

        
        canvasContainer.getStyleClass().add("canvas-container");


        containerOptions.getChildren().addAll(sidebarContainer1, sidebarContainer3, sidebarContainer2);
        containerOptions.getStyleClass().add("options-container");
        
        HBox root = new HBox();

        ivX = new ImageView();
        ivX.setImage(imgX0);
        ivX.setX(150); 
        ivX.setY(150); 
        ivX.setFitHeight(320); 
        ivX.setFitWidth(298); 
        canvasContainer.getChildren().add(ivX);

        ivY = new ImageView();
        ivY.setImage(imgY0);
        ivY.setX(550); 
        ivY.setY(150); 
        ivY.setFitHeight(320); 
        ivY.setFitWidth(246); 
        canvasContainer.getChildren().add(ivY);

        Canvas canvas = new Canvas(1200,650);
        canvasContainer.getChildren().add(canvas);
        
        root.getChildren().addAll(containerOptions, canvasContainer);

        //Eventos 
        nextStepInAnotherThread = () -> {
            nextStep(lblContentX, lblContentY, lblCurrentRule);
        };

        btnPrev.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                int selectedIdx = records.getItems().size() - 1;
                prevStep(lblContentX, lblContentY, lblCurrentRule, selectedIdx);
            }
        });

        btnNext.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                new Thread(nextStepInAnotherThread).start();
            }
        });

        btnAuto.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Toggle selectedTogger = group.getSelectedToggle();
                if(selectedTogger == btnAuto){
                    new Thread(nextStepInAnotherThread).start();
                }
            }
        });

        records.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2 ) {
                    int selectedIdx = records.getSelectionModel().getSelectedIndex() + 1;
                    prevStep(lblContentX, lblContentY, lblCurrentRule, selectedIdx);
                }
                
            }
        });

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


    public void prevStep(Label lblContentX, Label lblContentY, Label lblCurrentRule, int selectedIdx){
        Record rule = records.getItems().get(selectedIdx - 1);
        if(rule != null){

            contentX = rule.getNextX();
            contentY = rule.getNextY();

            lblCurrentRule.setText("APLICANDO REGLA: "+rule.getRule());
            lblContentX.setText("CONTENIDO X: "+contentX);
            lblContentY.setText("CONTENIDO Y: "+contentY);

            while(selectedIdx < records.getItems().size()){

                records.getItems().remove(selectedIdx);
                records.getItems().size();
            }

            records.getSelectionModel().select(records.getItems().size() - 1);
            records.scrollTo(records.getItems().size() - 1);

            updateImgs();
            
        }
    }

    public void nextStep(Label lblContentX, Label lblContentY, Label lblCurrentRule){
        if(!end()){
            int prevX = contentX;
            int prevY = contentY;
            boolean ruleExecuted = false;
            ruleNumber = 0;

            while(!ruleExecuted){
                ruleNumber = getRandomNumber(1, 9);

                switch (ruleNumber) {
                    case 1:
                        ruleExecuted = r1();
                        break;
        
                    case 2:
                        ruleExecuted = r2();
                        break;
        
                    case 3:
                        ruleExecuted = r3();
                        break;
        
                    case 4:
                        ruleExecuted = r4();
                        break;
        
                    case 5:
                        ruleExecuted = r5();
                        break;
        
                    case 6:
                        ruleExecuted = r6();
                        break;
        
                    case 7:
                        ruleExecuted = r7();
                        break;
                    
                    case 8:
                        ruleExecuted = r8();
                        break;
                }
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //update application thread
                    lblCurrentRule.setText("APLICANDO REGLA: "+ruleNumber);
                    lblContentX.setText("CONTENIDO X: "+contentX);
                    lblContentY.setText("CONTENIDO Y: "+contentY);
                    

                    Record nextRule = new Record(records.getItems().size() + 1, ruleNumber, prevX, contentX, prevY, contentY);
                    records.getItems().add(records.getItems().size(), nextRule);

                    
                    records.scrollTo(records.getItems().size() - 1);
                    records.getSelectionModel().select(records.getItems().size() - 1);

                    updateImgs();
                }
            });

            Toggle selectedTogger = group.getSelectedToggle();
            if(selectedTogger == btnAuto){
                pause();
                new Thread(nextStepInAnotherThread).start();
            }
        }else{

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //update application thread
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("ESTADO META ALCANZADO");
                    alert.setHeaderText(null);
                    alert.setContentText("Se ha completado la ejecución en "+ records.getItems().size() + " movimientos");

                    alert.showAndWait();
                }
            });
        }
    }

    public boolean end(){
        if(contentX == 2){
            return true;
        }else{
            return false;
        }
    }

    public boolean r1() {
        if(contentX < 4){
            contentX = 4;
            return true;
        }else{
            return false;
        }
    }

    public boolean r2() {
        if(contentY < 3){
            contentY = 3;
            return true;
        }else{
            return false;
        }
    }

    public boolean r3() {
        if(contentX > 0){
            contentX = 0;
            return true;
        }else{
            return false;
        }
    }

    public boolean r4() {
        if(contentY > 0){
            contentY = 0;
            return true;
        }else{
            return false;
        }
    }

    public boolean r5() {
        if(contentX > 0 && contentY < 3 && contentX + contentY >= 3){
            contentX = contentX - (3 - contentY);
            contentY = 3;
            return true;
        }else{
            return false;
        }
    }

    public boolean r6() {
        if(contentX < 4 && contentY > 0 && contentX + contentY >= 4){
            contentY = contentY - (4 - contentX);
            contentX = 4;
            return true;
        }else{
            return false;
        }
    }

    public boolean r7() {
        if(contentX > 0  && contentY < 3 && contentX + contentY <= 3){
            contentY = contentY + contentX;
            contentX = 0;
            return true;
        }else{
            return false;
        }
    }

    public boolean r8() {
        if(contentX < 4 && contentY > 0 && contentX + contentY <= 4){
            contentX = contentX + contentY;
            contentY = 0;
            return true;
        }else{
            return false;
        }
    }


    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void updateImgs(){
        Image currentXImage = null;
        Image currentYImage = null;

        switch(contentX){
            case 0:
                currentXImage = imgX0;
                break;

            case 1:
                currentXImage = imgX1;
                break;

            case 2:
                currentXImage = imgX2;
                break;

            case 3:
                currentXImage = imgX3;
                break;

            case 4:
                currentXImage = imgX4;
                break;
        }

        switch(contentY){
            case 0:
                currentYImage = imgY0;
                break;

            case 1:
                currentYImage = imgY1;
                break;

            case 2:
                currentYImage = imgY2;
                break;

            case 3:
                currentYImage = imgY3;
                break;
        }
        
        ivX.setImage(currentXImage);
        ivY.setImage(currentYImage);
    }

    public void pause(){
        long Time0 = System.currentTimeMillis();
        long Time1;
        long runTime = 0;
        while (runTime < (3000 - slider.getValue())) { // 1000 milliseconds or 1 second
            Time1 = System.currentTimeMillis();
            runTime = Time1 - Time0;
        }
    }
    
}
