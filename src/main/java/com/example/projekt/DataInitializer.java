package com.example.projekt;

import com.example.projekt.model.ArtillerySite;
import com.example.projekt.model.AssociationsClasses.ArtillerySite_SupplyStation;
import com.example.projekt.model.Enums.ArtillerySiteState;
import com.example.projekt.model.FireOrder;
import com.example.projekt.model.SupplyStation;
import com.example.projekt.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final ArtillerySiteRepository artillerySiteRepository;
    private final FireOrderRepository fireOrderRepository;
    private final ArtillerySite_SupplyStationRepository artillerySupplyRepository;
    private final SupplyStationRepository supplyStationRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        boolean artilleryExists = artillerySiteRepository.count() > 0;
        if (!artilleryExists) {
            ArtillerySite artillerySite1 = ArtillerySite.builder()
                    .cannons(20)
                    .ammunition(15)
                    .location("Bydgoszcz")
                    .artillerySiteState(ArtillerySiteState.WAITING_FOR_AMMUNITION)
                    .build();

            ArtillerySite artillerySite2 = ArtillerySite.builder()
                    .cannons(12)
                    .ammunition(20)
                    .location("Gdańsk")
                    .artillerySiteState(ArtillerySiteState.WAITING_FOR_AMMUNITION)
                    .build();
            artillerySiteRepository.saveAll(Arrays.asList(artillerySite1, artillerySite2));
        }
        boolean fireOrderExists = fireOrderRepository.count() > 0;
        if (!fireOrderExists) {
            FireOrder fireOrder1 = FireOrder.builder()
                    .artilleryBarageLocation("Warszawa")
                    .duration(2)
                    .date(LocalDate.now()
                    .plusYears(200))
                    .artillerySite(artillerySiteRepository.findById(1L).get())
                    .build();

            FireOrder fireOrder2 = FireOrder.builder()
                    .artilleryBarageLocation("Warszawa")
                    .duration(5)
                    .date(LocalDate.now()
                    .plusYears(150))
                    .artillerySite(artillerySiteRepository.findById(1L).get())
                    .build();
            fireOrderRepository.saveAll(Arrays.asList(fireOrder1, fireOrder2));
        }
        boolean supplyStationExists = supplyStationRepository.count() > 0;
        if (!supplyStationExists){
            SupplyStation supplyStation1 = SupplyStation.builder()
                    .capacity(2000)
                    .ammunition(200)
                    .location("Gdynia")
                    .supplies(new HashSet<>(Arrays.asList("food", "medical supplies")))
                    .build();


            SupplyStation supplyStation2 = SupplyStation.builder()
                    .capacity(1500)
                    .ammunition(800)
                    .location("Olsztyn")
                    .supplies(new HashSet<>(Arrays.asList("food", "medical supplies")))
                    .build();

            supplyStationRepository.saveAll(Arrays.asList(supplyStation1, supplyStation2));
        }

        boolean artySupplyExists = artillerySupplyRepository.count() > 0;
        if (!artySupplyExists){
            ArtillerySite_SupplyStation artillerySite_supplyStation1 = ArtillerySite_SupplyStation.builder()
                    .artillerySite(artillerySiteRepository.findById(1L).get())
                    .supplyStation(supplyStationRepository.findById(1L).get())
                    .build();

            ArtillerySite_SupplyStation artillerySite_supplyStation2 = ArtillerySite_SupplyStation.builder()
                    .artillerySite(artillerySiteRepository.findById(1L).get())
                    .supplyStation(supplyStationRepository.findById(2L).get())
                    .build();

            artillerySupplyRepository.saveAll(Arrays.asList(artillerySite_supplyStation1, artillerySite_supplyStation2));
        }
    }

}
