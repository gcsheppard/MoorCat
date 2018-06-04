<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/MoorCat/styles/styles.css">
    </head>
        <h1>The MoorCat--everything for your cat!</h1>
     <body>
        <h2>Ship Order For: ${order.first_name} ${order.last_name}</h2>
        <div class="b">(Notification email should be sent automatically.)</div><br>
        <form action="ship" method="post">
            <input type="hidden" name="order_id" value="${order.id}" />

            <div class="c"><input type="submit" value="Ship" /><br>
        </form>
        <br><br><div class="d"><a href="/MoorCat/orders">Return to order list</a></div><br>

    </body>
</html>
