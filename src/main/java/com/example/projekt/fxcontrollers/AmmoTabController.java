package com.example.projekt.fxcontrollers;

import com.example.projekt.model.ArtillerySite;
import com.example.projekt.model.AssociationsClasses.ArtillerySite_SupplyStation;
import com.example.projekt.model.FireOrder;
import com.example.projekt.model.Service;
import com.example.projekt.model.SupplyStation;
import com.example.projekt.repository.*;
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
import java.util.List;
import java.util.Optional;
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

    private final ServiceRepository serviceRepository;
    private final ArtillerySiteRepository artillerySiteRepository;
    private final FireOrderRepository fireOrderRepository;
    private final ArtillerySite_SupplyStationRepository artillerySupplyRepository;
    private final SupplyStationRepository supplyStationRepository;

    private List<Service> serviceList;
    private List<ArtillerySite> artillerySites;
    private List<FireOrder> fireOrders;
    private List<ArtillerySite_SupplyStation> artillerySiteSupplyStations;
    private List<SupplyStation> supplyStations;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        serviceList = (List<Service>) serviceRepository.findAll();
        artillerySites = (List<ArtillerySite>) artillerySiteRepository.findAll();
//        fireOrders = fireOrderRepository.fin

        loadArtilleryComboBoxInfo(artillerySites);




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

    private void loadArtilleryComboBoxInfo(List<ArtillerySite> artillerySites){

        List<String> artySiteLocations = artillerySites.stream()
                .map(ArtillerySite::getLocation).toList();

        artillery_ComboBox.getItems().addAll(artySiteLocations);

        //Co się dzieje po wybraniu danej opcji.
        artillery_ComboBox.setOnAction(e -> {
            boolean isSelected = artillery_ComboBox.getSelectionModel().getSelectedItem() != null;
            split_pane.setVisible(isSelected);

            String selectedArtillerySite = artillery_ComboBox.getSelectionModel().getSelectedItem();

            //Pobieranie ArtillerySite po wybroaniu opcji
            ArtillerySite test = artillerySites.stream()
                    .filter(artillerySite -> artillerySite.getLocation().equals(selectedArtillerySite))
                    .findAny()
                    .orElseThrow(() -> new RuntimeException("Artillery site not found"));

            loadArtillerySiteInfo(test);
        });
    }

    private void loadArtillerySiteInfo(ArtillerySite artillerySite){
        cannons_field.setText(String.valueOf(artillerySite.getCannons()));
        ammo_artillery_field.setText(String.valueOf(artillerySite.getAmmunition()));
        waiting_orders_field.setText(String.valueOf(artillerySite.getFireOrders().size()));
        int ammoNeeded = artillerySite.getCannons()-artillerySite.getAmmunition()+1;

        if (ammoNeeded >= 0) {
            ammo_needed_field.setText(String.valueOf(ammoNeeded));
        } else {
            ammo_needed_field.setText(String.valueOf(0));
        }
//        ammo_needed_field.setText(String.valueOf((artillerySite.getAmmunition()-artillerySite.getCannons()+1)));
    }
}
