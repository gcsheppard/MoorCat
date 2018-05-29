<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/MoorCat/styles/styles.css">
    </head>
        <h1>The MoorCat--everything for your cat!</h1>
     <body>
        <h2>Edit Order Information:</h2>
        <form action="edit" method="post">
            <input type="hidden" name="id" value="${order.id}" /><br>
            <div class="b">First Name: <input type="text" name="first_name" value="${order.first_name}" placeholder="First Name"></div><br>
            <div class="b">Last Name: <input type="text" name="last_name" value="${order.last_name}" placeholder="Last Name"></div><br>
            <div class="b">Status: <input type="text" name="status" value="${order.status}" placeholder="Status"></div><br>
            <div class="c"><input type="submit" value="Save" /><br>
        </form>  

        <c:choose>
            <c:when test = "${errors != null}">
                <br><br><div class="e">Errors:</div>
                <ul>
                <c:forEach var="error" items="${errors.values()}">
                    <li>
                        <div class="e">${error}</div>
                    </li>
                </c:forEach>
                </ul>
            </c:when>
        </c:choose>        
        <hr>        
        <h2>Approve Order:</h2>
        <form action="approve" method="post">
            <input type="hidden" name="id" value="${order.id}" /><br>
            <div class="b">Current Status: ${order.status}</div><br>
            <div class="c"><input type="submit" value="Approve" /><br>
        </form>  
                
                
        <br><br><div class="d"><a href="/MoorCat/orders">Return to order list</a></div><br>

     </body>
</html>
