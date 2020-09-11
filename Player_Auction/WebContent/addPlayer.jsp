<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@page import="com.mindtree.playerauctionweb.JDBCImpl.PlayerAuctionJDBCImpl"%>
<%@page import="com.mindtree.playerauctionweb.entity.Player" %>
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
	background-color: rgba(0, 0, 0, 0.1);
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
	background-color: rgba(0, 0, 0, 0.1);
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

input[type=reset] {
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

input[type=reset]:hover {
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
.bgimg {
	background-image: url('ipl-2018.jpg');
	background-position: center;
	background-repeat: no-repeat;
	margin-left: 25%;
	padding: 1px 16px;
	height: 600px;
	border-radius: 5px;
}

.form {
	position: relative;
	z-index: 1;
	background-color: rgba(0, 0, 0, 0.3);
	padding: 1px 16px;
	height: 600px;
	border-radius: 5px;
}

label {
	font-weight: bold;
}
table,tr,td{
    border: 1px solid black;
}
.details{
	font-weight: bold;
	margin-left: 25%;
	padding-top: 10px;
}
.floating-left{
 		float: left;
}

.floating-right{
	float:right;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>add_a_player</title>
<script type="text/javascript" src="validateaddplayer.js"></script>
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
		<h1>Add a player</h1>
	</div>

	<div class=bgimg>
		<div class=form>
			<form name="addplayerServlet" method="post" action="Addservlet"
				onsubmit="return addplayer()">
				<label>Player Name </label> <input type="text"
					placeholder="Player Name" name="playername" id="pname"> <label>Category</label>
				<select name=category id="category">
					<option>Batsman</option>
					<option>Bowler</option>
					<option>All-Rounder</option>
				</select> <label>High Score</label> <input type="number"
					placeholder="Highest Score" name="highscore" id="hscore"> <label>Best
					Figure</label> <input type="text" placeholder="Best Figure"
					name="bestfigure" id="bfigure"> <label>Team</label> 
					<select name=team id="team">
					<%  
						ResultSet teams= (ResultSet) request.getAttribute("TeamList");
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
					<%}}%>
				</select> 
				<div class="floating-left"><input type="submit" name="submit" value="Submit"></div>
                <div class="floating-right"><input type="reset" name="clear" value="Clear"> </div>
			</form>
		</div>
	</div>
	<div>
		<%
			String message = (String) request.getAttribute("Player");
			if (message == null) {
				out.println("<br>");
			} else {
		%>
		<script>var msg="<%=message%>";
			alert(msg);
		</script>
		<%
			}
		%>

	</div>
	<div class="details">
	<table>
	<%ArrayList<Player> details = (ArrayList<Player>)request.getAttribute("Allplayer");
				   if (details == null){
						out.println("<br>");
						
				   }
				   else
				   {
					   
		%>
				   <th style="width: 200px">Player Name</th>
				   <th style="width: 200px">Category</th>
				   <th style="width: 200px">Highscore</th>
				   <th style="width: 208px">Bestfigure</th>
				   </table>
				   
	<table>
	
		<% for(Player det: details)
		{ 
		%>
			<tr><td style="width: 200px"><%out.print(det.getPlayername()); %></td>
			<td style="width: 200px"><%out.print(det.getCategory()); %></td>
			<td style="width: 200px"><%out.print(det.getHighscore()); %></td>
			<td style="width: 200px"><%out.print(det.getBestfigure()); %></td>
			</tr>
		<%} }%>

	</table>
	</div>
</body>
</html>