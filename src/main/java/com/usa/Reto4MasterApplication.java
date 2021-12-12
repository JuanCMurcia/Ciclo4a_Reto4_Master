package com.usa;

import com.usa.interfaces.ClotheInterface;
import com.usa.interfaces.OrderInterface;
import com.usa.interfaces.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Component
@SpringBootApplication
public class Reto4MasterApplication implements CommandLineRunner {

    @Autowired
    private UserInterface userInterface;

    @Autowired
    private ClotheInterface clotheInterface;

    @Autowired
    private OrderInterface orderInterface;

    public static void main(String[] args) {
        SpringApplication.run(Reto4MasterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        userInterface.deleteAll();
        clotheInterface.deleteAll();
        orderInterface.deleteAll();
    }

}
