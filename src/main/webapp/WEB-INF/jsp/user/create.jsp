<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
       <title>Register new user</title>
       <link rel="stylesheet" type="text/css" href="css/button/button1.css">
   </head>
   <body>
       <jsp:include page="../navbar.jsp"/>
           <h1>Register new user</h1>
           <form action="create" method="post" >
                <input name="command" value="create_user" type="hidden"/>

                <label for="email-input">Email: </label>
                <input name="email" type="email"/>

                <br/>

                <label for="password-input">Password: </label>
                <input name="password" type="password" minlength="4"/>

                <br/>

                <input type="submit" value="REGISTER"/>

           </form>
    </body>
</html>