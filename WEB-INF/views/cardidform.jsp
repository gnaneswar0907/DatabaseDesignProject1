<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Card ID Form</title>
		<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
		  
		<script type='text/javascript' src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				
				$("#checkout").click(function(event){
					if ($('#card_id').val()==""){
						  alert("Please enter an ID to continue");
						  event.preventDefault();
						  }
					
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
		
        	<div>
        		<h5 class="text-primary mt-4 ml-4">Enter the Card ID for the Check-OUT</h5>
        		
        			<p class="text-danger mt-2 ml-3">${cardiderror }</p>
        		
        	</div>
			<div>
				<form action="checkoutform" method="post">
					<div class="form-group row mt-3 ml-3">
						<label class="col-form-label col-2">ISBN - </label>
						<input class="form-control col-5 " name="isbn" type="text" value="${isbn }"  readonly/>
					</div>
					<div class="form-group row mt-2 ml-3">
						<label class="col-form-label col-2">Card ID - </label>
						<input class="form-control col-5 " type="number" id="card_id" name="card_id" />
					</div> 
					<button type="submit" id="checkout" class="btn btn-primary mt-4 ml-4">Check Out</button>
				</form>
			</div>
		</div>
		
	</body>
</html>