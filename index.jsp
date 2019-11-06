<html>
	<head>
		<title>Homepage</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css ">
		<style>
			.bg {
				background-image: url("${pageContext.request.contextPath}/resources/images/database.jpg");
  				height: 100%;
  				background-position: center;
  				background-repeat: no-repeat;
  				background-size: cover;
  				}
		</style>
	</head>
	<body>
	
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
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
		<div class="bg">
			
        </div>
	</body>
</html>