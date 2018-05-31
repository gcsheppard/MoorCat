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
                <th>Action</th>
            </tr>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td><c:out value = "${order.first_name}"/></td>
                    <td><c:out value = "${order.last_name}"/></td>
                    <td><c:out value = "${order.status}"/></td>
                    <c:choose>
                        <c:when test = "${order.status == 'Placed'}">
                            <td><a href="/MoorCat/edit?id=${order.id}">Edit/Approve Order</a></td>
                        </c:when>
                        <c:when test = "${order.status == 'Approved'}">
                            <td><a href="/MoorCat/pick?id=${order.id}">Pick Order</a></td>
                        </c:when>
                        <c:when test = "${order.status == 'Picked'}">
                            <td><a href="/MoorCat/pack?id=${order.id}">Pack Order</a></td>
                        </c:when>
                    </c:choose>
                    
                </tr>
            </c:forEach>
        </table>
        <br><br><div class="d"><a href="/MoorCat/new">Add New Order</a></div><br>
    </body>
</html>
