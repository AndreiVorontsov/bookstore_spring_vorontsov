<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" type="text/css" href="css/button/button1.css">
</head>
<body>
    <div class="container-buttons">
        <button> <a href="/controller?command=home" class="button1">Home</a> </button>
        <button> <a href="/controller?command=books" class="button1">Books</a> </button>
    </div>
    <h1>All Users</h1>
    <h3>${date}</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>Email</th>
            <th>Role</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td><a href="/controller?command=user&email=${user.email}">| ${user.email}</a></td>
                <th>| ${user.role}</th>
            </tr>
        </c:forEach>
    </table>    
</body>
</html>