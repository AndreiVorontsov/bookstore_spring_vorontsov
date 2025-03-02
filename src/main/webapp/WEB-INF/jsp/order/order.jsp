<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" type="text/css" href="css/button/button1.css">
</head>
<body>
    <jsp:include page="../navbar.jsp"/>
    <h1>Order Detailed Info</h1>
    <h3>${date}</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>User</th>
            <th>Items</th>
            <th>Status</th>
        </tr>
        <tr>   
            <td><a href="/orders/${requestScope.order.id}">${order.id}</a></td>
            <td><a href="/users/${requestScope.order.user.email}">${order.user.email}</a></td>
            <td>
                <table>
                    <c:forEach items="${requestScope.order.orderItems}" var="orderItem">
                        <tr>
                            <td><a href="/books/${orderItem.book.id}">${orderItem.book.name}</a></td>
                            <td>$${orderItem.bookPrice} x ${orderItem.quantity}</td>
                        </tr>
                    </c:forEach>
                </table>
                TOTAL PRICE: ${requestScope.order.price} USD
            </td>
            <td>${requestScope.order.status.toString()}</td>
        </tr>
    </table>
</body>
</html>