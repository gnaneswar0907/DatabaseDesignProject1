<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css ">
		<style>
			.error{
				color:red;
			}
		</style>
		<title>Borrower Form</title>
	</head>
	<body>
	
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <ul class="nav navbar-nav mr-auto mt-2 mt-lg-0">
					<li class="nav-item px-5">
                    	<a class="nav-link text-light font-weight-bold" href="/">Home</a>
               		</li>
               		<li class="nav-item px-5">
                    	<a class="nav-link text-light font-weight-bold" href="/book/search">Book search</a>
               		</li>
                	<li class="nav-item px-5">
                        <a class="nav-link text-light font-weight-bold" href="/checkin/search">Check-In</a>
                    </li>
                	<li class="nav-item px-5">
                    	<a class="nav-link text-light font-weight-bold" href="/borrower/addborrower">Borrower</a>
                	</li>
                	<li class="nav-item px-5">
                    	<a class="nav-link text-light font-weight-bold" href="/fines/search">Fines</a>
                	</li>
            </ul> 
        </nav>
		
		<div class="container">
			
		
		<div>
			<h2 class="text-primary mt-5 ml-4">
				Enter the Borrower Details
			</h2>
			<form:form cssClass="pt-5 ml-3 mt-4" action="saveBorrower" modelAttribute="borrower" method="POST">
				
				<div class="form-group row">
					<label class="col-2 col-form-label ml-2">Name - </label>
					<form:input cssClass="form-control col-5" path="bName"></form:input>
					<form:errors path="bName" cssClass="ml-3 error" ></form:errors>
				</div>
				<div class="form-group row">
					<label class="col-2 col-form-label ml-2">SSN - </label>
					<form:input cssClass="form-control col-5" path="ssn"></form:input>
					<form:errors cssClass="ml-3 error" path="ssn"></form:errors>
					<span class="ml-2 error">${message}</span>
				</div>
				<div class="form-group row">
					<label class="col-2 col-form-label ml-2">Address - </label>
					<form:input cssClass="form-control col-5" path="address"></form:input>
					<form:errors path="address" cssClass="ml-3 error" ></form:errors>
				</div>
				<div class="form-group row">
					<label class="col-2 col-form-label ml-2">Phone - </label>
					<form:input cssClass="form-control col-5" path="phone"></form:input>
					<form:errors path="phone" cssClass="ml-3 error col" ></form:errors>
				</div>
				
				<button class="btn btn-primary mt-3 ml-3" type="submit">Add Borrower</button>
				
			</form:form>
		</div>
		</div>
	</body>
</html>