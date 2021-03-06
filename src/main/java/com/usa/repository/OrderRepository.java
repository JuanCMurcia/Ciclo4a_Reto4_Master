package com.usa.repository;

import com.usa.interfaces.OrderInterface;
import com.usa.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

    @Autowired
    private OrderInterface orderInterface;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Order>getAll(){
        return (List<Order>) orderInterface.findAll();
    }

    public Optional<Order>getOrder(int id){
        return orderInterface.findById(id);
    }

    public Order create(Order order){
        return orderInterface.save(order);
    }

    public void update(Order order){
        orderInterface.save(order);
    }

    public void delete(Order order){
        orderInterface.delete(order);
    }

    public Optional<Order>lastUserId(){
        return orderInterface.findTopByOrderByIdDesc();
    }

    public List<Order>findByZone(String zona){
        return orderInterface.findByZone(zona);
    }

    //Listado Ordenes de Producto x Vendedor
    public List<Order>ordersSalesByID(Integer id){
        Query query = new Query();

        Criteria criterio = Criteria.where("salesMan.id").is(id);
        query.addCriteria(criterio);

        List<Order> orders = mongoTemplate.find(query, Order.class);
        return orders;
    }

    //Listado de Asesores x Estado
    public List<Order>ordersSalesByState(String state, Integer id){
        Query query = new Query();

        Criteria criterio = Criteria.where("salesMan.id").is(id).and("status").is(state);
        query.addCriteria(criterio);

        List<Order> orders = mongoTemplate.find(query, Order.class);
        return orders;
    }

    //Listado Ordenes Asesor x Fecha
    public List<Order>ordersSalesByDate(String dateStr, Integer id){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Query query = new Query();

        Criteria criterio = Criteria.where("registerDay")
                .gte(LocalDate.parse(dateStr, dtf).minusDays(1).atStartOfDay())
                .lt(LocalDate.parse(dateStr, dtf).plusDays(1).atStartOfDay())
                .and("salesMan.id").is(id);

        query.addCriteria(criterio);

        List<Order> orders = mongoTemplate.find(query, Order.class);
        return orders;
    }
}
