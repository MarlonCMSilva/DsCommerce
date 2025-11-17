package com.marlonmachado.dscommerce.services;

import com.marlonmachado.dscommerce.dto.OrderDTO;
import com.marlonmachado.dscommerce.dto.OrderItemDTO;
import com.marlonmachado.dscommerce.dto.ProductDTO;
import com.marlonmachado.dscommerce.entities.*;
import com.marlonmachado.dscommerce.repositories.OrderItemRepository;
import com.marlonmachado.dscommerce.repositories.OrderRepository;
import com.marlonmachado.dscommerce.repositories.ProductRepository;
import com.marlonmachado.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productrRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Recurso não encontrado"));
        return new OrderDTO(order);
    }

    @Transactional
    public  OrderDTO insert(OrderDTO dto) {

        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticate();
        order.setClient(user);

        for(OrderItemDTO itemDTO : dto.getItems()){
            Product product = productrRepository.getReferenceById(itemDTO.getProductId());
            OrderItem item = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }

        repository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);
    }

}
