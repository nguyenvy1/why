package com.rover.interview.sitterrank.config;

import com.rover.interview.sitterrank.dao.DatabaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    DatabaseClient client;

    @Autowired
    ApplicationReadyListener(DatabaseClient client) {
        this.client = client;
    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Initialize dogs in the backend
        client.initDogs();
    }

}
