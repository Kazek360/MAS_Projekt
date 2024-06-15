package com.example.projekt;

import com.example.projekt.model.ArtillerySite;
import com.example.projekt.model.Enums.ArtillerySiteState;
import com.example.projekt.model._Employee;
import com.example.projekt.repository.ArtillerySiteRepository;
import com.example.projekt.repository._EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final _EmployeeRepository empRepo;
    private final ArtillerySiteRepository artillerySiteRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        boolean employeeExists = empRepo.count() > 0;
        if(!employeeExists) {

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
        if (!artilleryExists){
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
            artillerySiteRepository.saveAll(Arrays.asList(artillerySite1,artillerySite2));
        }
    }

}
