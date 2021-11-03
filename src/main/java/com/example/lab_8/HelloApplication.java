package com.example.lab_8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Casse-tête");
        stage.setHeight(603);
        stage.setWidth(603);

        //Liste ImageView
        ImageView image0 = new ImageView("file:mario0.jpg");
        image0.setPreserveRatio(true);

        ImageView image1 = new ImageView("file:mario1.jpg");
        image1.setPreserveRatio(true);

        ImageView image2 = new ImageView("file:mario2.jpg");
        image2.setPreserveRatio(true);

        ImageView image3 = new ImageView("file:mario3.jpg");
        image3.setPreserveRatio(true);

        ImageView image4 = new ImageView("file:mario4.jpg");
        image4.setPreserveRatio(true);

        ImageView image5 = new ImageView("file:mario5.jpg");
        image5.setPreserveRatio(true);

        ImageView image6 = new ImageView("file:mario6.jpg");
        image6.setPreserveRatio(true);

        ImageView image7 = new ImageView("file:mario7.jpg");
        image7.setPreserveRatio(true);

        ImageView image8 = new ImageView("file:mario8.jpg");
        image8.setPreserveRatio(true);

        ArrayList<ImageView> imageComplete = new ArrayList<>();

        imageComplete.add(image0);
        imageComplete.add(image1);
        imageComplete.add(image2);
        imageComplete.add(image3);
        imageComplete.add(image4);
        imageComplete.add(image5);
        imageComplete.add(image6);
        imageComplete.add(image7);
        imageComplete.add(image8);

        //Liste Image
        ArrayList<Image> images = new ArrayList<>();

        Image imageA = new Image("file:mario0.jpg");
        Image imageB = new Image("file:mario1.jpg");
        Image imageC = new Image("file:mario2.jpg");
        Image imageD = new Image("file:mario3.jpg");
        Image imageE = new Image("file:mario4.jpg");
        Image imageF = new Image("file:mario5.jpg");
        Image imageG = new Image("file:mario6.jpg");
        Image imageH = new Image("file:mario7.jpg");
        Image imageI = new Image("file:mario8.jpg");

        images.add(imageA);
        images.add(imageB);
        images.add(imageC);
        images.add(imageD);
        images.add(imageE);
        images.add(imageF);
        images.add(imageG);
        images.add(imageH);
        images.add(imageI);

        //Autre liste d'image originale
        ArrayList<Image> imagesOriginales = new ArrayList<>();

        imagesOriginales.add(imageA);
        imagesOriginales.add(imageB);
        imagesOriginales.add(imageC);
        imagesOriginales.add(imageD);
        imagesOriginales.add(imageE);
        imagesOriginales.add(imageF);
        imagesOriginales.add(imageG);
        imagesOriginales.add(imageH);
        imagesOriginales.add(imageI);

        //GridPane et arrangement de la position des images
        Collections.shuffle(images);
        for (int i = 0; i < images.size(); i++) {
            imageComplete.get(i).setImage(images.get(i));
        }

        GridPane gridPane = new GridPane();
        gridPane.add(image0, 0, 0);
        gridPane.add(image1, 1, 0);
        gridPane.add(image2, 2, 0);
        gridPane.add(image3, 0, 1);
        gridPane.add(image4, 1, 1);
        gridPane.add(image5, 2, 1);
        gridPane.add(image6, 0, 2);
        gridPane.add(image7, 1, 2);
        gridPane.add(image8, 2, 2);

        //Drag and drop
        for (ImageView temp : imageComplete) {
            temp.setOnDragDetected((ae) -> {
                System.out.println("Drag");
                Dragboard dragboard = temp.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putImage(temp.getImage());
                dragboard.setContent(content);
            });
            temp.setOnDragOver((ae) -> {
                ae.acceptTransferModes(TransferMode.MOVE);
            });
            temp.setOnDragDropped(ae -> {
                ImageView source = (ImageView) ae.getGestureSource();
                Image imageSource = source.getImage();
                source.setImage(temp.getImage());
                temp.setImage(imageSource);
                System.out.println("Drag and drop");
                ae.setDropCompleted(true);
                int verification = 0;
                for (int i = 0; i < imageComplete.size(); i++) {
                    if (imageComplete.get(i).getImage() == imagesOriginales.get(i)) {
                        verification++;
                    }
                }

                //Vérification de réussite
                if (verification == 9) {
                    Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
                    alerte.setTitle("Félicitations");
                    alerte.setContentText("Veux-tu recommencer le casse-tête?");
                    ButtonType resultat = alerte.showAndWait().get();
                    if (resultat == ButtonType.OK) {
                        Collections.shuffle(images);
                        gridPane.getChildren().clear();
                        for (int i = 0; i < images.size(); i++) {
                            imageComplete.get(i).setImage(images.get(i));
                        }
                        gridPane.addRow(0, imageComplete.get(0), imageComplete.get(1), imageComplete.get(2));
                        gridPane.addRow(1, imageComplete.get(3), imageComplete.get(4), imageComplete.get(5));
                        gridPane.addRow(2, imageComplete.get(6), imageComplete.get(7), imageComplete.get(8));
                    } else
                        stage.close();
                }
            });
        }

        //Ctrl + M
        Scene scene = new Scene(gridPane);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.M && event.isControlDown()) {
                Collections.shuffle(images);
                gridPane.getChildren().clear();
                System.out.println("Image mélangées");
                for (int i = 0; i < images.size(); i++) {
                    imageComplete.get(i).setImage(images.get(i));
                }
                gridPane.addRow(0, imageComplete.get(0), imageComplete.get(1), imageComplete.get(2));
                gridPane.addRow(1, imageComplete.get(3), imageComplete.get(4), imageComplete.get(5));
                gridPane.addRow(2, imageComplete.get(6), imageComplete.get(7), imageComplete.get(8));
            }
        });

        //Texte réussite
        if (images == imagesOriginales) {
            Label felicitations = new Label("Félicitation, vous avez réussi!");

            Button recommencer = new Button("Recommencer");
            recommencer.setOnAction(actionEvent -> {
                Collections.shuffle(images);
                for (int i = 0; i < images.size(); i++) {
                    imageComplete.get(i).setImage(images.get(i));
                }
            });
            HBox reussite = new HBox(felicitations, recommencer);
            reussite.setAlignment(Pos.TOP_CENTER);
        }

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}