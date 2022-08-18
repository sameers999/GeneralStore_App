package com.imi.order_service.service;

import com.imi.order_service.dto.GeneralStoreDTO;
import com.imi.order_service.dto.OrderDTO;
import com.imi.order_service.exception.GeneralStoreException;
import com.imi.order_service.exception.OrderException;
import com.imi.order_service.exception.UserException;
import com.imi.order_service.model.GeneralStoreData;
import com.imi.order_service.model.Order;
import com.imi.order_service.repository.OrderRepository;
import jdk.jshell.spi.ExecutionControl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService implements IOrderService{
    @Autowired
    OrderRepository repo;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Order insertOrder(OrderDTO dto) {
        Object user = restTemplate.getForObject("http://localhost:8086/user/findById/" + dto.getUserId(), Object.class);
        if (user.equals(null)) {
            throw new UserException("Invalid user id...please provide valid user id");
        } else {
            GeneralStoreData generalStoreData = restTemplate.getForObject("http://localhost:8085/products/getGeneralStoreData/" + dto.getProductId(), GeneralStoreData.class);
            if (generalStoreData.equals(null)) {
                throw new GeneralStoreException("Invalid product id...please provide valid product id");
            } else {
                if (dto.getQuantity() > (int) generalStoreData.getProductQuantity()) {
                    throw new GeneralStoreException("Currently we dont have that much products available");
                } else {
                    Order order = new Order(dto);
                    order.setPrice(dto.getQuantity() * generalStoreData.getProductPrice());
                    order.setDate(LocalDate.now());
                    repo.save(order);
                    return order;
                }
            }
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = repo.findAll();
        return orders;
    }

    @Override
    public Order getByID(Integer orderId) {
        Optional<Order> order = repo.findById(orderId);
        if (order.isPresent()) {
            throw new OrderException("Invalid Order Id...please provide valid Order id");
        }
        return order.get();
    }

    @Override
    public Order updateById(Integer orderId, OrderDTO dto) {
        Optional<Order> order = repo.findById(orderId);
        if (order.isPresent()) {
            throw new OrderException("Invalid Order Id...please provide valid Order id");
        }
        Object user = restTemplate.getForObject("http://USER-CLIENT/user/findById/" + dto.getUserId(), Object.class);
        if (user.equals(null)) {
            throw new UserException("Invalid user id...please provide valid user id");
        }
        GeneralStoreData generalStoreData = restTemplate.getForObject("http://GENERALSTORESERVICE/products/getGeneralStoreData/" + dto.getProductId(), GeneralStoreData.class);
        if (generalStoreData.equals(null)) {
            throw new GeneralStoreException("Invalid product id...please provide valid product id");
        }
        Order newOrder = new Order(dto);
        newOrder.setPrice(dto.getQuantity() * generalStoreData.getProductPrice());
        repo.save(newOrder);
        return newOrder;
    }

    @Override
    public Order deleteById(Integer orderId) {
        Optional<Order> order = repo.findById(orderId);
        if (order.isPresent()) {
            throw new OrderException("Invalid Order Id...please provide valid Order id");
        }
        repo.delete(order.get());
        return order.get();
    }
}
