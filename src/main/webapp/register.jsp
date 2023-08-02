<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html data-theme="light">
<head>
<link href="" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Register Page</title>
<script src="https://cdn.tailwindcss.com"></script>
<link href="https://cdn.jsdelivr.net/npm/daisyui@3.5.0/dist/full.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="">

	<c:if test="${not empty hasError and hasError eq 'true'}">
    	<div class="toast toast-end">
  			<div class="alert alert-error">
    			<span>${errorMessage}</span>
  			</div>
		</div>
	</c:if>
	
	<div class="card w-96 bg-base-100 shadow-xl m-auto my-48 bg-zinc-300">
    <div class="card-body items-center text-center">
    <h2 class="text-4xl font-bold">Register</h2>
      <form action="register" method="post" class="">
		<br> Username <input type="text" name="username" class="w-full px-4 py-2 border rounded-lg shadow-xl"/>
		<br> Password <input type="password" name="password" class="w-full px-4 py-2 border rounded-lg shadow-xl"/>		
		<button type="submit" class="btn m-4 btn-primary hover:text-white text-black">Submit</button>
	  </form>
  </div>
</div>
</body>
</html>