package com.example.projekt.fxcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class AmmoTabController implements Initializable {

    @FXML
    private ComboBox<String> artillery_ComboBox;

    @FXML
    private Text error_massage_artillery_search;

    @FXML
    private SplitPane split_pane;

    @FXML
    private Text cannons_field;

    @FXML
    private Text ammo_artillery_field;

    @FXML
    private Text waiting_orders_field;

    @FXML
    private Text ammo_needed_field;

    @FXML
    private ComboBox<String> supply_ComboBox;

    @FXML
    private Text error_massage_supply_search;

    @FXML
    private HBox supply_ammo_hbox;

    @FXML
    private Text ammo_supply_field;

    @FXML
    private VBox order_value_vbox;

    @FXML
    private TextField ammo_order_field;

    @FXML
    private Text error_massage_ammo_order;

    @FXML
    private Button order_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ustawienia początkowe ComboBox
        artillery_ComboBox.getItems().addAll("Stanowisko 1", "Stanowisko 2", "Stanowisko 3");
//        supply_ComboBox.getItems().addAll("Stacja 1", "Stacja 2", "Stacja 3");

        // Ustawienia początkowe tekstów
        cannons_field.setText(String.valueOf(2));
        ammo_artillery_field.setText("120");
        waiting_orders_field.setText("2");
        ammo_needed_field.setText("30");
        ammo_supply_field.setText("100");

        artillery_ComboBox.setOnAction(e -> {
            boolean isSelected = artillery_ComboBox.getSelectionModel().getSelectedItem() != null;
            split_pane.setVisible(isSelected);
        });

/*        // Obsługa przycisku zamówienia
        order_button.setOnAction(event -> {
            try {
                int orderValue = Integer.parseInt(ammo_order_field.getText());
                if (orderValue < 0) {
                    throw new NumberFormatException();
                }
                // Tutaj możesz dodać logikę zamawiania amunicji
                System.out.println("Zamówiono: " + orderValue);
                error_massage_ammo_order.setVisible(false);
            } catch (NumberFormatException e) {
                error_massage_ammo_order.setVisible(true);
            }
        });*/
    }
}
