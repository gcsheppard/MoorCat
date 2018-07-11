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
        
        <div class="products-grid">
          <div class="products1">Product #</div>
          <div class="products1">Price</div>
          <div class="products1">Name</div>
          <div class="products1">Category</div>  
          <div class="products1">Supplier</div>
          <div class="products1">Edit</div>
          
          <c:forEach var="product" items="${products}">
              <div class="products2"><c:out value = "${product.product_id}"/></div>
              <div class="products2"><c:out value = "${product.price}"/></div>
              <div class="products2"><c:out value = "${product.name}"/></div>  
              <div class="products2"><c:out value = "${product.category_name}"/></div>
              <div class="products2"><c:out value = "${product.supplier_name}"/></div>
              <div class="products2"><a href="/MoorCat/editproduct?product_id=${product.product_id}">Edit Product</a></div>
          </c:forEach>
        </div>
        
    <br><br><div class="d"><a href="/MoorCat/orders">Return to order list</a></div><br>

    </body>
</html>
