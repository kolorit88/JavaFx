package org.example.javafx;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;


public class Calculator extends Application {
    private TextField display;
    private double firstNumber = 0;
    private String operator = "";

    @Override
    public void start(Stage primaryStage) {
        // Создаем поле для отображения результатов
        display = new TextField();
        display.setEditable(false);

        List<Button> numsList = new ArrayList<>();
        // Создаем кнопки
        for(int num = 0; num < 10 ; num++) {
            Button btn = new Button("" + num);
            btn.setMinSize(50, 50);
            btn.setOnAction(event -> display.setText(display.getText() + btn.getText()));
            numsList.add(btn);
        }
        List<Button> operators = List.of(new Button("+"), new Button("-"), new Button("*"), new Button("/"),
                new Button("="),new Button("C"), new Button("."));

        // Обработчики событий для кнопок чисел
        operators.getLast().setOnAction(event -> {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        });

        operators.get(operators.size()-2).setOnAction(event -> {
            display.clear();
            firstNumber = 0;
            operator = "";
        });

        operators.get(operators.size()-3).setOnAction(event -> {
            calculateResult();
        });

        for(int i = 0; i < operators.size() - 3; i++) {
            Button op = operators.get(i);
            op.setOnAction(event -> {
                handleOperator(op.getText());
            });
        }

        GridPane operatorsGrid = new GridPane();
        operatorsGrid.setHgap(10);
        operatorsGrid.setVgap(10);
        operatorsGrid.setPadding(new Insets(10));

        for(int i = 0; i < operators.size(); i++) {
            operatorsGrid.add(operators.get(i), i % 2, i / 2);
            operators.get(i).setMinSize(50, 50);
        }

        // Размещение кнопок на форме
        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);
        buttonGrid.setPadding(new Insets(10));

        for(int i = 0; i < numsList.size(); i++) {
            buttonGrid.add(numsList.get(i), i % 3, i / 3);
        }

        // Добавление операторов
        VBox operatorsBox = new VBox(10);
        operatorsBox.getChildren().addAll(operatorsGrid);

        // Создание горизонтальной панели
        VBox buttonsBox = new VBox(10);
        buttonsBox.getChildren().addAll(buttonGrid);

        VBox rootHBox = new VBox(5, display, new HBox(1, buttonsBox, operatorsBox));

        // Создание сцены и ее добавление в окно
        Scene scene = new Scene(rootHBox, 350, 350);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Калькулятор");
        primaryStage.show();
    }

    private void handleOperator(String op) {
        firstNumber = Double.parseDouble(display.getText());
        operator = op;
        display.setText("");
    }

    private void calculateResult() {
        double secondNumber = Double.parseDouble(display.getText());
        switch (operator) {
            case "+":
                display.setText(String.valueOf(firstNumber + secondNumber));
                break;
            case "-":
                display.setText(String.valueOf(firstNumber - secondNumber));
                break;
            case "*":
                display.setText(String.valueOf(firstNumber * secondNumber));
                break;
            case "/":
                if (secondNumber == 0) {
                    display.setText("Ошибка: деление на ноль");
                } else {
                    display.setText(String.valueOf(firstNumber / secondNumber));
                }
                break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
