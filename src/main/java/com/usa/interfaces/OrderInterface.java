package com.usa.interfaces;

import com.usa.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderInterface extends MongoRepository<Order, Integer> {

    @Query("{'salesMan.zone': ?0}")
    List<Order>findByZone(final String zone);

    @Query("{status: ?0}")
    List<Order>findByStatus(final String status);

    Optional<Order>findTopByOrderByIdDesc();
}
