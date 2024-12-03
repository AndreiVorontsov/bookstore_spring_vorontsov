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
    <h1>Orders User </h1>
    <h3>${date}</h3>
    <table>
        <tr>
            <th>№</th>
            <th>ID</th>
            <th>User</th>
            <th>Items</th>
            <th>Status</th>
        </tr>
        <c:forEach items="${requestScope.orders}" var="order" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td><a href="/controller?command=order&id=${order.id}">${order.id}</a></td>
                <td><a href="/controller?command=user&email=${order.user.email}">${order.user.email}</a></td>
                <td>
                    <ul>
                        <c:forEach items="${order.orderItems}" var="orderItem">
                            <li>${orderItem.quantity} x <a
                            href="/controller?command=book&id=${orderItem.book.id}">${orderItem.book.name}</a></li>
                        </c:forEach>
                    </ul>
                    TOTAL PRICE: ${order.price} USD
                </td>
                <td>${order.status.toString()}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>