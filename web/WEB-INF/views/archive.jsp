<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/MoorCat/styles/styles.css">
    </head>
        <h1>The MoorCat--everything for your cat!</h1>
     <body>
        <h2>Archive Order For: ${order.first_name} ${order.last_name}</h2>
        <div class="b">(Order will no longer be included on default list.)</div><br>
        <form action="archive" method="post">
            <input type="hidden" name="order_id" value="${order.id}" />

            <div class="c"><input type="submit" value="Archive" /><br>
        </form>
        <br><br><div class="d"><a href="/MoorCat/orders">Return to order list</a></div><br>

    </body>
</html>
