<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Home</title>
        <link rel="stylesheet" type="text/css" href="../css/button/button1.css">
        <style>
                            body {
                            background-image: url("images/home_page.jpg");
                            background-repeat: no-repeat;
                            background-size: cover;
                          }
                        </style>

    </head>
    <body>
        <jsp:include page="../jsp/navbar.jsp"/>
         <c:if test="${sessionScope.user != null}">
         <h2 style="color:Blue;">Glad to see you again, ${sessionScope.user.email}</h2>
         </c:if>
    </body>
</html>