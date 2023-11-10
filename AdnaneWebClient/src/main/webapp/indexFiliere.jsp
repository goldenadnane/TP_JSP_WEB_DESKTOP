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
	
	
	<a href="FiliereController?action=add">new Filiere</a>
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>ID</th>
			<th>Code</th>
			<th>Name</th>
			<th>Options</th>
		
	   </tr>
	
	<c:forEach var="filiere" items="${listFilieres }">
		<tr>
			<td>${filiere.id}</td>
			<td>${filiere.code }</td>
			<td>${filiere.name }</td>
			<td><a href="FiliereController?action=update&id=${filiere.id}">Update</a> 
			| <a href="FiliereController?action=delete&id=${filiere.id}" onclick="return confirm('Are you sure')">Delete</a></td>
			
	
		</tr>
		
	</c:forEach>
	
	
	</table>

	
</body>
</html>