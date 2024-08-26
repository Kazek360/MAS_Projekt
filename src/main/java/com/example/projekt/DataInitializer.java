package com.example.projekt;

import com.example.projekt.model.*;
import com.example.projekt.model.Enums.*;
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
//    private final ArtillerySite_SupplyStationRepository artillerySupplyRepository;
    private final CarrierRepository carrierRepository;
    private final CruiserRepository cruiserRepository;
    private final FireOrderRepository fireOrderRepository;
    private final InfantrymanRepository infantrymanRepository;
    private final MarineRepository marineRepository;
    private final OperationRepository operationRepository;
    private final SailorRepository sailorRepository;
    private final ServiceRepository serviceRepository;
    private final SoldierRepository soldierRepository;
    private final SpaceShip_OperationRepository spaceShipOperationRepository;
    private final SpaceShipRepository spaceShipRepository;
    private final SupplyStationRepository supplyStationRepository;
    private final TransporterRepository transporterRepository;
    private final UnitRepository unitRepository;

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
        if (!supplyStationExists) {
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

           /* ArtillerySite artillerySite = artillerySiteRepository.findById(1L).get();

            // Dodaj supply stations do artylery site
            artillerySite.getSupplyStations().add(supplyStation1);
            artillerySite.getSupplyStations().add(supplyStation2);

            // Dodaj artylery site do supply stations (opcjonalnie, jeśli dwustronna relacja jest potrzebna)
            supplyStation1.getArtillerySites().add(artillerySite);
            supplyStation2.getArtillerySites().add(artillerySite);

            // Zapisz zmiany w bazie danych
            artillerySiteRepository.save(artillerySite);
            supplyStationRepository.save(supplyStation1);
            supplyStationRepository.save(supplyStation2);*/
        }

        /*boolean artySupplyExists = artillerySupplyRepository.count() > 0;
        if (!artySupplyExists) {
            ArtillerySite artillerySite = artillerySiteRepository.findById(1L).get();
            SupplyStation supplyStation1 = supplyStationRepository.findById(1L).get();
            SupplyStation supplyStation2 = supplyStationRepository.findById(2L).get();

            // Dodaj supply stations do artylery site
            artillerySite.getSupplyStations().add(supplyStation1);
            artillerySite.getSupplyStations().add(supplyStation2);

            // Dodaj artylery site do supply stations (opcjonalnie, jeśli dwustronna relacja jest potrzebna)
            supplyStation1.getArtillerySites().add(artillerySite);
            supplyStation2.getArtillerySites().add(artillerySite);

            // Zapisz zmiany w bazie danych
            artillerySiteRepository.save(artillerySite);
            supplyStationRepository.save(supplyStation1);
            supplyStationRepository.save(supplyStation2);
        }*/






        /*boolean carrierExist = carrierRepository.count() > 0;
        if (!carrierExist) {
            Carrier carrier = Carrier.builder()
                    .name("Invinvible")
                    .mass(202002020200.0)
                    .fuel(323456421.0)
                    .fighters(new HashSet<>(Arrays.asList("Archangel 1", "Archangel 2", "Archangel 3", "Millenium Falcon")))
                    .build();

            carrierRepository.saveAll(Arrays.asList(carrier));
        }

        boolean cruiserExists = cruiserRepository.count() > 0;
        if (!cruiserExists) {
            Cruiser cruiser = Cruiser.builder()
                    .name("Zło-To")
                    .mass(123321321.02)
                    .fuel(546151.45)
                    .numberOfSpecialWapons(5)
                    .build();

            cruiserRepository.saveAll(Arrays.asList(cruiser));
        }

        boolean transporterExists = transporterRepository.count() > 0;
        if (!transporterExists) {
            Transporter transporter = Transporter.builder()
                    .name("Rocinante")
                    .mass(3500.0)
                    .fuel(12345.0)
                    .magazinSpace(2500)
                    .build();

            transporterRepository.saveAll(Arrays.asList(transporter));
        }

        boolean operationExists = operationRepository.count() > 0;
        if (!operationExists) {
            Operation operation = Operation.builder()
                    .operationType(OperationType.ATTACK)
                    .sector("Gothic")
                    .planet("Klendathu")
                    .build();

            operationRepository.saveAll(Arrays.asList(operation));
        }

        boolean spaceShipOperationExists = spaceShipOperationRepository.count() > 0;
        if (!spaceShipOperationExists) {
            SpaceShip_Operation spaceShip_operation = SpaceShip_Operation.builder()
                    .spaceShip(spaceShipRepository.findById(1L).get())
                    .operation(operationRepository.findById(1L).get())
                    .build();

            SpaceShip_Operation spaceShip_operation2 = SpaceShip_Operation.builder()
                    .spaceShip(spaceShipRepository.findById(2L).get())
                    .operation(operationRepository.findById(1L).get())
                    .build();

            spaceShipOperationRepository.saveAll(Arrays.asList(spaceShip_operation, spaceShip_operation2));
        }
        boolean marineExists = marineRepository.count() > 0;
        if (!marineExists) {
            Marine marine1 = Marine.builder()
                    .identyficationNumber("321456")
                    .name("Johnny")
                    .surname("Rico")
                    .rank(Rank.MASTER_SERGEANT)
                    .pay(3500)
                    .specialities(new HashSet<>(Arrays.asList("Lasers", "Bombs", "Annihilating bugs")))
                    .equipment(new HashSet<>(Arrays.asList("Laser Carnine", "Nuclear Hand Granade")))
                    .currentMissionsNumber(20)
                    .trainingLevel(TrainingLevel.ADVANCED)
                    .operationalStatus(OperationalStatus.BATTLE_READY)
                    .seaRank(SeaRank.MASTER_CHIEF_PETTY_OFFICER_OF_THE_NAVY)
                    .build();

            marineRepository.saveAll(Arrays.asList(marine1));
        }



        boolean infantrymanExists = infantrymanRepository.count() > 0;
        if (!infantrymanExists) {
            Infantryman infantryman1 = Infantryman.builder()
                    .identyficationNumber("1234")
                    .name("Grzegorz")
                    .surname("Brzęczyszczykiewicz")
                    .rank(Rank.SERGEANT)
                    .pay(2000)
                    .trainingLevel(TrainingLevel.BASIC)
                    .medicCourses(new HashSet<>(Arrays.asList("FirstAid", "Surgon")))
                    .equipment(new HashSet<>(Arrays.asList("M4A1 Carbine", "Knife")))
                    .build();


            Infantryman infantryman2 = Infantryman.builder()
                    .identyficationNumber("4567")
                    .name("Bohdan")
                    .surname("Tymieniecki")
                    .rank(Rank.FIRST_LIEUTENANT)
                    .pay(3000)
                    .trainingLevel(TrainingLevel.SPECIALIZED)
                    .specialities(new HashSet<>(Arrays.asList("Tanks", "Horses")))
                    .equipment(new HashSet<>(Arrays.asList("Binocular", "Pan", "Colt", "Artyllery")))
                    .build();

            infantrymanRepository.saveAll(Arrays.asList(infantryman1, infantryman2));
        }

        boolean unitExists = unitRepository.count() > 0;
        if (!unitExists) {
            Unit unit = Unit.builder()
                    .name("Deep Recon Unit")
                    .location("Białowieża")
                    .unitNumber(21)
                    .commander(infantrymanRepository.findById(1L).get())
                    .operation(operationRepository.findById(1L).get())
                    .build();

            unitRepository.saveAll(Arrays.asList(unit));
        }
        boolean sailorExists = sailorRepository.count() > 0;
        if (!sailorExists) {
            Sailor sailor1 = Sailor.builder()
                    .identyficationNumber("54651")
                    .name("Jerzy")
                    .surname("Świrski")
                    .rank(Rank.CAPTAIN)
                    .pay(3500)
                    .trainingLevel(TrainingLevel.ADVANCED)
                    .specialities(new HashSet<>(Arrays.asList("Ships")))
                    .seaRank(SeaRank.CAPTAIN)
                    .spaceShip(carrierRepository.findById(1L).get())
                    .build();

            Sailor sailor2 = Sailor.builder()
                    .identyficationNumber("12354651")
                    .name("Papaye")
                    .surname("Big")
                    .rank(Rank.PRIVATE)
                    .pay(3500)
                    .trainingLevel(TrainingLevel.BASIC)
                    .specialities(new HashSet<>(Arrays.asList("Eating spinach")))
                    .seaRank(SeaRank.SEAMAN)
                    .spaceShip(cruiserRepository.findById(2L).get())
                    .build();

            sailorRepository.saveAll(Arrays.asList(sailor1, sailor2));
        }

        boolean serviceExists = serviceRepository.count() > 0;
        if (!serviceExists) {
            Service service = Service.builder()
                    .soldierID("4567")
                    .dateFrom(LocalDate.now().plusYears(253))
                    .operation(operationRepository.findById(1L).get())
                    .artillerySite(artillerySiteRepository.findById(1L).get())
                    .build();
            serviceRepository.saveAll(Arrays.asList(service));
        }*/
    }


}
