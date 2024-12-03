package com.vorontsov.bookstore.controller.impl;

import com.vorontsov.bookstore.controller.Command;
import com.vorontsov.bookstore.service.ServiceOrder;
import com.vorontsov.bookstore.service.dto.OrderDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller("order")
@RequiredArgsConstructor
public class OrderCommand implements Command {
    private final ServiceOrder serviceOrder;

    @Override
    public String process(HttpServletRequest req) {
        long id = getId(req);
        OrderDto order = serviceOrder.findById(id);
        req.setAttribute("order", order);
        req.setAttribute("date", LocalDateTime.now());
        return "jsp/order/order.jsp";
    }

    private long getId(HttpServletRequest req) {
        String tmpId = req.getParameter("id");
        long id = Long.parseLong(tmpId);
        return id;
    }
}
