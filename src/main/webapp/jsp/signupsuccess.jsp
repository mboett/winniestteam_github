<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
	<head>

		<c:import url="/jsp/head.jsp"/>

		<title>CLEF | Sign up!</title>

	</head>

	<body>

		<div class = "container">
			<header>
				<div class = "row align-items-start">
					<div class = "col-md-5">
					</div>
					<div class = "col-md-2 logo" id="signup-logo" >
						<figure>
							<a href="<c:url value="/jsp/home.jsp"/>">
								<img src="<c:url value="/media/clef-initiative-logo.png"/>" class="rounded mx-auto d-block" title="Home">
							</a>
						</figure>
					</div>
					<div class = "col-md-5">
					</div>
				</div>
			</header>

			<div class="row align-items-center" id="content" style="height: 250px;">
				<div class="col-md-12">
					<div class = "row">
						<div class = "col-md-4">
						</div>
						<div class = "col-md-4" id="signup-form">
							<div id="signup-title">
								Successfully signed up!
							</div>
							<div class = "col-md-4">
							</div>
						</div>
					</div>

					<div class = "row">
						<div class = "col-md-12">
							<p class="text text-center">
								You will be redirected to the Home Page in <span id="redirect-message"> 5 </span> seconds or you can click <a class="link" href="<c:url value="/jsp/home.jsp"/>">here</a>.
							</p>
						</div>
					</div>
				</div>
			</div>

		<!-- Bootstrap -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"crossorigin="anonymous"></script>

		<!-- JavaScript -->
		<script src="/winniest-team-project-1.00/js/formvalidation.js"></script>
		<script src="/winniest-team-project-1.00/js/redirect.js"></script>
	</body>
</html>
