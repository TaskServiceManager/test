package ru.whitegray.spring.web.repositories;

import ru.whitegray.spring.web.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
}
