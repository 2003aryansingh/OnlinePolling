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
<style>
@import url('https://fonts.googleapis.com/css2?family=Lugrasimo&display=swap');
</style>
</head>
<body>
<div class="navbar bg-base-100 text-sky-800 shadow-xl fixed top-0 w-full">
  	<div class="flex-1">
    	<a class="btn btn-ghost normal-case text-xl" href="Home.jsp">VerdictValley</a>
  	</div>
  	<div class="flex-none">
    	<ul class="menu menu-horizontal px-1">
      		<li><a href="register.jsp">Register</a></li>
      		<li>
        		<a href="Login.jsp">Login</a>
      		</li>
    	</ul>
  	</div>
</div>
<div class="hero min-h-screen bg-base-200">
  <div class="hero-content text-center">
    <div class="max-w-md">
      <h1 class="text-5xl font-bold">VERDICT VALLEY</h1>
      <p class="py-6">"Discover Insights, Cast Your Votes, Make a Difference."
      </p>
      <a class="btn btn-primary" href="register.jsp">Get Started</a>
    </div>
  </div>
</div>
</body>
</html>