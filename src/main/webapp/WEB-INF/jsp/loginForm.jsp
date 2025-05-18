<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
      <title>Login</title>
      <link rel="stylesheet" type="text/css" href="../css/button/button1.css">
    </head>
   <body>
       <jsp:include page="../jsp/navbar.jsp"/>
           <h1>Login</h1>
           <form class="login-form" action="login" method="post" >
                <label>Login: <input name="login" type="text"></label>
               <br/>
               <label>Password: <input name="password" type="password"></label>
                <br/>
                <button>Login</button>
           </form>
    </body>
</html>