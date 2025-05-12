<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
         <h2>Glad to see you again, ${sessionScope.user.login}</h2>
         </c:if>
    </body>
</html>