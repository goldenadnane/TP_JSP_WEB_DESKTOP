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


	<form method="post" action="StudentController?action=add">
		<table cellpadding="2" cellspacing="2">
		
		
			<tr>
				<td>Login</td>
				<td><input type="text" name="login"></td>
			</tr>
			
			<tr>
				<td>Password</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>Firstname</td>
				<td><input type="text" name="firstname"></td>
			</tr>
			
			<tr>
				<td>Lastname</td>
				<td><input type="text" name="lastname"></td>
			</tr>
			
			<tr>
				<td>Telephone</td>
				<td><input type="text" name="telephone"></td>
			</tr>
			
			
			<tr>
    			<td>Filiere</td>
    			<td>
        		<select name="filiereId">
    			<c:forEach var="filiere" items="${listFilieres}">
        		<option value="${filiere}">${filiere}</option>
    			</c:forEach>
				</select>
    			</td>
				</tr>
			
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Save"></td>
			</tr>
			
			
	
	
	
	
	</table>
	</form>
</body>
</html>