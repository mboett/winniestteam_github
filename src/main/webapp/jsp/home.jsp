<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
	<head>

		<c:import url="/jsp/head.jsp"/>
		
		<title>Home Page</title>

	</head>

	<body>

		<div class = "container">
			
			<c:import url="/jsp/header.jsp"/>

			<div id = "graph-container" >
				<div id="graph">
					<style>
						#graph {
							position: relative;
						}
					</style>
					<canvas width="0" height="600"></canvas>
				</div>
			</div>

			<c:import url="/jsp/footer.jsp"/>
			
		</div>

		<c:import url="/jsp/foot.jsp"/>
		
		<script type="text/javascript" src="/winniest-team-project-1.00/js/code.js"></script>
		<script type="text/javascript" src="/winniest-team-project-1.00/js/sigma.min.js"></script>
		<script type="text/javascript" src="/winniest-team-project-1.00/js/graph.js"></script>
		<script src="/winniest-team-project-1.00/js/sigma.layout.forceAtlas2/supervisor.js"></script>
		<script src="/winniest-team-project-1.00/js/sigma.layout.forceAtlas2/worker.js"></script>
		
	</body>
</html>