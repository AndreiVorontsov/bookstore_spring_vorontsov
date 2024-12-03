package com.vorontsov.bookstore.controller.impl;

import com.vorontsov.bookstore.controller.Command;
import com.vorontsov.bookstore.service.ServiceOrder;
import com.vorontsov.bookstore.service.dto.OrderDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller("orders")
@RequiredArgsConstructor
public class OrdersCommand implements Command {
    private final ServiceOrder serviceOrder;

    @Override
    public String process(HttpServletRequest req) {
        List<OrderDto> orders = serviceOrder.getAll();
        req.setAttribute("orders", orders);
        req.setAttribute("date", LocalDateTime.now());
        return "jsp/order/orders.jsp";
    }
}
