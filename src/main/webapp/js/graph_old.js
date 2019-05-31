var i;
var j;
var g = {
          nodes: [],
          edges: []
        };

console.log(window.location.pathname);

//metter if appartenenza cluster, decidere colore e dividere canvas based on how many cluster mathrandom

$.getJSON("/winniest-team-project-1.00/jsp/analysis.json", function ( data ) {
  //1
  for (i = 0; i < data.author.length; i++) {
    if (i % 20 == 0 || i % 20 == 1 || i % 20 == 2 || i % 20 == 3){
      g.nodes.push({ "id": data.author[i].id, "label": data.author[i].name, "x": Math.random()* (240 - 160) + 160, "y": Math.random()* (200 - 0) + 0, "size": data.author[i].rank*0.8*Math.random(), "color": '#28a745'});
    }
  }

    for (i = 0; i < data.author.length; i++) {
      if (i % 20 == 0 || i % 20 == 1 || i % 20 == 2 || i % 20 == 3){
        for (j = 0; j < data.link.length; j++) {
          if (data.author[i].id == data.link[j].from) {
            g.edges.push({"id": j, "source": data.link[j].from, "target": data.link[j].to, "size": 0.001, "color": '#28a745'});
          }
        }
      }
    }

    //2
    for (i = 0; i < data.author.length; i++) {
      if (i % 20 == 12 || i % 20 == 13 || i % 20 == 14 || i % 20 == 15){
        g.nodes.push({ "id": data.author[i].id, "label": data.author[i].name, "x": Math.random()* (80 - 0) + 0, "y": Math.random()* (180 - 20) + 20, "size": data.author[i].rank*0.8*Math.random(), "color": '#00bfff'});
      }
    }

      for (i = 0; i < data.author.length; i++) {
        if (i % 20 == 12 || i % 20 == 13 || i % 20 == 14 || i % 20 == 15){
          for (j = 0; j < data.link.length; j++) {
            if (data.author[i].id == data.link[j].from) {
              g.edges.push({"id": j, "source": data.link[j].from, "target": data.link[j].to, "size": 0.001, "color": '#00bfff'});
            }
          }
        }
      }

      //3
      for (i = 0; i < data.author.length; i++) {
        if (i % 20 == 4 || i % 20 == 5|| i % 20 == 6 || i % 20 == 7){
          g.nodes.push({ "id": data.author[i].id, "label": data.author[i].name, "x": Math.random()* (320 - 240) + 240, "y": Math.random()* (200 - 0) + 0, "size": data.author[i].rank*0.8*Math.random(), "color": '#20c997'});
        }
      }

      for (i = 0; i < data.author.length; i++) {
        if (i % 20 == 4 || i % 20 == 5|| i % 20 == 6 || i % 20 == 7){
            for (j = 0; j < data.link.length; j++) {
              if (data.author[i].id == data.link[j].from) {
                g.edges.push({"id": j, "source": data.link[j].from, "target": data.link[j].to, "size": 0.001, "color": '#20c997'});
              }
            }
          }
        }

        //4
        for (i = 0; i < data.author.length; i++) {
          if (i % 20 == 16 || i % 20 == 17 || i % 20 == 18 || i % 20 == 19){
            g.nodes.push({ "id": data.author[i].id, "label": data.author[i].name, "x": Math.random()* (160 - 80) + 80, "y": Math.random()* (200 - 0) + 0, "size": data.author[i].rank*0.8*Math.random(), "color": '#6610f2'});
          }
        }

          for (i = 0; i < data.author.length; i++) {
            if (i % 20 == 16 || i % 20 == 17 || i % 20 == 18 || i % 20 == 19){
              for (j = 0; j < data.link.length; j++) {
                if (data.author[i].id == data.link[j].from) {
                  g.edges.push({"id": j, "source": data.link[j].from, "target": data.link[j].to, "size": 0.001, "color": '#6610f2'});
                }
              }
            }
          }

          for (i = 0; i < data.author.length; i++) {
            if (i % 20 == 8 || i % 20 == 9 || i % 20 == 10 || i % 20 == 11){
              g.nodes.push({ "id": data.author[i].id, "label": data.author[i].name, "x": Math.random()* (400 - 320) + 320, "y": Math.random()*(180 - 20) + 20, "size": data.author[i].rank*0.8*Math.random(), "color": '#17a2b8'});
            }
          }

          for (i = 0; i < data.author.length; i++) {
            if (i % 20 == 8 || i % 20 == 9 || i % 20 == 10 || i % 20 == 11){
              for (j = 0; j < data.link.length; j++) {
                if (data.author[i].id == data.link[j].from) {
                    g.edges.push({"id": j, "source": data.link[j].from, "target": data.link[j].to, type:'curve', size:1, "color": '#17a2b8'});
                  }
                }
              }
            }

  s = new sigma({
    graph: g,
    renderer: {
      container: document.getElementById('graph-container'),
      type: 'canvas'
    },
    settings: {
     minNodeSize: 5,
     maxNodeSize: 8.5,
    }
  });

  s.bind("clickNode", function () {
  	if (window.location.pathname == "/winniest-team-project-1.00/"){
  		window.location = "jsp/author.jsp";
  	}
  	else {
    	window.location = "author.jsp";
    }
  });
});
