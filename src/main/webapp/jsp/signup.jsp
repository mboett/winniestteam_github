<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
	<head>

		<c:import url="/jsp/head.jsp"/>

		<title>Sign up!</title>

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

			<div id="content">
				<div class = "row align-items-start">
					<div class = "col-md-4">
					</div>
					<div class = "col-md-4" id="signup-form">
						<div id="signup-title">
							Join us!
						</div>
						<form method="POST" action="signup">
							<div class="form-group">
								<p class="text">E-Mail</p>
								<div class="input-group">
									<div id="user-img" class="input-group-prepend">
										<span class="input-group-text" id="inputGroupPrepend">
											<i class="fas fa-user"></i>
										</span>
									</div>
									<input type="text" class="form-control" id="input-email" placeholder="email@example.com" required name="email">
								</div>
								<div>
									<span class="email-error"></span>
								</div>
							</div>
							<div class="form-group">
								<p class="text">Password</p>
								<div class="input-group">
									<div id="pw-img" class="input-group-prepend">
										<span class="input-group-text" id="inputGroupPrepend">
											<i class="fas fa-lock"></i>
										</span>
									</div>
									<input type="password" class="form-control" id="input-pw" placeholder="Password" required name="password">
								</div>
								<div>
									<span class="pw-error"></span>
								</div>
							</div>
							<div class="form-group">
								<p class="text">Re-enter the password</p>
								<div class="input-group">
									<div id="conf-pw-img" class="input-group-prepend">
										<span class="input-group-text" id="inputGroupPrepend">
											<i class="fas fa-redo"></i>
										</span>
									</div>
									<input type="password" class="form-control" id="input-conf-pw" placeholder="Confirm password" required>
								</div>
								<div>
									<span class="conf-pw-error"></span>
								</div>
							</div>
							<button type="submit" class="btn btn-primary" id="signup-button">Sign up</button>
						</form>
						<div class = "col-md-4">
						</div>
					</div>
				</div>

				<div class = "row align-items-start">
					<div class = "col-md-12">
						<p class="text text-center">
							or return to the <a class="link" href="<c:url value="/jsp/home.jsp"/>">Home Page</a>.
						</p>
					</div>
				</div>
			</div>

		<!-- Bootstrap -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"crossorigin="anonymous"></script>

		<!-- JavaScript -->
		<script src="/winniest-team-project-1.00/js/formvalidation.js"></script>
	</body>
</html>
