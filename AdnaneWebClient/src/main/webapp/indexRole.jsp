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
	
	
	<a href="RoleController?action=add">new role</a>
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Options</th>
		
	   </tr>
	
	<c:forEach var="role" items="${listRoles }">
		<tr>
			<td>${role.id}</td>
			<td>${role.name }</td>
			
			<td><a href="RoleController?action=update&id=${role.id}">Update</a> 
			| <a href="RoleController?action=delete&id=${role.id}" onclick="return confirm('Are you sure')">Delete</a></td>
			
	
		</tr>
		
	</c:forEach>
	
	
	</table>

	
</body>
</html>