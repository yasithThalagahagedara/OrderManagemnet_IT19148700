package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/orderAPI")
public class OrderAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	



protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	OrderController orderObj = new OrderController();
	String output = orderObj.insertOrder(request.getParameter("prname"), 
			 request.getParameter("price"), 
			request.getParameter("quantity"), 
			request.getParameter("email"),
	        request.getParameter("paymentM")); 
			response.getWriter().write(output);
}

/**
* @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
*/

// Convert request parameters to a Map
private static Map getParasMap(HttpServletRequest request) 
{ 
Map<String, String> map = new HashMap<String, String>(); 
try
{ 
Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
String queryString = scanner.hasNext() ? 
scanner.useDelimiter("\\A").next() : ""; 
scanner.close(); 
String[] params = queryString.split("&"); 
for (String param : params) 
{
	 String[] p = param.split("=");
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}

	 
	 
protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	OrderController orderObj = new OrderController();
	Map paras = getParasMap(request); 
	 String output = orderObj.updateOrders(paras.get("hidItemIDSave").toString(), 
	 paras.get("prname").toString(), 
	 paras.get("price").toString(), 
	paras.get("quantity").toString(), 
	paras.get("email").toString(), 
	 paras.get("paymentM").toString()); 
	response.getWriter().write(output);
	
}

/**
* @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
*/
protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	OrderController orderObj = new OrderController();
	Map paras = getParasMap(request); 
	 String output = orderObj.deleteOrder(paras.get("id").toString()); 
	response.getWriter().write(output);
}













}
