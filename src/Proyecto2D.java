/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2d;

import java.io.File;
import java.util.function.UnaryOperator;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Asus
 */
public class Proyecto2D extends Application {
    int countCarritos = 0;
    int countCubos = 0;
    int countLibros = 0;
    int countPelotas = 0;
    int countRelojes = 0;
    private final Image bgImage = new Image(getClass().getResourceAsStream("fondo.jpeg"));
    
    @Override
    public void start(Stage primaryStage) {
        
        VBox containerOptions = new VBox();
        StackPane containerExistingOptions = new StackPane();
        Pane canvasContainer = new Pane();
        VBox buttonsObjects = new VBox();
        HBox buttonsLvl1 = new HBox();
        HBox buttonsLvl2 = new HBox();
        
        ListView<Record> existingObjects = new ListView<>();
        VBox manualOptions = new VBox();
        
        EventHandler eventButtons = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Node source = (Node) event.getSource();
                String id = source.getId();
                
                switch(id){
                    case "Carrito":
                        countCarritos++;
                        addObjectToScene(existingObjects, new Carrito(505,590,1,1,1,countCarritos), countCarritos, canvasContainer);
                    break;
                    
                    case "Cubo":
                        countCubos++;
                        addObjectToScene(existingObjects, new Cubo(600,495,1,1,1,countCubos), countCubos, canvasContainer);
                    break;
                    
                    case "Libro":
                        countLibros++;
                        addObjectToScene(existingObjects, new Libro(523,205,1,1,1,countLibros), countLibros, canvasContainer);
                    break;
                    
                    case "Pelota":
                        countPelotas++;
                        addObjectToScene(existingObjects, new Pelota(353,480,1,1,1,countPelotas), countPelotas, canvasContainer);
                    break;
                    
                    case "Reloj":
                        countRelojes++;
                        addObjectToScene(existingObjects, new Reloj(607,50,1,1,1,countRelojes), countRelojes, canvasContainer);
                    break;
                    
                    case "Eliminar":
                        if(existingObjects.getSelectionModel().getSelectedItem() != null && existingObjects.getItems().size() > 0){
                            final int selectedIdx = existingObjects.getSelectionModel().getSelectedIndex();
                            Record element = existingObjects.getSelectionModel().getSelectedItem();
                            
                            canvasContainer.getChildren().remove(element.getGroup());
                            
                            /*Aqui debe ir el codigo para eliminar el dibujo del objeto en cuestion
                            
                            
                            
                            */
                            
                            existingObjects.getItems().remove(selectedIdx);
                        }
                    break;
                }
                    
            }
        };
        
        Button btn1 = new Button("Carrito");
        btn1.setId("Carrito");
        btn1.getStyleClass().add("btn-obj");
        btn1.setOnAction(eventButtons);
        
        
        Button btn2 = new Button("Cubo");
        btn2.setId("Cubo");
        btn2.getStyleClass().add("btn-obj");
        btn2.setOnAction(eventButtons);
        
        
        Button btn3 = new Button("Libro");
        btn3.setId("Libro");
        btn3.getStyleClass().add("btn-obj");
        btn3.setOnAction(eventButtons);
        
        
        Button btn4 = new Button("Pelota");
        btn4.setId("Pelota");
        btn4.getStyleClass().add("btn-obj");
        btn4.setOnAction(eventButtons);
        
        
        Button btn5 = new Button("Reloj");
        btn5.setId("Reloj");
        btn5.getStyleClass().add("btn-obj");
        btn5.setOnAction(eventButtons);
        
        Button btn6 = new Button("Eliminar");
        btn6.setId("Eliminar");
        btn6.getStyleClass().add("btn-obj");
        btn6.setOnAction(eventButtons);
        
        buttonsLvl1.getChildren().addAll(btn1, btn2, btn3);
        buttonsLvl1.getStyleClass().add("buttons-container");
        
        buttonsLvl2.getChildren().addAll(btn4, btn5, btn6);
        buttonsLvl2.getStyleClass().add("buttons-container");
        
        
        Label lbObjs = new Label("Agrega un objeto");
        buttonsObjects.getChildren().addAll(lbObjs, buttonsLvl1, buttonsLvl2);
        
        buttonsObjects.getStyleClass().add("buttons-container");
        buttonsObjects.setTranslateX(10);
        buttonsObjects.setTranslateY(5);
        
        buttonsObjects.getStyleClass().add("panels1");
        containerExistingOptions.getStyleClass().add("panels2");
        manualOptions.getStyleClass().add("panels3");
        
        UnaryOperator<Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("([0-9][0-9]*)?")) { 
                return change;
            }
            return null;
        };
        
        
        TextField txtXTranslationOption = new TextField();
        
        TextField txtYTranslationOption = new TextField();

        
        Label lbXTranslationOption = new Label("En X");
        txtXTranslationOption.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        txtXTranslationOption.setId("traslation-x");
        txtXTranslationOption.getStyleClass().add("input-options");
        txtXTranslationOption.setText("0");
        txtXTranslationOption.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent ke){
                Record element = existingObjects.getSelectionModel().getSelectedItem();
                if (ke.getCode().equals(KeyCode.ENTER) && element != null){
                    
                    comprobarCampo(txtXTranslationOption);
                    comprobarCampo(txtYTranslationOption);
                    if((element.getTransformX() > element.getDegrees() || element.getTransformX() > element.getLimitRotation())){
                        trasladar(element, Integer.parseInt(txtXTranslationOption.getText()), Integer.parseInt(txtYTranslationOption.getText()));
                    }else{
                        txtXTranslationOption.setText(element.getTransformX()+"");
                        txtYTranslationOption.setText(element.getTransformY()+"");
                    }
                }
            }
        });
        
        VBox xTranslationOptions = new VBox();
        xTranslationOptions.getChildren().addAll(lbXTranslationOption, txtXTranslationOption);
        
        Label lbYTranslationOption = new Label("En Y");
        txtYTranslationOption.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        txtYTranslationOption.setId("traslation-y");
        txtYTranslationOption.getStyleClass().add("input-options");
        txtYTranslationOption.setText("0");
        txtYTranslationOption.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent ke){
                Record element = existingObjects.getSelectionModel().getSelectedItem();
                if (ke.getCode().equals(KeyCode.ENTER) && element != null){
                    comprobarCampo(txtXTranslationOption);
                    comprobarCampo(txtYTranslationOption);
                    
                    if((element.getTransformX() > element.getDegrees() || element.getTransformX() > element.getLimitRotation())){
                        trasladar(element, Integer.parseInt(txtXTranslationOption.getText()), Integer.parseInt(txtYTranslationOption.getText()));
                    }else{
                        txtXTranslationOption.setText(element.getTransformX()+"");
                        txtYTranslationOption.setText(element.getTransformY()+"");
                    }
                }
            }
        });
        
        VBox yTranslationOptions = new VBox();
        yTranslationOptions.getChildren().addAll(lbYTranslationOption, txtYTranslationOption);
        
        HBox translationOptions = new HBox();
        translationOptions.getChildren().addAll(xTranslationOptions, yTranslationOptions);
        translationOptions.getStyleClass().add("inputs-container");
        
        
        TextField txtYScaleOption = new TextField();
        TextField txtXScaleOption = new TextField();
        
        Label lbXScaleOption = new Label("En X");
        txtXScaleOption.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        txtXScaleOption.setId("scale-x");
        txtXScaleOption.getStyleClass().add("input-options");
        txtXScaleOption.setText("0");
        txtXScaleOption.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent ke){
                Record element = existingObjects.getSelectionModel().getSelectedItem();
                if (ke.getCode().equals(KeyCode.ENTER) && element != null){
                    comprobarCampo(txtXScaleOption);
                    comprobarCampo(txtYScaleOption);
                    
                    escalar(element, Integer.parseInt(txtXScaleOption.getText()), Integer.parseInt(txtYScaleOption.getText()));
                }
            }
        });
        
        VBox xScaleOptions = new VBox();
        xScaleOptions.getChildren().addAll(lbXScaleOption, txtXScaleOption);
        
        Label lbYScaleOption = new Label("En Y");
        txtYScaleOption.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        txtYScaleOption.setId("scale-y");
        txtYScaleOption.getStyleClass().add("input-options");
        txtYScaleOption.setText("0");
        txtYScaleOption.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent ke){
                Record element = existingObjects.getSelectionModel().getSelectedItem();
                if (ke.getCode().equals(KeyCode.ENTER) && element != null){
                    comprobarCampo(txtXScaleOption);
                    comprobarCampo(txtYScaleOption);
                    
                    escalar(element, Integer.parseInt(txtXScaleOption.getText()), Integer.parseInt(txtYScaleOption.getText()));
                }
            }
        });
        
        VBox yScaleOptions = new VBox();
        yScaleOptions.getChildren().addAll(lbYScaleOption, txtYScaleOption);
        
        HBox scaleOptions = new HBox();
        scaleOptions.getChildren().addAll(xScaleOptions, yScaleOptions);
        scaleOptions.getStyleClass().add("inputs-container");
        
        TextField txtRotationOptions = new TextField();
        txtRotationOptions.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        txtRotationOptions.setId("rotation-degrees");
        txtRotationOptions.getStyleClass().add("input-options");
        txtRotationOptions.setText("0");
        txtRotationOptions.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent ke){
                Record element = existingObjects.getSelectionModel().getSelectedItem();
                if (ke.getCode().equals(KeyCode.ENTER) && element != null){
                    comprobarCampo(txtRotationOptions);
                    
                    if(element.getTransformX() > element.getDegrees() || element.getTransformX() > element.getLimitRotation()){
                        rotar(element, Integer.parseInt(txtRotationOptions.getText()));
                    }else{
                        txtRotationOptions.setText(element.getDegrees()+"");
                    }
                }
            }
        });
        
        Label lbTranslationOptions = new Label("Traslacion");
        Label lbScaleOptions = new Label("Escalado");
        Label lbRotationOptions = new Label("Rotacion (en grados)");
        manualOptions.getChildren().addAll(lbTranslationOptions, translationOptions, lbScaleOptions, scaleOptions, lbRotationOptions, txtRotationOptions);
        manualOptions.setTranslateX(10);
        manualOptions.setTranslateY(5);
        
        manualOptions.getStyleClass().add("inputs-container");
        
        containerExistingOptions.getChildren().add(existingObjects);
        
        
        canvasContainer.getStyleClass().add("canvas-container");
        
        final ToggleGroup group = new ToggleGroup();

        ToggleButton tb1 = new ToggleButton("Mover");
        tb1.setToggleGroup(group);
        tb1.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent ke){
                Record element = existingObjects.getSelectionModel().getSelectedItem();
                if (ke.getCode().equals(KeyCode.A) && element != null){
                    if(element.getTransformX() > 0 && (element.getTransformX() > element.getDegrees() || element.getTransformX() > element.getLimitRotation())){
                        trasladar(element, element.getTransformX() - 1, element.getTransformY());
                    }
                    txtRotationOptions.setText(element.getDegrees()+"");
                    txtXTranslationOption.setText(element.getTransformX()+"");
                    txtYTranslationOption.setText(element.getTransformY()+"");
                    txtXScaleOption.setText(element.getScaleX()+"");
                    txtYScaleOption.setText(element.getScaleY()+"");
                    
                }else if(ke.getCode().equals(KeyCode.D) && element != null){
                    trasladar(element, element.getTransformX() + 1, element.getTransformY());
                    txtRotationOptions.setText(element.getDegrees()+"");
                    txtXTranslationOption.setText(element.getTransformX()+"");
                    txtYTranslationOption.setText(element.getTransformY()+"");
                    txtXScaleOption.setText(element.getScaleX()+"");
                    txtYScaleOption.setText(element.getScaleY()+"");
                }else if(ke.getCode().equals(KeyCode.W) && element != null){
                    if(element.getTransformY() > 0){
                        trasladar(element, element.getTransformX(), element.getTransformY() - 1);
                        txtRotationOptions.setText(element.getDegrees()+"");
                        txtXTranslationOption.setText(element.getTransformX()+"");
                        txtYTranslationOption.setText(element.getTransformY()+"");
                        txtXScaleOption.setText(element.getScaleX()+"");
                        txtYScaleOption.setText(element.getScaleY()+"");
                    }
                }else if(ke.getCode().equals(KeyCode.S) && element != null){
                    trasladar(element, element.getTransformX(), element.getTransformY() + 1);
                    txtRotationOptions.setText(element.getDegrees()+"");
                    txtXTranslationOption.setText(element.getTransformX()+"");
                    txtYTranslationOption.setText(element.getTransformY()+"");
                    txtXScaleOption.setText(element.getScaleX()+"");
                    txtYScaleOption.setText(element.getScaleY()+"");
                }
            }
        });

        ToggleButton tb2 = new ToggleButton("Escalar");
        tb2.setToggleGroup(group);
        tb2.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent ke){
                Record element = existingObjects.getSelectionModel().getSelectedItem();
                if (ke.getCode().equals(KeyCode.A) && element != null){
                    if(element.getScaleX() > 1){
                        escalar(element, element.getScaleX() - 1, element.getScaleY());
                        txtRotationOptions.setText(element.getDegrees()+"");
                        txtXTranslationOption.setText(element.getTransformX()+"");
                        txtYTranslationOption.setText(element.getTransformY()+"");
                        txtXScaleOption.setText(element.getScaleX()+"");
                        txtYScaleOption.setText(element.getScaleY()+"");
                    }
                }else if(ke.getCode().equals(KeyCode.D) && element != null){
                    escalar(element, element.getScaleX() + 1, element.getScaleY());
                    txtRotationOptions.setText(element.getDegrees()+"");
                    txtXTranslationOption.setText(element.getTransformX()+"");
                    txtYTranslationOption.setText(element.getTransformY()+"");
                    txtXScaleOption.setText(element.getScaleX()+"");
                    txtYScaleOption.setText(element.getScaleY()+"");
                }else if(ke.getCode().equals(KeyCode.W) && element != null){
                    if(element.getScaleY() > 1){
                        escalar(element, element.getScaleX(), element.getScaleY() - 1);
                        txtRotationOptions.setText(element.getDegrees()+"");
                        txtXTranslationOption.setText(element.getTransformX()+"");
                        txtYTranslationOption.setText(element.getTransformY()+"");
                        txtXScaleOption.setText(element.getScaleX()+"");
                        txtYScaleOption.setText(element.getScaleY()+"");
                    }
                }else if(ke.getCode().equals(KeyCode.S) && element != null){
                    escalar(element, element.getScaleX(), element.getScaleY() + 1);
                    txtRotationOptions.setText(element.getDegrees()+"");
                    txtXTranslationOption.setText(element.getTransformX()+"");
                    txtYTranslationOption.setText(element.getTransformY()+"");
                    txtXScaleOption.setText(element.getScaleX()+"");
                    txtYScaleOption.setText(element.getScaleY()+"");
                }
            }
        });

        ToggleButton tb3 = new ToggleButton("Rotar");
        tb3.setToggleGroup(group);
        tb3.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent ke){
                    Record element = existingObjects.getSelectionModel().getSelectedItem();
                if (ke.getCode().equals(KeyCode.A) && element != null){
                    if(element.getTransformX() > element.getDegrees()  || element.getTransformX() > element.getLimitRotation()){
                        rotar(element, element.getDegrees() + 1);
                    }
                    txtRotationOptions.setText(element.getDegrees()+"");
                    txtXTranslationOption.setText(element.getTransformX()+"");
                    txtYTranslationOption.setText(element.getTransformY()+"");
                    txtXScaleOption.setText(element.getScaleX()+"");
                    txtYScaleOption.setText(element.getScaleY()+"");
                }else if(ke.getCode().equals(KeyCode.D) && element != null){
                    if(element.getDegrees() > 0){
                        rotar(element, element.getDegrees() - 1);
                        txtRotationOptions.setText(element.getDegrees()+"");
                        txtXTranslationOption.setText(element.getTransformX()+"");
                        txtYTranslationOption.setText(element.getTransformY()+"");
                        txtXScaleOption.setText(element.getScaleX()+"");
                        txtYScaleOption.setText(element.getScaleY()+"");
                    }
                }
            }
        });
        
        HBox toggleButtons = new HBox(tb1, tb2, tb3);
        toggleButtons.setTranslateX(10);
        toggleButtons.setTranslateY(5);
        
        
        toggleButtons.getStyleClass().add("buttons-container");
        
        containerOptions.getChildren().addAll(buttonsObjects, containerExistingOptions, toggleButtons,manualOptions);
        containerOptions.getStyleClass().add("options-container");
        
        HBox root = new HBox();
        
        existingObjects.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Record element = existingObjects.getSelectionModel().getSelectedItem();
                if(element != null){
                    txtRotationOptions.setText(element.getDegrees()+"");
                    txtXTranslationOption.setText(element.getTransformX()+"");
                    txtYTranslationOption.setText(element.getTransformY()+"");
                    txtXScaleOption.setText(element.getScaleX()+"");
                    txtYScaleOption.setText(element.getScaleY()+"");
                }
                
            }
        });
        
        
        //Codigo de eventos de color 
        existingObjects.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void handle (KeyEvent k ) {
                
                Record element = existingObjects.getSelectionModel().getSelectedItem();
                if (k.getCode().equals(KeyCode.C)  && element != null){
                    canvasContainer.getChildren().remove(element.getGroup());
                    
                    Group newElement = new Group();
                    if(element.getCambioColor()){
                        newElement.getChildren().add(element.getImagen());
                    }else{
                        newElement.getChildren().add(element.cambiarColor());
                    }
                    
                    element.setCambioColor(!element.getCambioColor());
                    
                    element.setGroup(newElement);
                    canvasContainer.getChildren().add(newElement);
                    
                    int originalDegres = element.getDegrees();
                    int originalScaleX = element.getScaleX();
                    int originalScaelY = element.getScaleY();
                    int originalTransformX = element.getTransformX();
                    int originalTransformY = element.getTransformY();
                    
                    element.setDegrees(1);
                    element.setScaleX(1);
                    element.setScaleY(1);
                    element.setTransformX(1);
                    element.setTransformY(1);
                    
                    
                    trasladar(element, originalTransformX, originalTransformY);
                    escalar(element, originalScaleX, originalScaelY);
                    rotar(element, originalDegres);
                }else if(k.getCode().equals(KeyCode.M)  && element != null){
                    String musicFile = element.ponerMusica();  // For example 

                    Media sound = new Media(new File(musicFile).toURI().toString()); 
                    MediaPlayer mediaPlayer = new MediaPlayer(sound); 
                    mediaPlayer.play(); 
                }
                
            }
        });
        
        root.getChildren().addAll(containerOptions, canvasContainer);
        
        
        
        ImageView imageView = new ImageView();
        imageView.setImage(bgImage);
        imageView.setFitHeight(650);
        imageView.setFitWidth(900);
        canvasContainer.getChildren().add(imageView);
        
        dibujarFondo(canvasContainer);
        
        Scene scene = new Scene(root, 1195, 640);
        scene.getStylesheets().add("Estilos.css");
        
        primaryStage.setTitle("Vacaciones");
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
    
    public void addObjectToScene(ListView existingObjects, Record element, int count, Pane canvasContainer){
        Group newElement = new Group();
        newElement.getChildren().add(element.getImagen());
        element.setGroup(newElement);
        
        
        canvasContainer.getChildren().add(newElement);
        /*En esta parte debe ir el codigo para crear el objeto en cuestion y despues se a�ade a la lista de items
        
        
        */
        existingObjects.getItems().add(existingObjects.getItems().size(), element);
        existingObjects.scrollTo(existingObjects.getItems().size() - 1);
        
        int originalX = element.getTransformX();
        int originalY = element.getTransformY();
        
        element.setTransformX(1);
        element.setTransformY(1);
        
        trasladar(element, originalX, originalY);
        new AnimationTimer() {

            int frameCount = 0 ;

            @Override
            public void handle(long now) {
                frameCount++ ;
                if (frameCount > 1) {        
                    existingObjects.edit(existingObjects.getItems().size() - 1);
                    stop();
                }
            }

        }.start();
    }
    
    private void trasladar ( Record element, int x, int y){

        Translate translate = new Translate();      
        
        Rotate rotate = new Rotate();
        
        
        
        Scale scale= new Scale (); 
        
        
        scale.setX(1.0/element.getScaleX());
        scale.setY(1.0/element.getScaleY());

        element.getGroup().getTransforms().addAll(scale);

        rotate.setAngle(-element.getDegrees()+1);
        element.getGroup().getTransforms().addAll(rotate);
        

        translate.setX(x-element.getTransformX());
        translate.setY(y-element.getTransformY());
        
        element.setTransformX(x);
        element.setTransformY(y);
        

        element.getGroup().getTransforms().addAll(translate);
        
        
        
        rotate = new Rotate();

        rotate.setAngle(element.getDegrees()-1);
        element.getGroup().getTransforms().addAll(rotate);
        
        scale= new Scale (); 
        
        scale.setX(element.getScaleX());
        scale.setY(element.getScaleY());

        element.getGroup().getTransforms().addAll(scale);

    }

   

    private void escalar ( Record element, int x, int y){

        Scale scale= new Scale (); 
        
        
        scale.setX(1.0/element.getScaleX());
        scale.setY(1.0/element.getScaleY());

        element.getGroup().getTransforms().addAll(scale);
        
        scale= new Scale (); 
        
        scale.setX(x);
        scale.setY(y);
        
        element.setScaleX(x);
        element.setScaleY(y);

        element.getGroup().getTransforms().addAll(scale);

    }

   

    private void rotar ( Record element, int degrees){

        Rotate rotate = new Rotate();
        
        Scale scale= new Scale (); 
        
        
        scale.setX(1.0/element.getScaleX());
        scale.setY(1.0/element.getScaleY());

        element.getGroup().getTransforms().addAll(scale);

        rotate.setAngle(-element.getDegrees());

        element.getGroup().getTransforms().addAll(rotate);
        
        rotate = new Rotate();

        rotate.setAngle(degrees);
        
        element.setDegrees(degrees);

        element.getGroup().getTransforms().addAll(rotate);
        
         scale= new Scale (); 
        
        scale.setX(element.getScaleX());
        scale.setY(element.getScaleY());

        element.getGroup().getTransforms().addAll(scale);

    }
    
    public void comprobarCampo(TextField campo){
        if(campo.getText().equals("")){
            campo.setText("1");
        }
    }
    
    public void dibujarFondo(Pane root){
        Canvas canvas = new Canvas(1200,650);
        root.getChildren().add(canvas);
        
        //Marco
        GraphicsContext marcoS = canvas.getGraphicsContext2D();
        marcoS.setStroke(Color.web("D8DBDB"));
        marcoS.setFill(Color.web("DDE0E0"));
        marcoS.setLineWidth(10.0);
        marcoS.beginPath();
        marcoS.appendSVGPath("M 910 1 L 1190 1 L 1190 640 L 911 640 L 910 650z");
        marcoS.closePath();
        marcoS.fill();
        marcoS.stroke();
        
        GraphicsContext marco = canvas.getGraphicsContext2D();
        marco.setStroke(Color.web("CCCCCC"));
        marco.setLineWidth(10.0);
        marco.beginPath();
        marco.appendSVGPath("M 910 1 L 1199 1 L 1199 650 L 910 650z");
        marco.closePath();
        marco.stroke();
       
        //Globo terraquio
        //Soporte
        GraphicsContext soporte= canvas.getGraphicsContext2D();
        soporte.setFill(Color.web("C97E18"));
        soporte.beginPath();
        soporte.appendSVGPath("M 55 620 L 120 618 L 120 612 L 93 603 L 93 595 C 93 595 105 593 118 591 "
                + "C 118 591 132 585 144 577 C 144 577 152 568 159 559 C 159 559 164 547 166 536 C 166 536 166 524 162 514"
                + "C 162 514 157 506 151 495 C 151 495 143 490 133 483 L 120 477 L 113 487 L 120 490 L 122 484 L 132 488"
                + "C 132 488 139 495 146 500 C 146 500 152 509 156 517 C 156 517 158 527 158 538 "
                + "C 158 538 157 549 153 557 C 153 557 146 566 136 574 C 136 574 124 582 112 587 L 95 590 L 76 589 L 63 584"
                + "L 66 579 L 59 575 L 53 587 L 80 595 L 80 602 L 55 611Z");
        soporte.closePath();
        soporte.fill();

        
        //Mar de la tierra
        GraphicsContext mar = canvas.getGraphicsContext2D();
        mar.setStroke(Color.web("28BBDF"));
        mar.setFill(Color.web("28BBDF"));
        mar.fillOval(33,482,115,100);
        
        //Venta
        //Vidrio abajo derecha
        GraphicsContext vid = canvas.getGraphicsContext2D();
        vid.setFill(Color.web("5FDCF8"));
        vid.beginPath();
        vid.appendSVGPath("M 255 162 L 321 162 L 321 237 L 270 237 L 264 241 L 264 250 L 255 250z");
        vid.closePath();
        vid.fill();
        
        //Vidrio arriba derecha
        GraphicsContext vid2 = canvas.getGraphicsContext2D();
        vid2.setFill(Color.web("5FDCF8"));
        vid2.beginPath();
        vid2.appendSVGPath("M 255 156 L 322 156 L 322 108 L 296 108 L 255 140z");
        vid2.closePath();
        vid2.fill();
        //Efecto
        GraphicsContext efec = canvas.getGraphicsContext2D();
        efec.setFill(Color.web("AFEBF8"));
        efec.beginPath();
        efec.appendSVGPath("M 255 108 L 296 108 L 255 140 z");
        efec.closePath();
        efec.fill();
        
        //Vidrio arriba izquierda
        GraphicsContext vid3 = canvas.getGraphicsContext2D();
        vid3.setFill(Color.web("5FDCF8"));
        vid3.beginPath();
        vid3.appendSVGPath("M 242 108 L 171 108 L 160 156 L 242 156z");
        vid3.closePath();
        vid3.fill();
        //Efecto
        GraphicsContext efec2 = canvas.getGraphicsContext2D();
        efec2.setFill(Color.web("AFEBF8"));
        efec2.beginPath();
        efec2.appendSVGPath("M 237 156 L 242 151 L 242 120 L 195 156 L 168 156 L 224 108 "
                + "L 208 108 L 163 145 L 160 156z");
        efec2.closePath();
        efec2.fill();
        
        //Vidrio abajo izquierda
        GraphicsContext vid4 = canvas.getGraphicsContext2D();
        vid4.setFill(Color.web("5FDCF8"));
        vid4.beginPath();
        vid4.appendSVGPath("M 242 163 L 158 163 L 138 250 L 186 250 L 184 240 L 193 240 C 201 234 191 231 186 221"
                + "C 186 221 193 223 199 229 C 199 220 194 216 190 209 "
                + "C 190 209 196 212 200 215 L 201 208 C 198 205 194 202 199 200 "
                + "C 199 200 201 199 204 200 C 204 200 207 203 204 206 L 201 208 L 204 229 L 204 236"
                + "L 208 235 L 210 200 L 208 197 L 203 195 L 203 191 L 209 190 L 209 187 L 214 187"
                + "L 214 191 L 218 191 L 219 195 L 216 196 L 214 199 L 211 201 C 212 209 215 205 219 203"
                + "C 219 203 217 209 212 211 L 214 236 L 217 236 L 219 221 C 218 217 220 214 223 217"
                + "C 223 217 221 221 222 228 C 222 228 228 224 234 221 C 232 221 228 229 222 233 L 225 241"
                + "L 233 242 L 231 250  L 242 250 Z");
        vid4.closePath();
        vid4.fill();
        //Efecto
        GraphicsContext efec3 = canvas.getGraphicsContext2D();
        efec3.setFill(Color.web("AFEBF8"));
        efec3.beginPath();
        efec3.appendSVGPath("M 226 163 L 184 163 L 153 190 L 143 230z");
        efec3.closePath();
        efec3.fill();
        
        
        //Cara uno del globo terraquio
        //Tierra uno
        
        GraphicsContext europa= canvas.getGraphicsContext2D();
        europa.setStroke(Color.web("9CDE7C"));
        europa.setLineWidth(5.0);
        europa.setFill(Color.web("4FCB11"));
        europa.beginPath();
        europa.appendSVGPath("M 117 562 C 117 562 112 550 100 540 C 100 540 108 531 111 520"
                + "C 115 510 122 520 140 520 C 140 520 150 530 142 543 C 139 543 125 544 120 561z");
        europa.closePath();
        europa.fill();
        europa.stroke();
        
        //Tierra dos
        GraphicsContext america= canvas.getGraphicsContext2D();
        america.setStroke(Color.web("9CDE7C"));
        america.setLineWidth(5.0);
        america.setFill(Color.web("4FCB11"));
        america.beginPath();
        america.appendSVGPath("M 50 500 C 50 500 60 490 80 520 C 80 525 72 530 60 525"
                + "L 41 533 L 45 545 L 50 550 L 60 552 L 70 560 C 65 580 45 564 40 557 C 40 557 34 553 35 545"
                + "C 35 545 30 540 31 533 C 31 533 36 515 50 500 z");
        america.closePath();
        america.fill();
        america.stroke();
        
        //Tierra tres
         GraphicsContext dina= canvas.getGraphicsContext2D();
        dina.setStroke(Color.web("EBF0E9"));
        dina.setLineWidth(3.0);
        dina.setFill(Color.WHITE);
        dina.beginPath();
        dina.appendSVGPath("M 76 494 C 76 494 87 490 95 493 C 96 494 83 499 81 508 C 80 509 75 500 76 494Z");
        dina.closePath();
        dina.fill();
        dina.stroke();
    }
    

    
}
