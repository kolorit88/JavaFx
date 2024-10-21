package org.example.javafx;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class RestaurantOrder extends Application {
    private final Map<String, Integer> order = new HashMap<>();
    private final Label totalLabel = new Label("Итого: 0.00");
    HashMap<String, Double> dishes = new HashMap<>();

    @Override
    public void start(Stage stage) {
        // Создание меню блюд
        dishes.put("Пицца", 300.0);
        dishes.put("Суп", 150.0);
        dishes.put("Салат", 100.0);
        dishes.put("Десерт", 125.0);


        // Создание элементов для меню
        VBox menuVBox = new VBox(10);
        menuVBox.setPadding(new Insets(20));
        menuVBox.setAlignment(Pos.CENTER);

        for (Map.Entry<String, Double> entry : dishes.entrySet()) {
            // Разбиваем строку на название и цену
            String dish = entry.getKey();
            Double cost = entry.getValue();

            // Создаем элементы для блюда
            CheckBox dishCheckBox = new CheckBox(dish);
            TextField quantityField = new TextField("1");
            quantityField.setVisible(false);
            quantityField.setPrefWidth(50);

            // Обработчик события для чекбокса
            dishCheckBox.setOnAction(event -> {
                if (dishCheckBox.isSelected()) {
                    quantityField.setVisible(true);
                    quantityField.setText("1");
                    order.put(dish, Integer.parseInt(quantityField.getText()));
                } else {
                    quantityField.setVisible(false);
                    order.remove(dish);
                }
                updateTotal();
            });

            quantityField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.isEmpty()) {
                    order.put(dish, Integer.parseInt(newValue));
                } else {
                    order.put(dish, 0);
                }
                updateTotal();
            });

            // Добавление элементов в меню
            HBox dishHBox = new HBox(10, dishCheckBox, quantityField);
            menuVBox.getChildren().add(dishHBox);
        }

        // Создание панели для отображения чека
        VBox checkVBox = new VBox(10);
        checkVBox.setPadding(new Insets(20));
        checkVBox.setAlignment(Pos.CENTER);
        checkVBox.getChildren().add(new Label("Ваш заказ:"));
        checkVBox.getChildren().add(totalLabel);

        // Создание горизонтальной панели для меню и чека
        HBox rootHBox = new HBox(20, menuVBox, checkVBox);

        // Создание сцены и ее добавление в окно
        Scene scene = new Scene(rootHBox, 500, 300);
        stage.setScene(scene);
        stage.setTitle("Заказ в ресторане");
        stage.show();
    }

    private void updateTotal() {
        double total = 0;
        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            String dish = entry.getKey();
            Integer quantity = entry.getValue();

            total += dishes.get(dish) * quantity;
        }

        totalLabel.setText("Итого: " + String.format("%.2f", total));
    }

    public static void main(String[] args) {
        launch(args);

    }
}
