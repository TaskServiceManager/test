package ru.whitegray.spring.web.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.whitegray.spring.web.entities.Order;
import ru.whitegray.spring.web.repositories.OrdersRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public List<Order> findAll() {
        return ordersRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return ordersRepository.findById(id);
    }

    public void deleteById(Long id) {
        ordersRepository.deleteById(id);
    }

    public Order save(Order order) {
        return ordersRepository.save(order);
    }

    @Transactional
    public Order update(Order order) {
        Order entity = null;
        if (order.getId() != null) {
            entity = ordersRepository.findById(order.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Order with id " + order.getId() + " doesn't exist!"));
        } else {
            entity = ordersRepository.save(new Order(order.getTitle(), order.getText(), order.getComment()));
        }
        return entity;
    }
}
