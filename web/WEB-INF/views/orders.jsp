<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/MoorCat/styles/styles.css">
    </head>
    <body>
        <h1>The MoorCat--everything for your cat!</h1>
        <h2>Order Management</h2>
        
        <table id="customers">
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Status</th>
                <th>Edit</th>
            </tr>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td><c:out value = "${order.first_name}"/></td>
                    <td><c:out value = "${order.last_name}"/></td>
                    <td><c:out value = "${order.status}"/></td>
                    <td><a href="/MoorCat/edit?id=${order.id}">Edit Order</a></td>
                </tr>
            </c:forEach>
        </table>
        <br><br><div class="d"><a href="/MoorCat/new">Add New Order</a></div><br>
    </body>
</html>
