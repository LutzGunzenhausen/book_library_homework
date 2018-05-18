<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<link href="<spring:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<spring:url value="/js/jquery.1.10.2.min.js" var="jqueryJs" />
	<!-- <spring:url value="/js/main.js" var="mainJs" /> -->
	
    <script src="${jqueryJs}"></script>
    <!-- <script src="${mainJs}"></script>  -->
</head>
<body>
<script type="text/javascript">
     MyFunction = function(){
         console.log("java Script");
         var iDiv = document.createElement('div');
		iDiv.id = 'someBook';
		iDiv.className = 'bookLine';
		
		document.getElementsByTagName('msg')[0].appendChild(iDiv);
		
		// Now create and append to iDiv
		var innerDiv = document.createElement('div');
		innerDiv.className = 'block-2';

		// The variable iDiv is still good... Just append to it.
		iDiv.appendChild(innerDiv);
		
		$('#msg').html("This is updated by jQuery")
		return true;
     }
 </script>

<h1>1. Test CSS</h1>

<h2>2. Test JS</h2>
<div class="formcontainer">
	<!-- <form  name="myForm" class="form-horizontal" method="POST"> -->
	<form  name="myForm" class="form-horizontal" id="loadCategory" >
		<input type="text" id="dailyGrossIncome" pattern="^[0-9]+([.][0-9]{2}){0,1}$"
				title="The gross daily income. The expected pattern is 00.00"
				name="netIncome" class="form-control-input-sm"
				value="0"
				placeholder="The category" />
		<input type="button" value="Check" class="btn btn-primary btn-sm" onclick="javascript:MyFunction()"/>
	</form>
</div>
<div id="msg">
	<div id="msg1" class="book" >
		<div class="bookLine">
		   Name: SomeGreatBook
		</div>
		<div class="formcontainer">
			Category: Dingdongelong
		</div>
	</div>
</div>

</body>
</html>
