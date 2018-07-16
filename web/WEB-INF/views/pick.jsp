<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/MoorCat/styles/styles.css">
    </head>
        <h1>The Moor Cat--everything for your cat!</h1>
     <body>
        <h2>Pick Order #${order.id} For ${order.first_name} ${order.last_name}</h2>
        
        <form action="pick" method="post">
            <input type="hidden" name="order_id" value="${order.id}" />
            <div class="pick_grid">
                <div class="pick1">ID</div>
                <div class="pick1">Category</div>
                <div class="pick1">Supplier</div>
                <div class="pick1">Name</div>
                <div class="pick1">Ordered</div>
                <div class="pick1">Picked</div>
                <c:forEach var="orderItem" items="${orderItems}">
                    <div class="pick2"><c:out value = "${orderItem.product_id}"/></div>
                    <div class="pick2"><c:out value = "${orderItem.category}"/></div>
                    <div class="pick2"><c:out value = "${orderItem.supplier}"/></div>
                    <div class="pick2"><c:out value = "${orderItem.name}"/></div>
                    <div class="pick2"><c:out value = "${orderItem.quantity}"/></div>
                    <div class="pick2"><input type="text" size="10" name="${orderItem.product_id}" value="${orderItem.picked}"></div>
                </c:forEach>
                <div class="picksave"><input type="submit" value="Save" /></div>
            </div>
        </form>

        
        <br><br><div class="d"><a href="/MoorCat/orders">Return to order list</a></div><br>

    </body>
</html>
