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


	<form method="post" action="StudentController?action=update">
		<table cellpadding="2" cellspacing="2">
		
		
			<tr>
				<td>ID</td>
				<td>${student.id }</td>
			</tr>
		
			<tr>
				<td>Login</td>
				<td><input type="text" name="login" value="${student.login }"></td>
			</tr>
			
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" value="${student.password}"></td>
			</tr>
			<tr>
				<td>Firstname</td>
				<td><input type="text" name="firstname" value="${student.firstname}"></td>
			</tr>
			
			<tr>
				<td>Lastname</td>
				<td><input type="text" name="lastname" value="${student.lastname}" ></td>
			</tr>
			
			<tr>
				<td>Telephone</td>
				<td><input type="text" name="telephone" value="${student.telephone}"></td>
			</tr>
			
			
			
					
			<tr>
    <td>Filiere : ${student.filiere.id }</td>
    
    <td>
        		<select name="filiereId">
    			<c:forEach var="filiere" items="${listFilieres}">
        		<option value="${filiere}">${filiere.id}</option>
    			</c:forEach>
				</select>
    </td>
</tr>
			
			<tr>
			
			
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Update"></td>
			</tr>
			
			
	
	
	
	
	</table>
	</form>
</body>
</html>