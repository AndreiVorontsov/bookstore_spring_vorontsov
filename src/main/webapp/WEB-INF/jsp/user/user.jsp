<html>
<head>
    <title>User</title>
    <link rel="stylesheet" type="text/css" href="css/button/button1.css">
</head>
<body>
    <jsp:include page="../navbar.jsp"/>
    <h1>User</h1>
    <h3>${date}</h3>
    <p><a href="/orders/orders_user/${user.id}">ID: ${user.id}</a></p>
    <p>FIO: ${user.surName}${user.name} ${user.lastName}</p>
    <p>Login: ${user.email}</p> 
    <p>Password: ${user.password}</p>
    <p>Role: ${user.role}</p>
</body>
</html>