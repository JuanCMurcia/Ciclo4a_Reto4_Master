package com.usa.controller;

import com.usa.model.Order;
import com.usa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public List<Order>getAll(){
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Order>getOrder(@PathVariable("id")int id){
        return orderService.getOrder(id);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody Order gadget){
        return orderService.create(gadget);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Order update(@RequestBody Order gadget){
        return orderService.update(gadget);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id")int id){
        return orderService.delete(id);
    }

    @GetMapping("/zona/{zone}")
    public List<Order> findByZone(@PathVariable("zone")String zona){
        return orderService.findByZone(zona);
    }

    //Productos x Vendedor
    @GetMapping("/salesman/{id}")
    public List<Order>ordersSalesByID(@PathVariable("id")Integer id){
        return orderService.ordesSalesByID(id);
    }

    //Asesores x Estado
    @GetMapping("/state/{state}/{id}")
    public List<Order>ordersSalesByState(@PathVariable("state")String state, @PathVariable("id")Integer id){
        return orderService.ordesSalesByState(state, id);
    }

    //Asesores x Date (Fecha)
    @GetMapping("/date/{registerDay}/{id}")
    public List<Order>ordersSalesByDate(@PathVariable("registerDay")String dateStr, @PathVariable("id")Integer id){
        return orderService.ordesSalesByDate(dateStr, id);
    }
}
