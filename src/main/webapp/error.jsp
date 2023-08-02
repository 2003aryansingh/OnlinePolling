<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error !!</title>
</head>
<body>
	<c:if test="${not empty hasError and hasError eq 'true'}">
    	<div class="toast toast-end">
  			<div class="alert alert-error">
    			<span>${errorMessage}</span>
  			</div>
		</div>
	</c:if>
</body>
</html>