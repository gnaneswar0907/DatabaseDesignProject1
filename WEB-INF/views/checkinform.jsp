<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>CHECK-IN</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css ">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script>
		$(document).ready(function() {
			$("#checkinsuccess").fadeOut(4000);
		});
		</script>
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
                	</li></ul> 
        	</nav>
		
		<div class="container">
        	<div class="row">
        		<div class="col-4">
        			<h6 class="text-primary mt-4">Search for the Books to Check IN</h6>
        			
        			
        				<p id="checkouterror" class="text-danger mt-2 ">${checkouterror }</p>
        			
        				<p id="message" class="text-danger mt-2 ">${message }</p>
        			
        			
        				<p id="checkinsuccess" class="text-success mt-2">${checkinsuccess }</p>
        				
        				<p id="wronginput" class="text-danger mt-2">${wronginput }</p>
        			
        			
        			<form class="mt-4" action="checkinsearchlist" method="POST" >
        				<div class="form-group row">
        					<label class="col-4 col-form-label">Book ISBN</label>
        					<input class="form-control col-4" name="isbn" id="isbn"/>
        					<label class=" col-form-label ml-5">--OR--</label>
        				</div>
        		
        				<div class="form-group row">
        					<label class="col-4 col-form-label">Card ID</label>
        					<input class="form-control col-3" name="card_id" id="card_id"/>
        					<label class="col-form-label ml-5">--OR--</label>
        				</div>
        		
        				<div class="form-group row">
        					<label class="col-4 col-form-label">Borrower Name</label>
        					<input class="form-control col-3" name="bName" id="bName"/>
        				</div>
        				<button class="btn btn-primary mt-3" type="submit">Search</button>
        			</form>
        		</div>
        		<div class="col-8">
        			<table class="table table-hover table-striped mt-4">
                	 <thead class="thead-dark"> 
                 		<tr class="bg-info">
                 			<th>Loan ID</th>
                 			<th>ISBN</th>
                 			<th>Date OUT</th>
                 			<th>Due Date</th>
                 			<th>Date IN</th>
                 			<th>Action</th>
                 		</tr>
                	 </thead>
                	 <tbody>
                 		<c:forEach var="bookLoan" items="${loans }">
                 			<tr >
                 				<td class="table-primary">${bookLoan.getLoanId() }</td>
                 				<td class="table-secondary">${bookLoan.getBook().getIsbn() }</td>
                 				<td class="table-warning">${bookLoan.getDateOut().toString() }</td>
                 				<td class="table-info">${bookLoan.getDueDate().toString() }</td>
                 				<td class="table-info">${bookLoan.getDateIn().toString() }</td>
                 				<td class="table-danger">
                 					<c:url var="checkInLink" value="checkinsearchlist">
                 						<c:param name="loanId" value="${bookLoan.getLoanId() }"></c:param>
                 					</c:url>
                 					<c:choose>
                 						<c:when test="${bookLoan.getDateIn() == null }">
                 							<a class="alert-link" href="${checkInLink }">Check IN</a>
                 						</c:when>
                 						<c:otherwise>
                 							Checked IN
                 						</c:otherwise>
                 					</c:choose>
                 				</td>
                 			</tr>
                 		</c:forEach>
                	</tbody>
             	</table>
        		</div>
        	</div>
		</div>
	</body>
</html>