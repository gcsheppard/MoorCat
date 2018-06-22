<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/MoorCat/styles/styles.css">
    </head>
    <body>
        <h1>The Moor Cat--everything for your cat!</h1>
        <h2>Order Management</h2>
        

        
        <div class="grid-container">
          <div class="item1">Order #</div>
          <div class="item1">First Name</div>
          <div class="item1">Last Name</div>
          <div class="item1">Status</div>  
          <div class="item1">Action</div>
          
          <c:forEach var="order" items="${orders}">
              <div class="item2"><c:out value = "${order.id}"/></div>
              <div class="item2"><c:out value = "${order.first_name}"/></div>
              <div class="item2"><c:out value = "${order.last_name}"/></div>  
              <div class="item2"><c:out value = "${order.status}"/></div>
              <div class="item2"><c:choose>
                        <c:when test = "${order.status == 'Placed'}">
                            <a href="/MoorCat/edit?order_id=${order.id}">Edit/Approve Order</a>
                        </c:when>
                        <c:when test = "${order.status == 'Approved'}">
                            <a href="/MoorCat/pick?order_id=${order.id}">Pick Order</a>
                        </c:when>
                        <c:when test = "${order.status == 'Picked'}">
                            <a href="/MoorCat/pack?order_id=${order.id}">Pack Order</a>
                        </c:when>
                        <c:when test = "${order.status == 'Packed'}">
                            <a href="/MoorCat/ship?order_id=${order.id}">Ship Order</a>
                        </c:when>
                        <c:when test = "${order.status == 'Shipped'}">
                            <a href="/MoorCat/archive?order_id=${order.id}">Archive Order</a>
                        </c:when>
                    </c:choose></div>
          </c:forEach>
        </div>
        
        <br><div class="d"><a href="/MoorCat/neworder">New Order</a></div><br>
        
        <c:choose>
            <c:when test = "${user == null}">
                <br><div class="d"><a href="/MoorCat/login">Login</a></div><br>
            </c:when>
            <c:otherwise>
                <br><div class="d"><a href="/MoorCat/logout">Logout</a></div><br>
            </c:otherwise>
        </c:choose>
    </body>
</html>
