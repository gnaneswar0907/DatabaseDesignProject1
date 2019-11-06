<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Fines</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css ">
		<script type='text/javascript' src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				
				$("#notreturned").click(function(event){
					event.preventDefault();
				});
				
				$("#past").change(function(){
					$("totalfines").toggle();
					$("currentfines").toggle();
				});
				
				
				
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
		
			<%-- <c:if test="${param.cardiderror !=null }">
				<p id="cardiderror" class="alert alert-danger mt-3 ml-3">${cardiderror}</p>
			</c:if> --%>
			
			
			
			<p id="cardiderror" class="text-danger mt-3 ml-3">${cardiderror}</p>
			
			<form class="mt-3" action="fineslist" method="post">
				<div class="form-group row">
					<label class="col-form-label ml-2 font-weight-bold">Card ID - </label>
					<input class="form-control ml-3 col-3" type="text" value="${cardid }" placeholder="enter the card id number" id="cardid" name="cardid"/>
					
					
					
					<button class="btn btn-primary ml-4" type="submit">get fine data</button>
					
					<div class="row">
						<input class="ml-5 mt-3" name="pastfines" id="pastfines" value="pastfines" type="checkbox"></input>
						 <label class="ml-2 mt-2">Include Past Fines</label>
					</div>	
					
				</div>
			</form>
			
			
			
			<div class="card card-borderless mt-5">
                 
                 
                 
                 <table id="currentfines" class="table table-hover table-striped">
                 	<thead class="thead-dark"> 
                 		<tr class="bg-info">
                 			<th >Loan ID</th>
                 			<th >Fine Amount</th>
                 			<th >Paid / Unpaid</th>
                 		</tr>
                 	</thead>
                 	<tbody>
                 		<c:forEach var="fine" items="${fines }">
                 			<tr >
                 				<td class="table-primary">${fine.getBookloan().getLoanId() }</td>
                 				<td class="table-secondary"> $ ${fine.getFineAmount() }</td>
                 				<td class="table-info">
                 					<c:url var="finepayment" value="/fines/payfine">
                 						<c:param name="loanid" value="${fine.getBookloan().getLoanId() }"></c:param>
                 					</c:url>
                 					<c:choose>
                 						<c:when test="${fine.isPaid() }">
                 							Paid!!! 
                 						</c:when>
                 						<c:otherwise>
                 							<c:choose>
                 								<c:when test="${fine.getBookloan().getDateIn()==null }">
                 									Payment Due but first return the book | <a id="notreturned" class="alert-link" href="${finepayment }">pay</a>
                 								</c:when>
                 								<c:otherwise>
                 									Payment Due | <a class="alert-link" onclick="if(!(confirm('Are you sure you wnat to pay this fine ?'))) return false" href="${finepayment }">pay</a>
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
			<div>
				<label class="mt-4 ml-3 font-weight-bold">${totalfine }</label>
				<%-- <label> ${totalfine }</label> --%>
			</div>
		</div>
	</body>
</html>