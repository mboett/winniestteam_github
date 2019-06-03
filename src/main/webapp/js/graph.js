
// Define nodes variables
var i;
var j;

// Variable for the sigma graphs
// Graph with all the clusters
var graphCluster;
		
// Graph with the single clusterGraph
var singleGraphCluster;
		
// Log the path of the site
console.log(window.location.pathname);

// App Name
var splitedPath = window.location.pathname.split("/");
var appName = splitedPath[1];

// Log the path of the site
console.log("Splitted name " + appName);

sigma.parsers.gexf(
    'data/total_graph.gexf',
    { // Here is the ID of the DOM element that
      // will contain the graph:
      container: 'graph'
    }
  );