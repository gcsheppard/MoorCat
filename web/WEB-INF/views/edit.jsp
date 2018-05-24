<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/BikeWay/styles/styles.css">
    </head>
        <h1>The MoorCat--everything for your cat!</h1>
        <h2>Edit Order Information:</h2>
     <body>
        <form action="edit" method="post">
            <input type="hidden" name="id" value="${order.id}" /><br>
            <div class="b">First Name: <input type="text" name="first_name" value="${order.first_name}" placeholder="Manufacturer"></div><br>
            <div class="b">Last Name: <input type="text" name="name" value="${bike.name}" placeholder="Name"></div><br>
            <div class="b">Status: <input type="text" name="type" value="${bike.type}" placeholder="Type"></div><br>
            <div class="c"><input type="submit" value="Save Changes" /><br>
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
                
        <br><br><div class="d"><a href="/BikeWay/bikes">Return to list</a></div><br>

     </body>
</html>
