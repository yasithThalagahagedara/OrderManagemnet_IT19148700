package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;





public class OrderController {
	
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafp", "root", ""); 
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
		return con; 
	} 
	
	
	public String getOrder() 
	{ 
		 String output = ""; 
		 
		 try
		 { 
			 Connection con = connect(); 
		 
			 if (con == null) 
			 {return "Error while connecting to the database for reading."; } 
		 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Produt ID</th><th>Product Name</th><th>Price</th><th>Quantity</th><th>Email</th><th>Payment</th></tr>"; 
		 
			 String query = "select * from order_db"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
		 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
				 int id = rs.getInt("id");
				 String Prname = rs.getString("Prname");
				 float price = rs.getFloat("price"); 
				 int quantity = rs.getInt("quantity");
				 String email = rs.getString("email");
				 String paymentM = rs.getString("paymentM");
				 // Add into the html table
				 output += "<tr><td>" + id + "</td>"; 
				 output += "<td>" + Prname + "</td>"; 
				 output += "<td>" + price + "</td>"; 
				 output += "<td>" + quantity + "</td>"; 
				 output += "<td>" + email + "</td>"; 
				 output += "<td>" + paymentM + "</td>"; 
				 // buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' "
							+ "class='btnUpdate btn btn-secondary' data-id='" + id + "'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' "
							+ "class='btnRemove btn btn-danger' data-id='" + id + "'></td></tr>"; 
		 } 
		 
		con.close(); 
		 
		// Complete the html table
		 output += "</table>"; 
	} 
	catch (Exception e) 
	{ 
		 output = "Error while reading the items."; 
		 System.err.println(e.getMessage()); 
	} 
		 return output; 
}
	
	
	public String insertOrder(String Prname,String price,String quantity,String email,String paymentM) 
	{ 
		String output = ""; 
	
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 
 
			// create a prepared statement
			String query = " insert into pafp.order_db (`id`,`Prname`,`price`,`quantity`,`email`,`paymentM`)"
					+ " values (?,?,?,?,?,?)"; 
 
			PreparedStatement preparedStmt = con.prepareStatement(query); 

			// binding values
			
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2,Prname); 
			preparedStmt.setFloat(3, Float.parseFloat(price));
			preparedStmt.setInt(4, Integer.parseInt(quantity));
			preparedStmt.setString(5,email);
			preparedStmt.setString(6, paymentM);
			// execute the statement
			preparedStmt.execute(); 
			 con.close();
			 String neworder = getOrder(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 neworder + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 }
/*	{
			 
			 output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			 output = "Error while inserting the item."; 
			 System.err.println(e.getMessage()); 
		} 
		{
			 
		return output; 
	}
	*/
	public String updateOrders(String id, String Prname, String price, String quantity, String email, String paymentM)
	{ 
		 String output = ""; 
		 try
		 { 
			 	Connection con = connect(); 
		 
			 	if (con == null) 
			 	{return "Error while connecting to the database for updating."; } 
		 
			 	// create a prepared statement
			 	String query = "UPDATE order_db SET  Prname=?,price=?,quantity=?,email=?,paymentM=? WHERE id =?"; 
		 
			 	PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
			 	// binding values
			 	preparedStmt.setString(1, Prname); 
			 	preparedStmt.setFloat(2, Float.parseFloat(price)); 
			 	preparedStmt.setInt(3,Integer.parseInt(quantity)); 
			 	preparedStmt.setString(4, email); 
			 	preparedStmt.setString(5, paymentM); 
			 	preparedStmt.setInt(6,Integer.parseInt(id)); 
			
			 	// execute the statement
			 	preparedStmt.execute(); 
			 	con.close(); 
			 	 String neworder = getOrder(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
				 neworder + "\"}"; 
				 } 
				 catch (Exception e) 
				 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
				 System.err.println(e.getMessage()); 
				 } 
		  		{
				 return output; 
				 }
	}
		  		/*
			 	output = "Updated successfully"; 
	}
		 catch (Exception e)
		 { 
			 	output = "Error while updating the item."; 
			 	System.err.println(e.getMessage()); 
		 } 
		 
		 {
		 return output; 
		 
		 }*/
	
	public String deleteOrder(String id) 
	 { 
		String output = ""; 
	 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for deleting."; } 
	 
			// create a prepared statement
			String query = "delete from order_db where id=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			
	 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id)); 
	 
			// execute the statement
			preparedStmt.execute(); 
			con.close();
			 String neworder = getOrder(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 neworder + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
	 
		/*	output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while deleting the item."; 
		 System.err.println(e.getMessage()); 
	 }
	 
	 {
	 return output;
	 
	 } */
	 }

}
