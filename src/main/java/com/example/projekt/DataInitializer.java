package com.example.projekt;

import com.example.projekt.model.Employee;
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

            Employee e1 = Employee.builder()
                    .firstName("Jan")
                    .lastName("Kowalski")
                    .build();
            Employee e2 = Employee.builder()
                    .firstName("Adam")
                    .lastName("Nowak")
                    .build();

            empRepo.saveAll(Arrays.asList(e1, e2));
        }
    }

}
