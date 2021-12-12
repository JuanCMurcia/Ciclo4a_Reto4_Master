package com.usa.service;

import com.usa.model.Order;
import com.usa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll(){
        return orderRepository.getAll();
    }

    public Optional<Order> getOrder(int id){
        return orderRepository.getOrder(id);
    }

    public Order create(Order order){
        Optional<Order> orderIdMaxima = orderRepository.lastUserId();

        if (order.getId() == null) {
            if (orderIdMaxima.isEmpty()) {
                order.setId(1);
            } else {
                order.setId(orderIdMaxima.get().getId() + 1);
            }
        }

        Optional<Order> evc = orderRepository.getOrder(order.getId());
        if (evc.isEmpty()) {
            return orderRepository.create(order);
        }else {
            return order;
        }
    }

    public Order update(Order order){
        if (order.getId() != null){
            Optional<Order> orderDb = orderRepository.getOrder(order.getId());
            if (!orderDb.isEmpty()){
                if (order.getStatus() != null){
                    orderDb.get().setStatus(order.getStatus());
                }
                orderRepository.update(orderDb.get());
                return orderDb.get();
            }else {
                return order;
            }
        }else {
            return order;
        }
    }

    public boolean delete(int orderId) {
        boolean del = getOrder(orderId).map(order -> {
            orderRepository.delete(order);
            return true;
        }).orElse(false);
        return del;
    }

    //Orden de Pedidos asociadas a Zonas
    public List<Order>findByZone(String zona){
        return orderRepository.findByZone(zona);
    }

    //Ordenes de Producto x Vendedor
    public List<Order>ordesSalesByID(Integer id){
        return orderRepository.ordersSalesByID(id);
    }

    //Ordenes de Asesores x Estado
    public List<Order>ordesSalesByState(String state, Integer id){
        return orderRepository.ordersSalesByState(state, id);
    }

    //ordenes de Asesores x Fecha
    public List<Order>ordesSalesByDate(String dateStr, Integer id){
        return orderRepository.ordersSalesByDate(dateStr, id);
    }
}
