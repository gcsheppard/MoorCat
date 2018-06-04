<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/MoorCat/styles/styles.css">
    </head>
        <h1>The MoorCat--everything for your cat!</h1>
     <body>
        <h2>Packing List For: ${order.first_name} ${order.last_name}</h2>
    
     <form action="pack" method="post">
            <input type="hidden" name="order_id" value="${order.id}" />
        <table border = "1">
            <tr>
                <th>ID</th>
                <th>Category</th>
                <th>Supplier</th>
                <th>Name</th>
                <th>Ordered</th>
                <th>Picked</th>
            </tr>
            <c:forEach var="orderItem" items="${orderItems}">
                <tr>
                    <td align="center"><c:out value = "${orderItem.product_id}"/></td>
                    <td><c:out value = "${orderItem.category}"/></td>
                    <td><c:out value = "${orderItem.supplier}"/></td>
                    <td><c:out value = "${orderItem.name}"/></td>
                    <td align="center"><c:out value = "${orderItem.quantity}"/></td>
                    <td align="center"><c:out value = "${orderItem.picked}"/></td>
                </tr>
            </c:forEach>
        </table>
        <br><div class="c"><input type="submit" value="Pack Complete" /><br>
        </form>
        <br><br><div class="d"><a href="/MoorCat/orders">Return to order list</a></div><br>
     </body>
</html>
