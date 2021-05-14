<%@page import="com.OrderController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/order.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Order Management V10.1</h1>
<form id="formItem" name="formItem">
 Product name: 
 <input id="prname" name="prname" type="text" 
 class="form-control form-control-sm">
 <br> Price: 
 <input id="price" name="price" type="text" 
 class="form-control form-control-sm">
 <br> Quantity: 
 <input id="quantity" name="quantity" type="text" 
 class="form-control form-control-sm">
 <br> Email: 
 <input id="email" name="email" type="text" 
 class="form-control form-control-sm">
 <br>
  <br> payment Method: 
 <input id="paymentM" name="paymentM" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
	<%
 		OrderController orderObj = new OrderController(); 
 		out.print(orderObj.getOrder()); 
 	%>
</div>
</div> </div> </div> 
</body>
</html>