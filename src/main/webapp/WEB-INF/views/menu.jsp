<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}"/>
<div class="top-navbar header b-b">
				<div>
					<ul class=" nav navbar-nav">
						<li><a href="home">Home</a></li>
						<sec:authorize access="hasRole('ADMIN')">
						<li><a href="${pageContext.request.contextPath}/restraMenu" id="menu">Menu</a></li>
						<li><a href="${pageContext.request.contextPath}/expenses" id="expenses">Expenses</a></li>
						<li><a href="${pageContext.request.contextPath}/reports" id="reports">Reports</a></li>
						</sec:authorize>
						<li><a href="billingHome">Items</a></li>
						<li><a href="aboutUs">About us</a></li>
						<li><a href="plans">Plans</a></li>
						<li><a href="termsAndConditions">Terms and Conditions</a></li>
						<li><a href="whyUs">Why us</a></li>
						<li><a href="fAQ">FAQ</a></li>
						<li><a href="contactUs">Contact us</a></li>
					</ul>
					<ul ng-if="name==null" class="nav navbar-nav navbar-right">
						<li><a href="signUp">Sign Up</a></li>
						<li><a href="login">Log In </a></li>
						<c:if test="${pageContext.request.userPrincipal.name != null}">
						<li><a href="logout">Logout</a></li>
						${pageContext.request.userPrincipal.name}
						</c:if>
					</ul>
				</div>
			</div>