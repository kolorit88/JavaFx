package org.example.javafx;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class CheckBoxExample extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label("Label");
        TextField textField = new TextField();
        Button button = new Button("Нажми меня");

        CheckBox labelCheckBox = new CheckBox("Показать/Скрыть Label");
        CheckBox textFieldCheckBox = new CheckBox("Показать/Скрыть TextField");
        CheckBox buttonCheckBox = new CheckBox("Показать/Скрыть Button");

        labelCheckBox.setSelected(true);
        textFieldCheckBox.setSelected(true);
        buttonCheckBox.setSelected(true);

        labelCheckBox.setOnAction(event -> {
            label.setVisible(labelCheckBox.isSelected());
        });
        textFieldCheckBox.setOnAction(event -> {
            textField.setVisible(textFieldCheckBox.isSelected());
        });
        buttonCheckBox.setOnAction(event -> {
            button.setVisible(buttonCheckBox.isSelected());
        });

        HBox labelHBox = new HBox(10, label, labelCheckBox);
        HBox textFieldHBox = new HBox(10, textField, textFieldCheckBox);
        HBox buttonHBox = new HBox(10, button, buttonCheckBox);

        VBox root = new VBox(20, labelHBox, textFieldHBox, buttonHBox);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER_LEFT);

        Scene scene = new Scene(root, 400, 200);
        stage.setScene(scene);
        stage.setTitle("Виджеты с чекбоксами");
        stage.show();
    }
}
