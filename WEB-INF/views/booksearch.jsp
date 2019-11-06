<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!Doctype HTML>
<html>
	<head>
		<title>Book Search</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css ">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#borrowersuccess").fadeOut(4000);
				$("#checkoutsuccess").fadeOut(4000);
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
                	</li>
            	</ul> 
        	</nav>
		
		<div class="container">
			<form:form class="mt-2" action="searchresult" method="POST">
				<div class="form-group row">
					<label class="col-form-label ml-3 mt-3"><strong>Key Word - </strong></label>
					<input class="form-control ml-4 col-5 mt-3" name="keyword" value="${keyword }" type="text" placeholder="enter keyword for searching" />
					<button class="btn btn-primary mt-3 ml-4 col-2" type="submit">Search</button>
				</div>
			</form:form>
			
			<div class="mt-3">
				<c:if test="${param.borrowersuccess !=null}">
					<p id="borrowersuccess" class=" alert alert-success">${borrowersuccess }</p>
				</c:if>
        		
        		<c:if test="${param.checkoutsuccess!=null }">
        			<p id="checkoutsuccess" class=" alert alert-success">${checkoutsuccess }</p>
        		</c:if>
			</div>
				
        		
        	
			
			<div class="mt-5">
                 
                 <table class="table table-hover table-striped">
                 	<thead class="thead-dark"> 
                 		<tr class="bg-info row">
                 			<th class="col-3" >ISBN</th>
                 			<th class="col-3">Book Title</th>
                 			<th class="col-3">Book Author</th>
                 			<th class="col-3">Book Availability</th>
                 		</tr>
                 	</thead>
                 	<tbody>
                 		<c:forEach var="resultBook" items="${resultBooks }">
                 			<tr class="row">
                 				<td class="table-primary col-3">${resultBook.getIsbn() }</td>
                 				<td class="table-secondary col-3">${resultBook.getTitle() }</td>
                 				<td class="table-warning col-3">${resultBook.getAuthors() }</td>
                 				<td class="table-info col-3">
                 					<c:url var="checkoutLink" value="/checkout/cardidform">
                 						<c:param name="isbn" value="${resultBook.getIsbn() }"></c:param>
                 					</c:url>
                 					<c:choose>
                 						<c:when test="${ resultBook.getBookLoan()==null}">
                 							Available | <a class="alert-link" href="${checkoutLink }">checkout</a>
                 						</c:when>
                 						<c:otherwise>
                 							<c:choose>
                 								<c:when test="${ resultBook.getBookLoan().getDateIn()== null && resultBook.getBookLoan().getDateOut()!=null }">
                 									Unavailable | <a class="alert-link" href="#" onclick="alert('You cannot checkout this book as it is unavailable')">checkout</a>
                 								</c:when>
                 								<c:otherwise>
                 									Available | <a class="alert-link" href="${checkoutLink }">checkout</a>
                 								</c:otherwise>
                 							</c:choose>
                 						</c:otherwise>
                 					</c:choose>
                 					
                 				</td>
                 			</tr>
                 		</c:forEach>
                 	</tbody>
                 </table>
            </div>
            
		</div>
	</body>
</html>