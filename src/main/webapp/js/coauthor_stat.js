// Current URL of the page
var url = new URL(window.location.href);
var id = url.searchParams.get("id");

// Build Rest URL
var restURL = "rest/statistic/coauthors/"+id;

// Ajax request
$.ajax({
  dataType: "json",
  url: restURL,
  success: displayPie
});

function displayPie(data) {

	// Log message 
	console.log(data.message);

	if (data.message.error-code != null){
	  console.log(data.message.error-code);
	  console.log(data.message.error-details);
	}

	var datas = [];
	var label = [];
	var idAuthors = [];
	var i;
	for (i = 0; i < data.coauthorstat.length; i++) {
	label[i] = data.coauthorstat[i].coauthor;
	datas[i] = data.coauthorstat[i].num;
	idAuthors[i] = data.coauthorstat[i].id; 
	}

	let myChart = document.getElementById('myChart-B').getContext('2d');

	Chart.defaults.global.defaultFontFamily = 'Lato';
	Chart.defaults.global.defaultFontSize = 18;
	Chart.defaults.global.defaultFontColor = '#303234';

	let massPopChart = new Chart(myChart, {
	type:'pie', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
	data:{
	  labels: label,
	  datasets:[{
	  /*  label:'Population',*/
		data: datas,
		//backgroundColor:'green',
		backgroundColor:[
		  '#00bfff',
		  '#6610f2',
		  '#28a745',
		  '#20c997',
		  '#17a2b8',
		  'rgba(255, 159, 64, 0.6)',
		  'rgba(255, 99, 132, 0.6)',
		  '#0000FF',
		  '#8A2BE2',
		  '#A52A2A',
		  '#DEB887',
		  '#5F9EA0',
		  '#7FFF00',
		  '#D2691E',
		  '#FF7F50',
		  '#6495ED',
		  '#DC143C',
		  '#00FFFF',
		  '#00008B',
		  '#008B8B',
		  '#B8860B',
		  '#006400',
		  '#8B008B',
		  '#FF8C00',
		  '#8B0000',
		  '#FF1493',
		  '#1E90FF',
		  '#FF00FF',
		  '#FFD700',
		  '#4B0082',
		  '#FFB6C1',
		  '#FFA07A',
		  '#20B2AA'
		],
		borderWidth:1,
		borderColor:'#777',
		hoverBorderWidth:3,
		hoverBorderColor:'#000'
	  }]
	},
	options:{

	  onClick: pieClickEvent,
	  title:{
		display:true,
		text:['Number collaborations with coauthors'],
		fontSize:18
		},
	 legend:{
		display:false,
		position:'right',
		labels:{
		  fontColor:'#000'
		}
	  },
	  layout:{
		padding:{
		  left:0,
		  right:0,
		  bottom:-25,
		  top:0
		}
	  },
	  tooltips:{
		enabled:true
	  }
	}
	});
});

function pieClickEvent(e, array){
	console.log("Hello " + array[0] + e[0]);
	var id = idAuthors[i];
	window.location = "search-author?id="+id;
}
