<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Base64"%>
<%@ page import="java.sql.*, java.io.*"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welocme To Store</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">

			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="index.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="signup.jsp">Signup</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a>
					</li>
					<li class="nav-item"><a class="nav-link" aria-disabled="true" href="aboutus.jsp">About
							us</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<h4 Style="padding:10px;">To Explore Please<a href="login.jsp">Login</a> </h4>
<div class="container">
        <div class="row">
            <%
Connection con;
Statement stmt;
ResultSet rs;

try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	System.out.println("loaded");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","Manu2341");
	System.out.println("connected");
	stmt=con.createStatement();
	rs=stmt.executeQuery("SELECT * FROM login.employees inner join login.books where employees.id=books.id");
	int count=0;
	while(rs.next()){
	Blob blob = rs.getBlob("image");
			byte[] imageBytes = blob.getBytes(1, (int) blob.length());
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	%>
		      <div class="col-md-3 col-sm-5 mb-4">
                <div class="card h-100" style="padding:30px; padding-left:40px;">
                    <img src="data:image/jpeg;base64,<%= base64Image %>" class="card-img-top" alt="Book Image" style="height: 140px;">
                    <div class="card-body">
                        <span class="card-title"><%= rs.getString(8) %></span>
                    </div>
                    <div class="list-group list-group-flush">
                        <b>Author Of Book: <%= rs.getString(9) %></b><br>
                        <b>Published Year: <%= rs.getString(10) %></b>
                    </div>
                </div>
            </div>
	<%}
}catch(Exception e){
	e.printStackTrace();
}
%>
</div>
</div>

</body>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
	integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
	crossorigin="anonymous"></script>
</html>