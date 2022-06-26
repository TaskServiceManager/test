package ru.whitegray.spring.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.whitegray.spring.web.entities.Order;
import ru.whitegray.spring.web.exceptions.ResourceNotFoundException;
import ru.whitegray.spring.web.services.OrdersService;
import ru.whitegray.spring.web.validators.OrderValidator;

import java.util.List;

@RestController
@RequestMapping("/rest_api/v1")
@RequiredArgsConstructor
@Tag(name = "Заказы", description = "Список заказов")
public class Rest_Controller {
    private final OrdersService ordersService;
    private final OrderValidator orderValidator;

    @Operation(
            summary = "Запрос на получение страницы списка заказов",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    @GetMapping
    public List<Order> getAllOrders() {
        return ordersService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Запрос на показ заказа по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Order.class))
                    )
            }
    )
    public Order getOrderById(
            @PathVariable @Parameter(description = "Идентификатор заказа", required = true) Long id
    ) {
        return ordersService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found, id: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order saveNewOrder(@RequestBody Order order) {
        order.setId(null);
        return ordersService.save(order);
    }

    @PutMapping
    public Order updateOrder(@RequestBody Order order) {
        return ordersService.update(order);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        ordersService.deleteById(id);
    }
}
