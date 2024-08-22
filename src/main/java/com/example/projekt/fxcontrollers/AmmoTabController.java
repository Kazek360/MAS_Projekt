package com.example.projekt.fxcontrollers;

import com.example.projekt.model.ArtillerySite;
import com.example.projekt.model.AssociationsClasses.ArtillerySite_SupplyStation;
import com.example.projekt.model.FireOrder;
import com.example.projekt.model.Service;
import com.example.projekt.model.SupplyStation;
import com.example.projekt.repository.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    private final ArtillerySite_SupplyStationRepository artillerySupplyRepository;
    private final SupplyStationRepository supplyStationRepository;

    private List<ArtillerySite> artillerySites;
    private List<FireOrder> fireOrders;
    private List<ArtillerySite_SupplyStation> artillerySiteSupplyStations;
    private List<SupplyStation> supplyStations;

    private String testSoldier = "123";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        artillerySites = (List<ArtillerySite>) artillerySiteRepository.findAll();
//        fireOrders = (List<FireOrder>) fireOrderRepository.findAll();
//        artillerySiteSupplyStations = (List<ArtillerySite_SupplyStation>) artillerySupplyRepository.findAll();
//        supplyStations = (List<SupplyStation>) supplyStationRepository.findAll();

        if (testSoldier.equals("123")){
            error_massage_artillery_search.setVisible(false);
            loadArtilleryComboBoxInfo(artillerySites);
        } else {
            error_massage_artillery_search.setVisible(true);
        }
    }
/*

    */
/**
     * Wyświetlanie listy stanowisk artyleryjskich.
     *
     * @param artillerySites lista stanowisk artyleryjskich które będą się wyświetlać w liście rozwijanej
     */

    private void loadArtilleryComboBoxInfo(List<ArtillerySite> artillerySites) {

        List<String> artySiteLocations = artillerySites.stream()
                .map(ArtillerySite::getLocation).toList();

        artillery_ComboBox.getItems().addAll(artySiteLocations);

        //Co się dzieje po wybraniu danej opcji.
        artillery_ComboBox.setOnAction(e -> {
            boolean isSelected = artillery_ComboBox.getSelectionModel().getSelectedItem() != null;
            split_pane.setVisible(isSelected);
            if (isSelected){
                loadArtillerySiteInfo(getSelectedArtillerySite());
            }
//            refreshRepositoriesAndInfo();

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

        //Wyszukiwanie stacji zaopatrzeniowych przypisanych do artylerii. Używam klasy asocjacyjnej
        List<String> supplyStationLocations = artillerySiteSupplyStations.stream()
                .filter(artillerySite_supplyStation -> artillerySite_supplyStation.getArtillerySite().equals(artillerySite))
                .map(artillerySite_supplyStation -> artillerySite_supplyStation.getSupplyStation().getLocation())
                .collect(Collectors.toList());

        supply_ComboBox.getItems().clear();

        if (supplyStationLocations.isEmpty()) {

            supply_ComboBox.getItems().add("<Wybierz stacje>");
            supply_ComboBox.getSelectionModel().selectFirst();
            error_massage_supply_search.setVisible(true);

        } else {

            supply_ComboBox.getSelectionModel().select("<Wybierz stacje>");
            supply_ComboBox.getItems().addAll(supplyStationLocations);
            error_massage_supply_search.setVisible(false);

        }

/*
        supply_ComboBox.setOnAction(e -> {
            String isSelected = supply_ComboBox.getSelectionModel().getSelectedItem();
            if (isSelected != null && !isSelected.equals("<Wybierz stacje>")) {
                supply_ammo_hbox.setVisible(true);
                order_value_vbox.setVisible(true);

                String selectedSupplyStation = supply_ComboBox.getSelectionModel().getSelectedItem();


                SupplyStation choosedSupplyStations = supplyStations.stream()
                        .filter(supplyStation -> supplyStation.getLocation().equals(selectedSupplyStation))
                        .findAny()
                        .orElseThrow(() -> new RuntimeException("Supply station not found"));


                //Ładowanie info o amunicji na stacji zaopatrzeniowej
                ammo_supply_field.setText(String.valueOf(choosedSupplyStations.getAmmunition()));

//                loadOrderAmmoLogic(artillerySite, choosedSupplyStations);
            } else {
                supply_ammo_hbox.setVisible(false);
                order_value_vbox.setVisible(false);
            }

        });*/
    }

/**
     * Cała logika zamawiania amunicji, wraz z robieniem updatów na baziw danuch.
     *
     * @param artillerySite stanowisko artyleryjskie go którego będziemy dostarczać amunicję
     * @param supplyStation stacja zaopatrzneiowa z której będziemy amunicję pobierać.
     *//*

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

                refreshRepositoriesAndInfo();

            } else {

                error_massage_ammo_order.setVisible(true);

            }
        });
    }

    */
/**
     * Odświeżanie informacji o stanowiskach artyleryjskich oraz stacji zaopatrzeniowych
     */

    private void refreshRepositoriesAndInfo() {
/*        artillerySites = (List<ArtillerySite>) artillerySiteRepository.findAll();
        supplyStations = (List<SupplyStation>) supplyStationRepository.findAll();
        fireOrders = (List<FireOrder>) fireOrderRepository.findAll();
        artillerySiteSupplyStations = (List<ArtillerySite_SupplyStation>) artillerySupplyRepository.findAll();*/

        ArtillerySite choosedArtillerySite = getSelectedArtillerySite();
//        List<FireOrder> filteredFireOrders = getFilteredFireOrders(choosedArtillerySite);
//
//        loadArtillerySiteInfo(choosedArtillerySite, filteredFireOrders);
//        System.out.println(filteredFireOrders);
//        loadSupplyStationComboBox(choosedArtillerySite);
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

/**
     * @param artillerySite stanowisko artyleryjskie dla jakiego szukamy połączonych stacji zaopatrzeniowych
     * @return stacje zaopatrzeniowe przypisane do stanowiska artyleryjskiego
     */

    private List<FireOrder> getFilteredFireOrders(ArtillerySite artillerySite) {
        return fireOrders.stream()
                .filter(fireOrder -> fireOrder.getArtillerySite().equals(artillerySite))
                .collect(Collectors.toList());
    }

}
