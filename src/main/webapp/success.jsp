<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" data-theme="light">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Registration Success</title>
  <head>
<link href="" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Register Page</title>

</head>

<body>
  <c:if test="${not empty success and success eq 'true'}">
    	<div class="toast toast-end">
  			<div class="alert alert-error">
    			<span>${message}</span>
  			</div>
		</div>
	</c:if>
</body>

</html>
    