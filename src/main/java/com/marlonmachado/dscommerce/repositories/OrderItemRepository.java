package com.marlonmachado.dscommerce.repositories;

import com.marlonmachado.dscommerce.entities.Order;
import com.marlonmachado.dscommerce.entities.OrderItem;
import com.marlonmachado.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {



}
