$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 


// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateItemForm(); 

if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
$.ajax( 
{ 
url : "orderAPI", 
type : type, 
data : $("#formItem").serialize(), 
dataType : "text", 
complete : function(response, status) 
{ 
onItemSaveComplete(response.responseText, status); 
} 
}); 
}); 
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
	$("#hidItemIDSave").val($(this).data("id")); 
 $("#prname").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#price").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#quantity").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#email").val($(this).closest("tr").find('td:eq(4)').text()); 
 $("#paymentM").val($(this).closest("tr").find('td:eq(5)').text());
});


$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "orderAPI", 
		 type : "DELETE", 
		 data : "id=" + $(this).data("id"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onItemDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});


//CLIENT-MODEL================================================================
function validateItemForm() 
{ 
// CODE
if ($("#prname").val().trim() == "") 
 { 
 return "Insert Product Name."; 
 } 
// NAME
if ($("#price").val().trim() == "") 
 { 
 return "Insert Price."; 
 } 
var tmpPrice = $("#price").val().trim(); 
if (!$.isNumeric(tmpPrice)) 
 { 
 return "Insert a numerical value for Product Price."; 
 } 
// convert to decimal price
 $("#price").val(parseFloat(tmpPrice).toFixed(2)); 
//PRICE-------------------------------
if ($("#quantity").val().trim() == "") 
 { 
 return "Insert Procuct Quantity."; 
 } 
if ($("#email").val().trim() == "") 
{ 
return "Insert Email."; 
}
if ($("#paymentM").val().trim() == "") 
{ 
return "Insert Payment Method."; 
}
return true; 
}

function onItemSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
$("#hidItemIDSave").val(""); 
$("#formItem")[0].reset(); 
}


function onItemDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}
