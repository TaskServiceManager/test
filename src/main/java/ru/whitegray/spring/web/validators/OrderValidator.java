package ru.whitegray.spring.web.validators;

import ru.whitegray.spring.web.entities.Order;
import ru.whitegray.spring.web.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderValidator {
    public void validate(Order order) {
        List<String> errors = new ArrayList<>();
        if (order.getText().isBlank()) {
            errors.add("Необходимо описать задание");
        }
        if (order.getTitle().isBlank()) {
            errors.add("У задания обязательно должно быть название");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
