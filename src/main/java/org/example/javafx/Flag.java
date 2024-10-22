package org.example.javafx;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class Flag extends Application {

    private final ToggleGroup colorGroup1 = new ToggleGroup();
    private final ToggleGroup colorGroup2 = new ToggleGroup();
    private final ToggleGroup colorGroup3 = new ToggleGroup();
    private final Label resultLabel = new Label("");
    Button drawButton = new Button("Нарисовать");

    HBox colorHBox1 = new HBox();
    HBox colorHBox2 = new HBox();
    HBox colorHBox3 = new HBox();

    @Override
    public void start(Stage stage) {
        List<String> Colours = List.of("Красный", "Зелёный", "Синий");
        for (String colour : Colours) {

            RadioButton c1 = new RadioButton(colour);
            RadioButton c2 = new RadioButton(colour);
            RadioButton c3 = new RadioButton(colour);

            c1.setToggleGroup(colorGroup1);
            c2.setToggleGroup(colorGroup2);
            c3.setToggleGroup(colorGroup3);

            colorHBox1.getChildren().add(c1);
            colorHBox2.getChildren().add(c2);
            colorHBox3.getChildren().add(c3);
        }

        drawButton.setOnAction(event -> {
            String color1 = ((RadioButton) colorGroup1.getSelectedToggle()).getText();
            String color2 = ((RadioButton) colorGroup2.getSelectedToggle()).getText();
            String color3 = ((RadioButton) colorGroup3.getSelectedToggle()).getText();
            resultLabel.setText(color1 + ", " + color2 + ", " + color3);
        });


        VBox rootVBox = new VBox(20, colorHBox1, colorHBox2, colorHBox3, drawButton, resultLabel);
        rootVBox.setAlignment(Pos.CENTER);
        rootVBox.setPadding(new Insets(20));

        Scene scene = new Scene(rootVBox, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Текстовый флаг");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
