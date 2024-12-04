<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books</title>
    <link rel="stylesheet" type="text/css" href="css/button/button1.css">
</head>
<body>
    <jsp:include page="../navbar.jsp"/>
    <h1>All Books</h1>
    <h3>${date}</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>ISBN</th>
            <th>Cover</th>
            <th>Price</th>
            <th>Publication year</th>
        </tr>
        <c:forEach items="${books}" var="book">
            <tr>
                <td>${book.id}</td>
                <td><a href="/controller?command=book&id=${book.id}">| ${book.name}</a></td>
                <th>| ${book.author}</th>
                <th>| ${book.isbn}</th>
                <th>| ${book.cover}</th>
                <th>| ${book.price}$</th>
                <th>| ${book.yearPublication}</th>
            </tr>
        </c:forEach>
    </table>    
</body>
</html>