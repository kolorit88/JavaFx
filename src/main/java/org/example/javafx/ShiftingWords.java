package org.example.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ShiftingWords extends Application {
    boolean arrowRight = true;
    TextField textField1;
    TextField textField2;
    Button btn;

    @Override
    public void start(Stage stage) throws IOException {

        textField1 = new TextField("123");
        textField2 = new TextField();

        btn = new Button("-->");
        btn.setOnAction(this::switchWord);

        FlowPane root = new FlowPane(Orientation.HORIZONTAL, 10, 10, textField1, btn, textField2);
        Scene scene = new Scene(root, 360, 100);

        stage.setScene(scene);
        stage.setTitle("ShiftingWords");
        stage.show();
    }
    public void switchWord(ActionEvent event){
        if(arrowRight){
            btn.setText("<--");
            textField2.setText(textField1.getText());
            textField1.clear();
            arrowRight = false;
        }
        else {
            btn.setText("-->");
            textField1.setText(textField2.getText());
            textField2.clear();
            arrowRight = true;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}