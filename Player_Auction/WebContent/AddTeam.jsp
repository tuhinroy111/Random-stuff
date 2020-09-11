<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>


input[type=text], select {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	background: transparent;
	background-color: rgba(0,0,0,0.1);
}

input[type=number] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	background: transparent;
	background-color: rgba(0,0,0,0.2);
}

input[type=submit] {
	width: 100%;
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=submit]:hover {
	background-color: #45a049;
}

ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	width: 24%;
	background-color: #24337C;
	position: fixed;
	height: 100%;
	overflow: auto;
	position: fixed;
	border-radius: 5px;
	font-family: Comic Sans MS;
}

li a {
	display: block;
	color: #000;
	padding-top: 50px;
	padding-left: 20px;
	padding-bottom: 50px;
	text-decoration: none;
	text-align: left;
	color: #fff;
	font-weight: bold;
	font-size: 20px
}

li a.active {
	background-color: #000;
	color: white;
}

li a:hover:not(.active) {
    background-color: #555;
    color: white;
}
.bgimg{
	background-image: url('ipl-2018.jpg');
	background-position: center;
	background-repeat: no-repeat;
	margin-left: 25%; 
	padding: 1px 16px; 
	height: 600px; 
	border-radius: 5px;
}
.form{
	position: relative;
	z-index: 1;
	background-color: rgba(0,0,0,0.3);
	padding: 1px 16px;
	height: 1000px;
	border-radius: 5px;
	
}
label{
	font-weight: bold;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>add_a_player</title>
<script type="text/javascript" src="addTeam.js"></script>
</head>
<body>

	<ul>
		<li><a href="PlayerAuctionHome.html">Home</a></li>
		<li><a href="Addservlet">Add a Player</a></li>
		<li><a href="Displayservlet">Display Players</a></li>
		<li><a href="AddTeam.jsp">Add Team</a></li>
		<li><a href="Exit.html">Logout</a></li>
		
	</ul>

	<div
		style="margin-left: 25%; padding: 1px 16px; border-radius: 5px; background-color: #f2f2f2;">
		<h1>Add a Team</h1>
	</div>
	
	<div class=bgimg>
		<div class=form>
		<form name="addTeamServlet" method="post" action="AddTeam"
			onsubmit="return addTeam()">

			<label>Team Name</label> <input type="text"
				placeholder="Team Name" name="teamname" id="tname"> 
			 <input type="submit" name="submit" value="Submit">
		</form>
	</div>
	</div>
	<div>
				<%
					String message=(String) request.getAttribute("Team");
					if(message== null)
					{
						out.println("<br>");
					}
					else
					{%>
						<script>var msg="<%=message%>";alert(msg);</script>
					<%}%>
				
				</div>

</body>
</html>