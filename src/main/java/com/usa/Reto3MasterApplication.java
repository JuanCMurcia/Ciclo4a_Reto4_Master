package com.usa;

import com.usa.interfaces.ClotheInterface;
import com.usa.interfaces.OrderInterface;
import com.usa.interfaces.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class Reto3MasterApplication implements CommandLineRunner {

    @Autowired
    private UserInterface userInterface;

    @Autowired
    private ClotheInterface clotheInterface;

    @Autowired
    private OrderInterface orderInterface;

    public static void main(String[] args) {
        SpringApplication.run(Reto3MasterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userInterface.deleteAll();
        clotheInterface.deleteAll();
        orderInterface.deleteAll();
    }

}
