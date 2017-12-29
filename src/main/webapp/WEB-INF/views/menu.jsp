<script  src="static/JSlib/kafeneioCommons.js"></script>

<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}"/>
<%-- <input type="text" name="currentDate" id="currentDate" value="${currentDate}"/> --%> 

<div class="top-navbar header b-b">
	<div>
		<ul class="nav navbar-nav">
			<li><a href="${pageContext.request.contextPath}/home">Home</a></li>
			<sec:authorize access="hasRole('ADMIN')">
				<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Admin <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li ><a href="${pageContext.request.contextPath}/restraMenu"
							id="menu" style="color:white">Menu</a></li>
						<li><a href="${pageContext.request.contextPath}/expenses"
							id="expenses" style="color:white">Expenses</a></li>
						<li><a href="${pageContext.request.contextPath}/reports"
							id="reports" style="color:white">Reports</a></li>
					</ul>
				</li>
				
				
				<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Masters<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li ><a href="${pageContext.request.contextPath}/units"
							id="units" style="color:white">Setup Units</a></li>
						<li><a href="${pageContext.request.contextPath}/addMenuItems"
							id="menuStore" style="color:white">Setup Menu Store</a></li>
						
					</ul>
				</li>
				
			<!-- <li><a href="addMenuItems">Setup Menu Store</a></li> -->
			</sec:authorize>
			
			<li><a href="#">About us</a></li>
			<li><a href="#">Terms and Conditions</a></li>
			<li><a href="#">Why us</a></li>
			<li><a href="#">FAQ</a></li>
			<li><a href="#">Contact us</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<!-- <li><a href="signUp">Sign Up</a></li> -->
			<c:if
				test="${pageContext.request.userPrincipal.name == null or pageContext.request.userPrincipal.name == ''}">
				<li><a href="login">Log In </a></li>
			</c:if>
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<li><spring:message code="kafeneio.welcome.message" />
					${pageContext.request.userPrincipal.name}</li>
				<li><a href="logout">Logout</a></li>
			</c:if>
		</ul>
	</div>
</div>
<!-- 

<nav class="navbar navbar-default">
  <div class="container-fluid">
    Brand and toggle get grouped for better mobile display
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

    Collect the nav links, forms, and other content for toggling
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul>
    </div>/.navbar-collapse
  </div>/.container-fluid
</nav> -->