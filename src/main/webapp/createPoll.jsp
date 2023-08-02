<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html data-theme="light">
<head>
<meta charset="ISO-8859-1">
<title>Create Poll</title>
<script src="https://cdn.tailwindcss.com"></script>
<link href="https://cdn.jsdelivr.net/npm/daisyui@3.5.0/dist/full.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
	<h1 class="text-4xl text-center">Create a poll</h1>
	
	<c:if test="${not empty hasMessage and hasMessage eq 'true'}">
    	<div class="toast toast-end">
  			<div class="alert alert-error">
    			<span>${errorMessage}</span>
  			</div>
		</div>
	</c:if>
	
	<c:if test="${not empty success and success eq 'true'}">
    	<div class="toast toast-end">
  			<div class="alert alert-success">
    			<span>http://localhost:8080/JavaOnlinePoll/polls?id=${poll_id}</span>
  			</div>
		</div>
	</c:if>
	
	<form action="createpoll" method="post">
	
	<div class="mx-auto my-16">
        <label for="question text-xl">Question:</label><br>
        <input type="text" placeholder="Type here" class="input input-bordered input-md w-full max-w-xs" name = "question"/><br><br>

        <label for="option1">Option 1:</label><br>
        <input type="text" placeholder="Option 1" class="input input-bordered input-md w-full max-w-xs" name="option1"/><br><br>

        <label for="option2">Option 2:</label><br>
        <input type="text" placeholder="Option 2" class="input input-bordered input-md w-full max-w-xs" name="option2"/><br><br>

        <label for="option3">Option 3:</label><br>
        <input type="text" placeholder="Option 3" class="input input-bordered input-md w-full max-w-xs" name="option3"/><br><br>

        <label for="option4">Option 4:</label><br>
        <input type="text" placeholder="Option 4" class="input input-bordered input-md w-full max-w-xs" name="option4"/><br><br>
        <button type="submit" class="btn text-blue-600 hover:text-white hover:bg-blue-700">Create</button>
	</div>
       
    </form>
</body>
</html>