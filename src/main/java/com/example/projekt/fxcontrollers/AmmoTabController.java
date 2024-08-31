package com.example.projekt.fxcontrollers;

import com.example.projekt.model.ArtillerySite;
import com.example.projekt.model.SupplyStation;
import com.example.projekt.repository.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.*;

@Component
@RequiredArgsConstructor
public class AmmoTabController implements Initializable {

    @FXML
    private TabPane tabPane;

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

    private final ArtillerySiteRepository artillerySiteRepository;
    private final FireOrderRepository fireOrderRepository;
    private final SupplyStationRepository supplyStationRepository;

    private List<ArtillerySite> artillerySites;

    private final HashMap<Long, List<SupplyStation>> loadedSupplyStations = new HashMap<>();


    private String testSoldier = "123";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        artillerySites = (List<ArtillerySite>) artillerySiteRepository.findAll();

        if (testSoldier.equals("123")) {
            error_massage_artillery_search.setVisible(false);
            loadArtilleryComboBoxInfo(artillerySites);
        } else {
            error_massage_artillery_search.setVisible(true);
        }
    }


    /**
     * Wyświetlanie listy stanowisk artyleryjskich.
     *
     * @param artillerySites lista stanowisk artyleryjskich które będą się wyświetlać w liście rozwijanej
     */

    private void loadArtilleryComboBoxInfo(List<ArtillerySite> artillerySites) {

        List<String> artySiteLocations = artillerySites.stream()
                .map(ArtillerySite::getLocation).toList();

        artillery_ComboBox.getItems().addAll(artySiteLocations);

        artillery_ComboBox.setOnAction(e -> {
            boolean isSelected = artillery_ComboBox.getSelectionModel().getSelectedItem() != null;
            split_pane.setVisible(isSelected);
            if (isSelected) {
                loadArtillerySiteInfo(getSelectedArtillerySite());
            }

        });
    }


    /**
     * Wyświetlanie podstawowych informacji o stanowisku artyleryjskim
     *
     * @param artillerySite stanowisko artyleryjskie dla którego będziemy wypisywać informacje
     */

    private void loadArtillerySiteInfo(ArtillerySite artillerySite) {
        cannons_field.setText(String.valueOf(artillerySite.getCannons()));
        ammo_artillery_field.setText(String.valueOf(artillerySite.getAmmunition()));
        waiting_orders_field.setText(String.valueOf(fireOrderRepository.countByArtillerySiteId(artillerySite.getId())));
        int ammoNeeded = artillerySite.getCannons() - artillerySite.getAmmunition() + 1;

        if (ammoNeeded >= 0) {
            ammo_needed_field.setText(String.valueOf(ammoNeeded));
        } else {
            ammo_needed_field.setText(String.valueOf(0));
        }
        loadSupplyStationComboBox(artillerySite);
    }

    /**
     * Wyświetlanie listy stacji zaopatrzeniowych.
     *
     * @param artillerySite stanowisko artyleryjskie dla którego będziemy wyświetlać przypisane stacje zaopatrzeniowe
     */

    private void loadSupplyStationComboBox(ArtillerySite artillerySite) {

        if (!loadedSupplyStations.containsKey(artillerySite.getId())) {
            List<SupplyStation> supplyStations = artillerySiteRepository.findSupplyStationsByArtillerySiteId(artillerySite.getId());
            loadedSupplyStations.put(artillerySite.getId(), supplyStations);
        }

        List<String> supplyStationLocations = new ArrayList<>();
        for (SupplyStation supplyStation : loadedSupplyStations.get(artillerySite.getId())) {
            supplyStationLocations.add(supplyStation.getLocation());
        }

        supply_ComboBox.getItems().clear();

        if (supplyStationLocations.isEmpty()) {
            supply_ComboBox.getItems().add("<Wybierz stacje>");
            supply_ComboBox.getSelectionModel().selectFirst();
            error_massage_supply_search.setVisible(true);

        } else {

            supply_ComboBox.getItems().addAll(supplyStationLocations);
            supply_ComboBox.getSelectionModel().select("<Wybierz stacje>");
            error_massage_supply_search.setVisible(false);

        }

        supply_ComboBox.setOnAction(e -> {
            String isSelected = supply_ComboBox.getSelectionModel().getSelectedItem();
            if (isSelected != null && !isSelected.equals("<Wybierz stacje>")) {
                supply_ammo_hbox.setVisible(true);
                order_value_vbox.setVisible(true);

                String selectedSupplyStation = supply_ComboBox.getSelectionModel().getSelectedItem();

                List<SupplyStation> stations = loadedSupplyStations.get(artillerySite.getId());
                SupplyStation choosedSupplyStations = stations.stream()
                        .filter(supplyStation -> supplyStation.getLocation().equals(selectedSupplyStation))
                        .findAny()
                        .orElseThrow(() -> new RuntimeException("Supply station not found"));

                ammo_supply_field.setText(String.valueOf(choosedSupplyStations.getAmmunition()));

                loadOrderAmmoLogic(artillerySite, choosedSupplyStations);
            } else if (error_massage_supply_search.isVisible()) {
                supply_ammo_hbox.setVisible(false);
                order_value_vbox.setVisible(false);
            }

        });
    }

    /**
     * Cała logika zamawiania amunicji, wraz z robieniem updatów na baziw danuch.
     *
     * @param artillerySite stanowisko artyleryjskie go którego będziemy dostarczać amunicję
     * @param supplyStation stacja zaopatrzneiowa z której będziemy amunicję pobierać.
     */

    private void loadOrderAmmoLogic(ArtillerySite artillerySite, SupplyStation supplyStation) {
        ammo_order_field.setText("");
        error_massage_ammo_order.setVisible(false);

        order_button.setOnMouseClicked(e -> {

            String order = ammo_order_field.getText();

            if (order.isEmpty()) {

                error_massage_ammo_order.setVisible(true);

            } else if (order.chars().allMatch(Character::isDigit) && order.charAt(0) != '0' && Integer.parseInt(order) < supplyStation.getAmmunition()) {

                error_massage_ammo_order.setVisible(false);

                supplyStationRepository.updateAmmunition(supplyStation.getId(), supplyStation.getAmmunition() - Integer.parseInt(order));
                artillerySiteRepository.updateAmmunition(artillerySite.getId(), artillerySite.getAmmunition() + Integer.parseInt(order));

                refreshData(artillerySite, supplyStation, Integer.parseInt(order));

            } else {

                error_massage_ammo_order.setVisible(true);

            }
        });
    }


    /**
     * Odświeżanie informacji o stanowiskach artyleryjskich oraz stacji zaopatrzeniowych
     *
     * @param artillerySite stanowisko do którego byłą zamawiana amunicja
     * @param supplyStation stacja z której amunicję brano
     * @param ammoOrdered   ilość zamówionej amunicji
     */

    private void refreshData(ArtillerySite artillerySite, SupplyStation supplyStation, int ammoOrdered) {
        supplyStation.setAmmunition(supplyStation.getAmmunition() - ammoOrdered);

        artillerySite.setAmmunition(artillerySite.getAmmunition() + ammoOrdered);

        loadArtillerySiteInfo(artillerySite);
        ammo_supply_field.setText(String.valueOf(supplyStation.getAmmunition()));
        ammo_order_field.clear();

        supply_ammo_hbox.setVisible(false);
        order_value_vbox.setVisible(false);

    }


    /**
     * @return zwraca wybrane w liście stanowisko artyleryjskie
     */

    private ArtillerySite getSelectedArtillerySite() {
        String currentArtillerySite = artillery_ComboBox.getSelectionModel().getSelectedItem();

        return artillerySites.stream()
                .filter(artillerySite -> artillerySite.getLocation().equals(currentArtillerySite))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Artillery site not found"));
    }

}
