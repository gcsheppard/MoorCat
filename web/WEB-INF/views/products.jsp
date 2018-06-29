<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/MoorCat/styles/styles.css">
    </head>
    <body>
        <h1>The Moor Cat--everything for your cat!</h1>
        <h2>Product Management</h2>
        
        <div class="grid-container">
          <div class="item1">Product #</div>
          <div class="item1">Price</div>
          <div class="item1">Name</div>
          <div class="item1">Category</div>  
          <div class="item1">Supplier</div>
          
          <c:forEach var="product" items="${products}">
              <div class="item2"><c:out value = "${product.product_id}"/></div>
              <div class="item2"><c:out value = "${product.price}"/></div>
              <div class="item2"><c:out value = "${product.name}"/></div>  
              <div class="item2"><c:out value = "${product.category_name}"/></div>
              <div class="item2"><c:out value = "${product.supplier_name}"/></div>
          </c:forEach>
        </div>
        
    </body>
</html>
