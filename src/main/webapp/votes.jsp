<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html data-theme="light">
<head>
<meta charset="ISO-8859-1">
<title>Votes Page</title>
<script src="https://cdn.tailwindcss.com"></script>
<link href="https://cdn.jsdelivr.net/npm/daisyui@3.5.0/dist/full.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
   <div class="m-auto">
		<h1 class="text-4xl text-center">Welcome, ${sessionScope.username}!</h1>
   </div>	
  
  <div class="flex flex-row justify-center my-64 space-x-7">
    <a href="createPoll.jsp" class="btn btn-primary">Create a Poll</a>
    <a href="renderpolls?userId=${userId}" class="btn btn-primary">View Existing Polls</a>
  </div>
</body>
</html>