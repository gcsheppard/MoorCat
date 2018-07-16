<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/MoorCat/styles/styles.css">
    </head>
    <body>
        <h1>Moor Cat Administration</h1>
        <h1><c:out value = "${flash}"/></h1>
        
        <h2>Please Login:</h2>
        <form action="login" method="post">
            <div class="login_grid">
                <div class="login1"><input type="text" name="username" placeholder="Username"}></div><br>
                <div class="login1"><input type="password" name="password" placeholder="Password"></div><br>
                <div class="login1"><input type="submit" value="Login" /><br>
            </div>
        </form>  
    </body>
</html>