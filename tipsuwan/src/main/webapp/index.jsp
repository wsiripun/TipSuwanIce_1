<!DOCTYPE html>
<html>
<head>
	<title>login</title>
	<meta charset="utf-8">
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script 
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
</head>
<body>

<h2>Hello World!</h2>
<div id="userInfo" style="display: none"><%= request.getUserPrincipal().getName() %></div>
<div id="toPrint"></div>

<% if (request.isUserInRole("Sale")) { %>
	<button type="button" class="btn btn-warning" onclick="getEmployeeDataByID('<%= request.getUserPrincipal().getName() %>')" >GET REPORT</button>
<% } %>

<script>

function getEmployeeDataByID(toSend) {
	
	
	$.post("/tipsuwan/tutorial/getEmployeeData", 
		{
			postName: toSend
		},
		function(data, status){
			// 'data' Example:  { employeeList: [ {...}, {...} ]  }
			console.log(data);		// send the value of 'orderList'  	
			displayData(data.employeeList);
		}
	);	
}

function displayData(data) {
	document.getElementById("toPrint").innerHTML = "Last Name: " + data[0].lastName;
}

$(document).ready(function() {
	var x = document.getElementById("userInfo").innerHTML;
	
	getEmployeeDataByID(x);
	
});


</script>
</body>
</html>
