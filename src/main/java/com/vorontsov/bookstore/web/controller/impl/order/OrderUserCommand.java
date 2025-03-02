package com.vorontsov.bookstore.web.controller.impl.order;

import com.vorontsov.bookstore.web.controller.Command;
import com.vorontsov.bookstore.service.ServiceOrder;
import com.vorontsov.bookstore.service.dto.OrderDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller("orders_user")
@RequiredArgsConstructor
public class OrderUserCommand implements Command {
    private final ServiceOrder serviceOrder;

    @Override
    public String process(HttpServletRequest req) {
        long user_id = getId(req);
        List<OrderDto> orders = serviceOrder.findByUserId(user_id);
        req.setAttribute("orders", orders);
        req.setAttribute("date", LocalDateTime.now());
        return "jsp/order/ordersUser.jsp";
    }

    private long getId(HttpServletRequest req) {
        String tmpId = req.getParameter("id");
        long id = Long.parseLong(tmpId);
        return id;
    }
}
