<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html data-theme="light">
<head>
<meta charset="UTF-8">
<title>Poll Results</title>
<script src="https://cdn.tailwindcss.com"></script>
<link href="https://cdn.jsdelivr.net/npm/daisyui@3.5.0/dist/full.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
    <h1 class="text-5xl my-8 text-center">Poll Results</h1>
    
    
    <c:forEach var="pollEntry" items="${pollResults}" varStatus="loopStatus">
        <h3 class="text-3xl my-8 mx-4">${loopStatus.index + 1}: ${questions.get(loopStatus.index)}</h3>
		<div class="overflow-x-auto">
    		<table class="table">
        		<thead>
          			<tr>
          				<th>Option</th>
          				<th>Percentage</th>
          			</tr>
        		</thead>
        		<tbody>
        		<c:forEach var="optionEntry" items="${pollEntry.value}">
      				<tr class="bg-base-200">
        				<td>${optionEntry.key}</td>
        				<td>${optionEntry.value}%</td>
            		</tr>
        		</c:forEach>
    			</tbody>
    		</table>
		</div>
    </c:forEach>
</body>
</html>

    