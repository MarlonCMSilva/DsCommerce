package com.marlonmachado.dscommerce.repositories;

import com.marlonmachado.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
