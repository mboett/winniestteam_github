<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html>
	<head>

		<c:import url="/jsp/head.jsp"/>

		<title>
			<c:if test='${not empty author}'>
				CLEF | <c:out value="${author.name}"/>
			</c:if>
			<c:if test='${empty author}'>
				Not Found
			</c:if>
		</title>

	</head>

	<body>

		<div class = "container">

			<c:import url="/jsp/header.jsp"/>

			<div class="content">
			<c:if test="${sessionScope.log}">
				<c:if test='${not empty author}'>
					
					<h1 class="title"><c:out value="${author.name}" escapeXml="false"/>
						<c:if test="${not fav}">
						  <a href="like?id=<c:out value="${param.id}"/>">
							<button class="heart btn"><i class="far fa-heart"></i></button>
						  </a>
						</c:if>
						<c:if test="${fav}">
						  <a href="dislike?id=<c:out value="${param.id}"/>">
							<button class="heart btn"><i class="fas fa-heart"></i></button>
						  </a>
						</c:if>
					  </h1>

					<div class="row">
						<div class = "col-md-8">
							<div class="table-Responsive">
								<h5>Publications</h5>
								<h6>Click on the name of the paper you are interested in to get more info and statistics</h6>
								<br/>
								<!--<table class="table table-bordered table-striped" id="file_table">-->
								<table id="file_table">
									<thead>
										<tr>
											<th>
												Title
											</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="paper" items="${author.pubList}">
											<tr>
												<td><div id="linkdiv"><a class="link" href="<c:out value="${paper.ee}"/>"><c:out value="${paper.title}" escapeXml="false"/></a></div></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>

						<div class = "col-md-4">
							<div class="container" id="stats">
								<div class="row">
									<canvas id="myChart-A"></canvas>
								</div>
								<div class="row">
									<canvas id="myChart-B"></canvas>
								</div>
							</div>
						</div>
					</div>
				</c:if>
			</c:if>

				<c:if test="${not sessionScope.log}">
					<c:if test='${not empty author}'>
						<h1 class="title"><c:out value="${author.name}" escapeXml="false"/></h1>

						<div class="row">
							<div class = "col-md-8">
								<div class="table-Responsive">
									<h5>Publications</h5>
									<h6>Click on the name of the paper you are interested in to get more info and statistics</h6>
									<br />
									<!--<table class="table table-bordered table-striped" id="file_table">-->
									<table id="file_table">
										<thead>
											<tr>
												<th>
													Title
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="paper" items="${author.pubList}">
												<tr>
													<td><div id="linkdiv"><a class="link" href="<c:out value="${paper.ee}"/>"><c:out value="${paper.title}" escapeXml="false"/></a></div></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>

							<div class = "col-md-4">
								<div class="container" id="stats">
									<div class="row">
										<canvas id="myChart-A"></canvas>
									</div>
									<div class="row">
										<canvas id="myChart-B"></canvas>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					</c:if>


				<c:if test='${empty author}'>
					<div class="row text-center align-items-center" id="search-error">
						<div class="col-md-4">
						</div>
						<div class="col-md-4">
							<div class="row text-center align-items-center">
								<p class="title" style="width:100%;">Cannot find author<!--<c:out value="${name}"/>--></p>
							</div>
							<div class = "row text-center align-items-center">
								<div class="col-md-2">
								</div>
								<div class="col-md-8">
									<img src="https://i.giphy.com/media/ZxekC6fvFAqxPpXJdN/giphy.webp"/>
								</div>
								<div class="col-md-2">
								</div>
							</div>
						</div>
						<div class="col-md-4">
						</div>
					</div>
				</c:if>

				<!--
				<div class="container" style="width: 30%; height: 50%; top: 32%; right: 50%; bottom: 30%; left: 7%;">
				<canvas id="myChart-B"></canvas>
			  </div> -->
			</div>

		<c:import url="/jsp/footer.jsp"/>

    <c:import url="/jsp/foot.jsp"/>
    <c:import url="/jsp/foot.jsp"/>
	<script src="/winniest-team-project-1.00/js/coauthor_stat.js"></script>
	<script src="/winniest-team-project-1.00/js/years_stat.js"></script>
	<script src="/winniest-team-project-1.00/js/code.js"></script>

  </body>
</html>
