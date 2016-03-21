<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
<title>Home</title>
<%@ include file="genericinclude.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="js/stdlib/jquery-1-9-1-min.js"></script>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
body {background-color:white;}
h2   {color:#2E9AFE;}
h3   {color:#2E9AFE;}

input[type=text], textarea {
  -webkit-transition: all 0.30s ease-in-out;
  -moz-transition: all 0.30s ease-in-out;
  -ms-transition: all 0.30s ease-in-out;
  -o-transition: all 0.30s ease-in-out;
  outline: none;
  padding: 3px 0px 3px 3px;
  margin: 5px 1px 3px 0px;
  border: 1px solid #DDDDDD;
}
 
input[type=text]:focus, textarea:focus {
  box-shadow: 0 0 5px rgba(81, 203, 238, 1);
  padding: 3px 0px 3px 3px;
  margin: 5px 1px 3px 0px;
  border: 1px solid rgba(81, 203, 238, 1);
}

select {
    padding:5px 15px; 
    background:#ccc;
   /*  background:#26A2E0; */
    border:0 none;
    cursor:pointer;
    -webkit-border-radius: 5px;
    border-radius: 5px; 
}
input[type=submit] {
    padding:5px 15px; 
    /* background:#ccc; */ 
    background:#8AB6CB;
    border:0 none;
    cursor:pointer;
    -webkit-border-radius: 5px;
    border-radius: 5px; 
}

.main-header {
  text-align: center;

   /* height: 10px;
  width: 28px;
  position: fixed;
  top: 0;
  left: 80%;
  margin-left: -499px; */
}

div {
    width: 100%;
    height: 10px;
    border: 3px solid #73AD21;
}
</style>
  <h5 align="left"> 
			<a href="showCustomer.jsp">View Contacts</a> |
		    <a href="sendEmail.jsp">Send Email</a> |
			<a href="register.jsp">Create Contact</a> |
			<a href="newUser.jsp">Create User</a> |
		    <a href="changePassword.jsp">Change Password</a> |
		    <a href="logout.jsp">Logout</a>
		</h5>
<header class="main-header" role="banner">
  <img src="images/user.png" alt="Banner Image" align="center"  height="15%" width="15%"/>
</header>

</head>
<body>
		<!-- <h1 align="center"><font color="#808000"><u>Create New Contact</u></font></h1> -->
		<h2 align="center"><u>User List</u></h2>

	<form name="homePage" action="" method="post">
		
		<table align="center" border="0">
		
			<c:forEach var="userList" items="${listOfUser}">
			  <tr>	
				<td>${userL.userId }</td>
				<td>${userList.firstName }</td>
				<td>${userList.lastName}</td>
				<td>${userList.userGender }</td>
				<td>${userList.roll }</td>
				<td>${userList.userMobile1 }</td>
				<td>${userList.userMobile2 }</td>
				<td>${userList.userEmail }</td>
				
				<td>  <a href="">Edit</a>  &nbsp;/ &nbsp;  
					  <a href=""><font color="red">Delete</font></a>   
			    </td>
		 	 </tr>		
		   </c:forEach>
							   
		</table>
	
	</form>

</body>
</html>