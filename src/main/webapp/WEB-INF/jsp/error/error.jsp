<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="css/button/button1.css">
</head>
<body>
   <ul>
      <li><a href="/">Home</a></li>
   </ul>
    <jsp:include page="../navbar.jsp"/>
    <h1>Error occurred...</h1>
        <p>Status: ${status}</p>
        <p>Reason: ${massage}</p>
</body>
</html>    