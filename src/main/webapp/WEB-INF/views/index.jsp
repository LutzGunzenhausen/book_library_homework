<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script src="rest.js"></script>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Net-Income calculator</title>  
		<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	</head>

	<body>
		<div align="center">
			<div class="formcontainer">
				<!-- <form  name="myForm" class="form-horizontal" method="POST"> -->
				<form  name="myForm" class="form-horizontal" id="loadCategory" >
					<input type="text" id="dailyGrossIncome" pattern="^[0-9]+([.][0-9]{2}){0,1}$"
							title="The gross daily income. The expected pattern is 00.00"
							name="netIncome" class="form-control-input-sm"
							value="0"
							placeholder="The category"/>
					<input type="submit" value="Check" class="btn btn-primary btn-sm"/>
				</form>
			</div>
		</div>
	</body>
</html>