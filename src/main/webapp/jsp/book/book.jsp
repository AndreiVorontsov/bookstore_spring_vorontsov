<html>
<head>
    <title>Book</title>
    <link rel="stylesheet" type="text/css" href="css/button/button1.css"> 
</head>
<body>
    <div class="container-buttons">
        <button> <a href="/controller?command=home" class="button1">Home</a> </button>
        <button> <a href="/controller?command=books" class="button1">Books</a> </button>
        <button> <a href="/controller?command=users" class="button1">Users</a> </button>
    </div>
    <h1>Book</h1>
    <h3>${date}</h3>
    <p>ID: ${book.id}</p>
    <p>Title: ${book.name}</p>
    <p>Author: ${book.author}</p> 
    <p>ISBN: ${book.isbn}</p>
    <p>Cover: ${book.cover}</p>
    <p>Price: ${book.price}$</p>
    <p>Publication year: ${book.yearPublication}</p>
    <p>Delete: ${book.delete}</p>
</body>
</html>