<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	<a href="StudentController?action=add">new student</a>
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>ID</th>
			<th>Login</th>
			<th>Password</th>
			<th>Firstname</th>
			<th>Lastname</th>
			<th>Telephone</th>
			<th>Options</th>
		
	   </tr>
	
	<c:forEach var="student" items="${listStudents }">
		<tr>
			<td>${student.id}</td>
			<td>${student.login }</td>
			<td>${student.password }</td>
			<td>${student.firstname }</td>
			<td>${student.lastname }</td>
			<td>${student.telephone }</td>
			<td>${student.filiere.id}</td>
			<td><a href="StudentController?action=update&id=${student.id}">Update</a> 
			| <a href="StudentController?action=delete&id=${student.id}" onclick="return confirm('Are you sure')">Delete</a></td>
			
	
		</tr>
		
	</c:forEach>
	
	
	</table>

	
</body>
</html>