<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html data-theme = "light">
<head>
<meta charset="ISO-8859-1">
<title>Poll</title>
<script src="https://cdn.tailwindcss.com"></script>
<link href="https://cdn.jsdelivr.net/npm/daisyui@3.5.0/dist/full.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
    <h2 class="text-4xl text-center my-8">Poll Question:</h2>
    <p class="text-xl my-8 text-center">${question}</p>
    
    <c:if test="${not empty hasError and hasError eq 'true'}">
    	<div class="toast toast-end">
  			<div class="alert alert-error">
    			<span>${errorMessage}</span>
  			</div>
		</div>
	</c:if>
	
	<c:if test="${not empty success and success eq 'true'}">
    	<div class="toast toast-end">
  			<div class="alert alert-error">
    			<span>${message}</span>
  			</div>
		</div>
	</c:if>
    
 <div class="flex justify-center items-center">
    <form action="submitpoll" method="post">
        <h2>Poll Options:</h2>
        <c:forEach var="option" items="${optionsList}">
            <input type="radio" name="selectedOption" value="${option}">
            ${option}<br>
        </c:forEach>
        
        <br>
        <input type="hidden" name="poll_id" value="${poll_id}">
        <input type="submit" value="Submit Vote" class="btn">
    </form>
 </div>
</body>
</html>