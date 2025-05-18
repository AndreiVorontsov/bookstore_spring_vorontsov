package com.vorontsov.bookstore.web.controller.impl.order;

import com.vorontsov.bookstore.service.ServiceOrder;
import com.vorontsov.bookstore.service.dto.OrderDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {
    private final ServiceOrder serviceOrder;

    @GetMapping
    public String getOrders(Model model) {
        List<OrderDto> orders = serviceOrder.getAll();
        model.addAttribute("orders", orders);
        model.addAttribute("date", LocalDateTime.now());
        return "order/orders";
    }

    @GetMapping("/{id}")
    public String getOrder(@PathVariable long id, Model model) {
        OrderDto order = serviceOrder.findById(id);
        model.addAttribute("order", order);
        model.addAttribute("date", LocalDateTime.now());
        return "order/order";
    }

    @GetMapping("/orders_user/{id}")
    public String getOrderUser(@PathVariable Long id, Model model) {
        List<OrderDto> orders = serviceOrder.findByUserId(id);
        model.addAttribute("orders", orders);
        model.addAttribute("date", LocalDateTime.now());
        return "order/ordersUser";
    }
}
