package com.example.projekt;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * A main FX class, its run from main class (JavafxSpringDemoApplication)
 */
public class FXApplication extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {
        super.init();

        //initialize spring context
        context = new SpringApplicationBuilder(ProjektApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        //when app is started publish event that should trigger creation of GUI (in StageInitializer class)
        context.publishEvent(new StageReadyEvent(stage));

        stage.setTitle("SoldierGUI");

        stage.setOnCloseRequest(event -> {
            stage.close();
            event.consume();
        });

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.stop();
        Platform.exit();
        System.exit(0);
    }

    public static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }

        public Stage getStage() {
            return (Stage) getSource();
        }
    }
}
