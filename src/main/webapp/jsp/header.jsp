<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
	<div class = "row align-items-center" id="header-row">
		<div class = "col-md-2 logo">
			<figure>
				<a href="<c:url value="/jsp/home.jsp"/>">
					<img id="logo" src="<c:url value="/media/clef-initiative-logo.png"/>" class="rounded mx-auto d-block" title="Home">
				</a>
			</figure>
		</div>
		<div class = "col-md-4 title">
			<h1 class = "text-left">The CLEF Initiative</h1>
			<h6 class = "text-left">20<sup>th</sup> anniversary</h6>
		</div>
		<div class = "col-md-3">
		</div>
		<div class="col-md-3">
			<c:if test="${not sessionScope.log}">
				<form action="login" method="POST">
					<div class="form-group">
						<!--<label for="email">E-mail</label>-->
						<div class="input-group">
							<div id="user-img" class="input-group-prepend">
								<span class="input-group-text" id="inputGroupPrepend">
									<i class="fas fa-user"></i>
								</span>
							</div>
							<input type="email" class="form-control form-control-sm" id="email" placeholder="E-mail" name="email">
						</div>
						<!--<label for="pw">Password</label>-->
						<div class="input-group">
							<div id="pw-img" class="input-group-prepend">
								<span class="input-group-text" id="inputGroupPrepend">
									<i class="fas fa-lock"></i>
								</span>
							</div>
							<input type="password" class="form-control form-control-sm" id="pw" placeholder="Password" name="password">
						</div>
					</div>
					<button type="signin" class="btn btn-primary btn-sm btn-block" id="signin-button">Sign in!</button>
					<small id="lanHelp" class="form-text text-muted text-center">Not a member? Sign up <a class="link" href="<c:url value="/jsp/signup.jsp"/>">here</a>.</small>
				</form>
			</c:if>
			<c:if test="${sessionScope.log}">
		    <form action="logout" method="POST">
				<p id="login-as" style="margin-top:-25px;"> 
					Logged as <br> <a class="link" id="user-mail" href="<c:url value="favs?email=${sessionScope.email}"/>"><c:out value="${sessionScope.email}"/> <i class="fas fa-user"></i>	</a>
					<div class="row">
						<div class="col-md-6">
						</div>
						<div class="col-md-6">
							<div class="user-content">
								<div class="userMeasuringWrapper">
									<a href="<c:url value="favs?email=${sessionScope.email}"/>"
										<button type="submit" class="btn btn-primary btn-sm btn-block" id="signin-button">Favourites <i class="fas fa-heart"></i></button>
									</a>
									<button type="submit" class="btn btn-primary btn-sm btn-block" id="signin-button">Logout <i class="fas fa-sign-out-alt"></i></button>
								</div>
							</div>
						</div>
					</div>
				</p>
		    </form>
			</c:if>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<ul class="nav nav-pills nav-fill">
			    <li class="nav-item">
					<a class="nav-link" href="<c:url value="/jsp/home.jsp"/>">
						<i class="fas fa-home"></i>
						Home
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/jsp/papers.jsp"/>">
						<i class="fas fa-file"></i>
						Papers
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/jsp/authors.jsp"/>">
						<i class="fas fa-users"></i>
						Authors
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/jsp/about.jsp"/>">
						<i class="fas fa-question-circle"></i>
						About
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" id="search">
						<i class="fas fa-search"></i>
					</a>
					<div class="search-content">
						<div class="measuringWrapper">
							<form action="search-author-name" method="GET">
								<div class="form-group">
									<p class="text">Author</p>
									<div class="input-group">
										<input type="text" class="form-control form-control-sm" placeholder="Name Surname" name="name">
									</div>
								</div>
								<button type="go" class="btn btn-primary" id="search-button">Search</button>
							</form>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>

</header>
