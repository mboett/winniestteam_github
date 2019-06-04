<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html>
	<head>

		<c:import url="/jsp/head.jsp"/>

		<title>
			CLEF | <c:out value="${sessionScope.email}"/>
		</title>
  </head>

	<body>

	<div class = "container">
		
		<c:import url="/jsp/header.jsp"/>

		<div class="content">
		
			<c:if test="${sessionScope.log}">

				<h1 class="title">
					Favourites
				</h1>

				<div class="row">
					<div class="col-md-12">
						<div class="table-Responsive">
							<table id="file_table">
								<tbody>
									<c:forEach var="author" items="${user.favList}">
										<tr>
											<td><div id="linkdiv"><a class="link" href="#"<!--href="<c:out value="${author.ee}"/>"-->><c:out value="${author.author_name}" escapeXml="false"/></a></div></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
					
			</c:if>

			<c:if test="${not sessionScope.log}">
				<h1 class="title">
					ERRORE
				</h1>
			</c:if>
		
		</div>
		
		<c:import url="/jsp/footer.jsp"/>
	
	</div>
	
    <c:import url="/jsp/foot.jsp"/>
	<script src="/winniest-team-project-1.00/js/code.js"></script>

	</body>
</html>
