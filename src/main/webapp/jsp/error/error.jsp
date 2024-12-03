<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="css/button/button1.css">
</head>
<body>
    <jsp:include page="../navbar.jsp"/>
    <h1>Error</h1>
        <p>Something went wrong ...</p>
        <c:if test="${error_command!=null}">
        <p>invalid value -> ${error_command} </p>
        </c:if>
        <c:if test="${error_email!=null}">
            <p>invalid email -> ${error_email}</p>
        </c:if>
        <c:if test="${exception!=null}">
            <p>${exception}</p>
        </c:if>
</body>
</html>    