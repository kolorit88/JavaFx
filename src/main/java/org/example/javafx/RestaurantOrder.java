package org.example.javafx;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class RestaurantOrder extends Application {

    private ListView<String> dishListView;
    private final ObservableList<String> dishes = FXCollections.observableArrayList();
    private final Map<String, Double> dishPrices = new HashMap<>();
    private final Map<String, Integer> dishQuantities = new HashMap<>();
    Spinner<Integer> quantitySpinner = new Spinner<>(1, 10, 1, 1);
    TextArea orderTextArea = new TextArea();

    @Override
    public void start(Stage stage) {
        // Инициализация данных о блюдах
        dishes.addAll("Пицца", "Суши", "Салат", "Десерт");
        dishPrices.put("Пицца", 100.0);
        dishPrices.put("Суши", 150.0);
        dishPrices.put("Салат", 50.0);
        dishPrices.put("Десерт", 30.0);

        // Создание элементов GUI
        dishListView = new ListView<>(dishes);
        dishListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        Label orderLabel = new Label("Ваш заказ:");
        orderTextArea.setEditable(false);

        Label quantityLabel = new Label("Количество:");

        Button addToOrderButton = new Button("Добавить в заказ");
        addToOrderButton.setOnAction(event -> addToOrder());

        Button checkoutButton = new Button("Оформить заказ");
        checkoutButton.setOnAction(event -> checkout());

        // Размещение элементов в GridPane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        gridPane.add(new Label("Меню:"), 0, 0);
        gridPane.add(dishListView, 0, 1, 2, 1);
        gridPane.add(quantityLabel, 0, 2);
        gridPane.add(quantitySpinner, 1, 2);
        gridPane.add(addToOrderButton, 0, 3);
        gridPane.add(orderLabel, 0, 4);
        gridPane.add(orderTextArea, 0, 5, 2, 1);
        gridPane.add(checkoutButton, 0, 6);

        // Создание сцены и отображение окна
        Scene scene = new Scene(gridPane, 400, 450);
        stage.setTitle("Ресторан");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    // Метод для добавления выбранных блюд в заказ
    private void addToOrder() {
        String dish = dishListView.getSelectionModel().getSelectedItem();
        if (dish != null) {
            int quantity = quantitySpinner.getValue();
            dishQuantities.put(dish, quantity);
            updateOrderTextArea();
            dishListView.getSelectionModel().clearSelection();
        }
    }

    // Метод для оформления заказа
    private void checkout() {
        if (dishQuantities.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Пустой заказ");
            alert.setHeaderText(null);
            alert.setContentText("Ваш заказ пуст. Выберите блюда.");
            alert.showAndWait();
            return;
        }

        double totalCost = 0;

        for (Map.Entry<String, Integer> entry : dishQuantities.entrySet()) {
            String dish = entry.getKey();
            int quantity = entry.getValue();
            double dishCost = dishPrices.get(dish) * quantity;
            totalCost += dishCost;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ваш заказ");
        alert.setHeaderText(null);
        alert.setContentText(orderTextArea.getText() + "\n" + "Итого: " + String.format("%.2f", totalCost) + "руб");
        alert.showAndWait();

        // Сброс заказа после оформления
        dishQuantities.clear();
        updateOrderTextArea();
    }

    // Метод для обновления текста в области заказа
    private void updateOrderTextArea() {
        StringBuilder orderText = new StringBuilder();

        if (dishQuantities.isEmpty()) {
            orderText.append("Ваш заказ пуст.");
        } else {
            for (Map.Entry<String, Integer> entry : dishQuantities.entrySet()) {
                String dish = entry.getKey();
                int quantity = entry.getValue();
                orderText.append(dish).append(": ").append(quantity).append(" шт.\n");
            }
        }

        orderTextArea.setText(orderText.toString());
    }

    public static void main(String[] args) {
        launch();
    }
}