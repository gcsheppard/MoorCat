<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/MoorCat/styles/styles.css">
    </head>
    <body>
        <h1>The Moor Cat--everything for your cat!</h1>
        <h1>Edit Product Information:</h1>
        
        <c:choose>
            <c:when test = "${show_errors == 'true'}">
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

        <form action="editproduct" method="post">
            <input type="hidden" name="product_id" value="${product.product_id}" />
            <div class="editproduct_grid">
                <div class="editproduct1a">Product #: ${product.product_id}</div>
                <div class="editproduct1b">Name: <input type="text" size="100" name="name" value="${product.name}"></div>
                <div class="editproduct2a">Price: <input type="text" size="10" name="price" value="${product.price}"></div>
                <div class="editproduct2b">Category: <input type="text" size="20" name="category_id" value="${product.category_id}"></div>
                <div class="editproduct2c">Supplier: <input type="text" size="20" name="supplier_id" value="${product.supplier_id}"></div>
                <div class="editproductsave"><input type="submit" value="Save" /></div>
            </div>        
        </form>

        
        <br><div class="d"><a href="/MoorCat/products">Return to product list</a></div><br>

    </body>
</html>
