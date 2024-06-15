package com.example.projekt;

import com.example.projekt.model.ArtillerySite;
import com.example.projekt.model.Enums.ArtillerySiteState;
import com.example.projekt.model.FireOrder;
import com.example.projekt.model._Employee;
import com.example.projekt.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final _EmployeeRepository empRepo;
    private final ArtillerySiteRepository artillerySiteRepository;
    private final FireOrderRepository fireOrderRepository;
    private final ArtillerySite_SupplyStationRepository artillerySupplyRepository;
    private final SupplyStationRepository supplyStationRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        boolean employeeExists = empRepo.count() > 0;
        if (!employeeExists) {

            _Employee e1 = _Employee.builder()
                    .firstName("Jan")
                    .lastName("Kowalski")
                    .build();
            _Employee e2 = _Employee.builder()
                    .firstName("Adam")
                    .lastName("Nowak")
                    .build();

            empRepo.saveAll(Arrays.asList(e1, e2));

        }
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
    }

}
