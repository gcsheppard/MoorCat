<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/MoorCat/styles/styles.css">
    </head>
    <body>
        <h1>The Moor Cat--everything for your cat!</h1>
        <h2>Edit Product Information:</h2>
        
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

                <div class="editproduct2b">Category:
                    <select name="category_id">
                        <c:forEach var="category" items="${categories}">
                            <c:choose>
                                <c:when test = "${category.category_id == product.category_id}">
                                    <option value="${category.category_id}" selected>${category.category_name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${category.category_id}">${category.category_name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>    

                <div class="editproduct2c">Supplier:
                    <select name="supplier_id">
                        <c:forEach var="supplier" items="${suppliers}">
                            <c:choose>
                                <c:when test = "${supplier.supplier_id == product.supplier_id}">
                                    <option value="${supplier.supplier_id}" selected>${supplier.supplier_name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${supplier.supplier_id}">${supplier.supplier_name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>    
                
                <div class="editproductsave"><input type="submit" value="Save" /></div>
            </div>        
        </form>

        
        <br><div class="d"><a href="/MoorCat/products">Return to product list</a></div><br>

    </body>
</html>
