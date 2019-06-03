<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html>
	<head>

		<c:import url="/jsp/head.jsp"/>

		<title>CLEF | Authors</title>

	</head>

	<body>

		<div class = "container">
			
			<c:import url="/jsp/header.jsp"/>

			<div class="content table-Responsive">
				<h1 class="title">List of authors</h1>
				<h6>Click on the name of the author you are interested in to get more info and statistics</h6>
				<br />
				<div class="row">
					<div class="col-md-12">
						<table class="table table-bordered table-striped" id="authors">
							<thead><tr><th>Name</th></tr></thead>
						</table>
					</div>
				</div>
			</div>

			<c:import url="/jsp/footer.jsp"/>
			
		</div>

		<c:import url="/jsp/foot.jsp"/>
		
		<!-- jQuery -->
		<script src="/winniest-team-project-1.00/js/jquery-3.3.1.min.js"></script>
		<!-- Popper -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"crossorigin="anonymous"></script>
		<!-- Bootstrap -->
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"crossorigin="anonymous"></script>
		<!-- DataTables -->
		<script type="text/javascript" src="/winniest-team-project-1.00/js/DataTables/datatables.min.js"></script>
		<!-- Custom -->
		<script src="/winniest-team-project-1.00/js/code.js"></script>
		<script src="/winniest-team-project-1.00/js/aut.js"></script>
		
	</body>
</html>