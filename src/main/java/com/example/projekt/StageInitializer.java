package com.example.projekt;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * A class which creates first FX GUI View. Its invoked via event from  
 */
@Component
@RequiredArgsConstructor
public class StageInitializer implements ApplicationListener<FXApplication.StageReadyEvent> {

    @Value("classpath:/fxml/Hello.fxml")
    private Resource helloFxml;

    @Value("classpath:/fxml/Ammunition_Chose_Artillery.fxml")
    private Resource ammoFxml;
    private final ApplicationContext springContext;

    @Override
    public void onApplicationEvent(FXApplication.StageReadyEvent event) {
        try {
//            FXMLLoader loader = new FXMLLoader(helloFxml.getURL());
            FXMLLoader loader = new FXMLLoader(ammoFxml.getURL());
            loader.setControllerFactory(controllerClass -> springContext.getBean(controllerClass));
            Parent parent = loader.load();
            Stage stage = event.getStage();
            stage.setScene(new Scene(parent, 600, 500));
            stage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
