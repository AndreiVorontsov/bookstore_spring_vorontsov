<html>
<head>
    <title>User</title>
    <link rel="stylesheet" type="text/css" href="css/button/button1.css">
</head>
<body>
    <div class="container-buttons">
        <button> <a href="/controller?command=home" class="button1">Home</a></button>
        <button> <a href="/controller?command=users" class="button1">Users</a></button>
        <button> <a href="/controller?command=books" class="button1">Books</a></button>
    </div>
    <h1>User</h1>
    <h3>${date}</h3>
    <p>ID: ${user.id}</p>
    <p>FIO: ${user.surName}${user.name} ${user.lastName}</p>
    <p>Login: ${user.email}</p> 
    <p>Password: ${user.password}</p>
    <p>Role: ${user.role}</p>
</body>
</html>