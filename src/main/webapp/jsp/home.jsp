<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
	<head>

		<c:import url="/jsp/head.jsp"/>
		
		<title>CLEF | Home</title>

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
		<!--<script type="text/javascript" src="/winniest-team-project-1.00/js/sigma.min.js"></script>-->
		<!--<script type="text/javascript" src="/winniest-team-project-1.00/js/graph.js"></script>-->
		<script src="/winniest-team-project-1.00/js/src/sigma.core.js"></script>
		<script src="/winniest-team-project-1.00/js/src/conrad.js"></script>
		<script src="/winniest-team-project-1.00/js/src/utils/sigma.utils.js"></script>
		<script src="/winniest-team-project-1.00/js/src/utils/sigma.polyfills.js"></script>
		<script src="/winniest-team-project-1.00/js/src/sigma.settings.js"></script>
		<script src="/winniest-team-project-1.00/js/src/classes/sigma.classes.dispatcher.js"></script>
		<script src="/winniest-team-project-1.00/js/src/classes/sigma.classes.configurable.js"></script>
		<script src="/winniest-team-project-1.00/js/src/classes/sigma.classes.graph.js"></script>
		<script src="/winniest-team-project-1.00/js/src/classes/sigma.classes.camera.js"></script>
		<script src="/winniest-team-project-1.00/js/src/classes/sigma.classes.quad.js"></script>
		<script src="/winniest-team-project-1.00/js/src/classes/sigma.classes.edgequad.js"></script>
		<script src="/winniest-team-project-1.00/js/src/captors/sigma.captors.mouse.js"></script>
		<script src="/winniest-team-project-1.00/js/src/captors/sigma.captors.touch.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/sigma.renderers.canvas.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/sigma.renderers.webgl.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/sigma.renderers.svg.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/sigma.renderers.def.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/webgl/sigma.webgl.nodes.def.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/webgl/sigma.webgl.nodes.fast.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/webgl/sigma.webgl.edges.def.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/webgl/sigma.webgl.edges.fast.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/webgl/sigma.webgl.edges.arrow.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/canvas/sigma.canvas.labels.def.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/canvas/sigma.canvas.hovers.def.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/canvas/sigma.canvas.nodes.def.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/canvas/sigma.canvas.edges.def.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/canvas/sigma.canvas.edges.curve.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/canvas/sigma.canvas.edges.arrow.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/canvas/sigma.canvas.edges.curvedArrow.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/canvas/sigma.canvas.edgehovers.def.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/canvas/sigma.canvas.edgehovers.curve.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/canvas/sigma.canvas.edgehovers.arrow.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/canvas/sigma.canvas.edgehovers.curvedArrow.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/canvas/sigma.canvas.extremities.def.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/svg/sigma.svg.utils.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/svg/sigma.svg.nodes.def.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/svg/sigma.svg.edges.def.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/svg/sigma.svg.edges.curve.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/svg/sigma.svg.labels.def.js"></script>
		<script src="/winniest-team-project-1.00/js/src/renderers/svg/sigma.svg.hovers.def.js"></script>
		<script src="/winniest-team-project-1.00/js/src/middlewares/sigma.middlewares.rescale.js"></script>
		<script src="/winniest-team-project-1.00/js/src/middlewares/sigma.middlewares.copy.js"></script>
		<script src="/winniest-team-project-1.00/js/src/misc/sigma.misc.animation.js"></script>
		<script src="/winniest-team-project-1.00/js/src/misc/sigma.misc.bindEvents.js"></script>
		<script src="/winniest-team-project-1.00/js/src/misc/sigma.misc.bindDOMEvents.js"></script>
		<script src="/winniest-team-project-1.00/js/src/misc/sigma.misc.drawHovers.js"></script>
		<script src="/winniest-team-project-1.00/js/plugins/sigma.layout.forceAtlas2/supervisor.js"></script>
		<script src="/winniest-team-project-1.00/js/plugins/sigma.layout.forceAtlas2/worker.js"></script>
		<script src="/winniest-team-project-1.00/js/plugins/sigma.renderers.edgeLabels/sigma.canvas.edges.labels.curve.js"></script>
		<script src="/winniest-team-project-1.00/js/plugins/sigma.plugins.neighborhoods/sigma.plugins.neighborhoods.js"></script>
		<script src="/winniest-team-project-1.00/js/plugins/sigma.parsers.gexf/gexf-parser.js"></script>
		<script src="/winniest-team-project-1.00/js/plugins/sigma.parsers.gexf/sigma.parsers.gexf.js"></script>
		<script>
		
		// App Name
		var splitedPath = window.location.pathname.split("/");
		var appName = splitedPath[1];
		var nodeId = -1;
		var counter = 0;
		
		sigma.classes.graph.addMethod('neighbors', function(nodeId) {
			var k,
				neighbors = {},
				index = this.allNeighborsIndex[nodeId] || {};

			for (k in index)
			  neighbors[k] = this.nodesIndex[k];

			return neighbors;
		});
		
		s = new sigma({
			renderer: {
				container: document.getElementById('graph'),
				type: 'canvas'
			},
			settings : {
				animationsTime: 1000,
				minNodeSize: 0.2,
				maxNodeSize: 13,
				labelSize: "proportional",
				labelSizeRatio: 2,
				font: "Avant Garde,Avantgarde,Century Gothic,CenturyGothic,AppleGothic,sans-serif",
				enableCamera: false, // disable moving around the graph
				drawLabels: false, // do not show labels (only on hover)
				maxEdgeSize: 0.2,
				defaultEdgeType: 'curve',
				zommingRatio: 1 // disable zoom
			}
		});



				
		sigma.parsers.gexf('/winniest-team-project-1.00/js/data/total_graph.gexf', s,
			function() {

				s.graph.nodes().forEach(function(n) {
				n.label = decodeEntities(n.label);
			});


				s.graph.nodes().forEach(function(n) {
				n.originalColor = n.color;
			});
				s.graph.edges().forEach(function(e) {
				e.originalColor = e.color;
			});
			
			s.refresh();
			
			//s.startForceAtlas2();
			//window.setTimeout(function() {s.killForceAtlas2()}, 10000);	
			
			s.bind('clickNode', function(e) {
				nodeId = e.data.node.id;
				var toKeep = s.graph.neighbors(nodeId);
				toKeep[nodeId] = e.data.node;

				s.graph.nodes().forEach(function(n) {
				  if (toKeep[n.id])
					n.color = n.originalColor;
				  else
					n.color = '#eee';
				});

				s.graph.edges().forEach(function(e) {
				  if (toKeep[e.source] && toKeep[e.target])
					e.color = e.originalColor;
				  else
					e.color = '#eee';
				});
				s.refresh();
			});
			
			s.bind('clickStage', function(e) {
				s.graph.nodes().forEach(function(n) {
				  n.color = n.originalColor;
				});

				s.graph.edges().forEach(function(e) {
				  e.color = e.originalColor;
				});

				s.refresh();
			});
						
			s.bind("doubleClickNode", function(e){
			
				// Get informations about the node
				var node = e.data.node; 
				var id = node.id;
				
				// Log result
				console.log("Node ID:" + node.id);
				
				// Make a request to the server the correct page
				if ("/"+appName+"/" == window.location.pathname){
					window.location = "jsp/search-author?id="+id;
					console.log(window.location.pathname + " " + "jsp/search-author?id="+id);
				} else {
					window.location = "search-author?id="+id;
					console.log(window.location.pathname + " " + "search-author?id="+id);
				}	
				
			});
						
		});
		
		
		var decodeEntities = (function() {
		  // this prevents any overhead from creating the object each time
		  var element = document.createElement('div');

		  function decodeHTMLEntities (str) {
		    if(str && typeof str === 'string') {
		      // strip script/html tags
		      str = str.replace(/<script[^>]*>([\S\s]*?)<\/script>/gmi, '');
		      str = str.replace(/<\/?\w(?:[^"'>]|"[^"]*"|'[^']*')*>/gmi, '');
		      element.innerHTML = str;
		      str = element.textContent;
		      element.textContent = '';
		    }

		    return str;
		  }

		  return decodeHTMLEntities;
		})();

		
		</script>
		
	</body>
</html>