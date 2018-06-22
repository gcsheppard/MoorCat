<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/MoorCat/styles/styles.css">
    </head>
        <h1>The Moor Cat--everything for your cat!</h1>
     <body>
        <h2>Ship Order:</h2>
        
        <br><br>
        
        <form action="ship" method="post">
            <input type="hidden" name="order_id" value="${order.id}" /><br>
            <div class="ship_grid">
                <div class="ship1"><c:out value = "Order #: ${order.id} for ${order.first_name} ${order.last_name}"/></div>
                <div class="ship2">    
                    <select name="ship_method">
                        <option>Select Ship Method:</option>
                        <c:forEach var="ship_method" items="${ship_methods}">
                            <option value="${ship_method.ship_method_id}">${ship_method.ship_method}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="ship3">Tracking Number: <input type="text" size="40" name="tracking"></div>
                <div class="ship1"><input type="submit" value="Ship"></div>
            </div>
        </form>  
            
        <br><br><div class="d">Notification email is sent automatically.</div><br>
        <br><br><div class="d"><a href="/MoorCat/orders">Return to order list</a></div><br>

    </body>
</html>
