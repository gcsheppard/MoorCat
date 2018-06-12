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
                
        <br>        
        
        <form action="edit" method="post">
            <input type="hidden" name="id" value="${order.id}" /><br>
            <div class="edit_grid">
                <div class="edit1">Order #: ${order.id}</div>
                <div class="edit2">First Name: <input type="text" name="first_name" value="${order.first_name}"></div>
                <div class="edit3">Last Name: <input type="text" size="40" name="last_name" value="${order.last_name}"></div>
            </div>  
            <br>
            <div class="edit_grid">
                <div class="edit4">Product #</div>
                <div class="edit4">Category</div>
                <div class="edit4">Supplier</div>
                <div class="edit4">Name</div>
                <div class="edit4">Ordered</div>
                <c:forEach var="orderItem" items="${orderItems}">
                    <div class="edit5"><c:out value = "${orderItem.product_id}"/></div>
                    <div class="edit5"><c:out value = "${orderItem.category}"/></div>
                    <div class="edit5"><c:out value = "${orderItem.supplier}"/></div>
                    <div class="edit5"><c:out value = "${orderItem.name}"/></div>
                    <div class="edit5"><input type="text" size="10" name="${orderItem.product_id}" value="${orderItem.quantity}"></div>
                </c:forEach>
                    
                <div class="edit7">Add Product:
                    <select name="new_product">
                        <option>Select Product</option>
                        <c:forEach var="product" items="${products}">
                            <option value="${product.product_id}">${product.name}</option>
                        </c:forEach>
                    </select>
                    Quantity: <input type="text" name="new_quantity" value="0">
                </div>    
                    
                <div class="edit6"><input type="submit" value="Save" /></div>
        </form>
                
                <div class="edit6">
                    <form action="approve" method="post">
                        <input type="hidden" name="id" value="${order.id}" />
                        <input type="submit" value="Approve" />
                    </form>
                </div>
            </div>        
            
        <br><br><div class="d"><a href="/MoorCat/orders">Return to order list</a></div><br>

     </body>
</html>
