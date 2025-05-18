<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container-buttons">
    <button> <a href="/" class="button1">Home</a> </button>
    <button> <a href="/books" class="button1">Books</a></button>
    <button> <a href="/users" class="button1">Users</a></button>
    <button> <a href="/orders" class="button1">All orders</a></button>
    <c:if test="${sessionScope.user != null}">
     <button> <a href="/logout" class="button1">Logout</a></button>
    </c:if>
    <c:if test="${sessionScope.user == null}">
    <button> <a href="/users/create" class="button1">Register</a></button>
    <button> <a href="/login" class="button1">Login</a></button>
    </c:if>
</div>