<%@page import="java.sql.*"%>
<%@page
	import="com.mindtree.playerauctionweb.JDBCImpl.PlayerAuctionJDBCImpl"%>
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

div {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
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
  .player {
	font-weight: bold;
	margin-left: 25%;
	padding-top: 10px;
}
label {
	font-weight: bold;
}
table,tr,td{
    border: 1px solid black;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>display_team</title>
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
		<h1>Display Players</h1>
	</div>
	<div
		style="margin-left: 25%; padding: 1px 16px; height: 200px; border-radius: 5px; background-color: #f2f2f2;">
		<form name="displayplayerServlet" method="post"
			action="Displayservlet">
			<label>Team</label> <select name=team id="team">
				<%
					
					ResultSet teams = (ResultSet) request.getAttribute("TeamList");
					if (teams == null) {
						out.println("<br>");
					} else {
						while (teams.next()) {
				%>
				<option>
					<%
						out.print(teams.getString(1));
					%>
				</option>
				<%
					}
					}
				%>
			</select> <input type="submit" name="submitdisp" value="Submit">
		</form>
	</div>
	<div class=player>
			
		<%
			ResultSet players = (ResultSet) request.getAttribute("Disp_player");
			if (players == null)
				out.println("<br>");
			else {
				if (!players.next()) {
		%>
		<script>
			var msg = "No player added to this team yet.";
			alert(msg);
		</script>
		<%
			} else {%>
				<table>
				   <th style="width: 200px">Player Name</th>
				   <th style="width: 200px">Category</th>
				   <th style="width: 200px">Highscore</th>
				   <th style="width: 208px">Bestfigure</th>
				   </table>
					<% do {%>
		   <table>
		   
			<tr>
				<td style="width: 200px">
					<%
						out.print(players.getString(1));
					%>
				</td>
				<td style="width: 200px">
					<%
						out.print(players.getString(2));
					%>
				</td>
				<td style="width: 200px">
					<%
						out.print(players.getInt(3));
					%>
				
				<td style="width: 200px">
					<%
						out.print(players.getString(4));
					%>
				</td>
			</tr>
		</table>
		<%
			} while (players.next());
				}
			}
		%>

	</div>
</body>
</html>