<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/MoorCat/styles/styles.css">
    </head>
        <h1>The Moor Cat--everything for your cat!</h1>
     <body>
        <h2>Packing Order ${order.id} for ${order.first_name} ${order.last_name}</h2>
    
        <form action="packlist" method="post">
            <input type="hidden" name="order_id" value="${order.id}" />
            <br><div class="c"><input type="submit" value="Packing List" /><br>
        </form>

            
            <form action="pack" method="post">
            <input type="hidden" name="order_id" value="${order.id}" />
            <br><div class="c"><input type="submit" value="Packing Complete" /><br>
        </form>
            
        <br><br><div class="d"><a href="/MoorCat/orders">Return to order list</a></div><br>
     </body>
</html>
