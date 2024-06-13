package com.example.projekt;

import com.example.projekt.model._Employee;
import com.example.projekt.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final EmployeeRepository empRepo;

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
    }

}
