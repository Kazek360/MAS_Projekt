package com.example.projekt.fxcontrollers;

import com.example.projekt.model._Employee;
import com.example.projekt.repository._EmployeeRepository;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class HelloController implements Initializable {

    private final _EmployeeRepository empRepo;

    private List<_Employee> emps;


    public Label lbl_e1;
    public Label lbl_e2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emps = (List<_Employee>) empRepo.findAll();
        if(emps.size() >= 2) {
            lbl_e1.setText(emps.get(0).toString());
            lbl_e2.setText(emps.get(1).toString());
        }
    }
}
