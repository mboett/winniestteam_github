// Current URL of the page
var url = new URL(window.location.href);
var id = url.searchParams.get("id");

// Build Rest URL
var restURL = "../rest/statistic/years/"+id;

// Ajax request
$.ajax({
  contentType: "application/json; charset=utf-8",
  url: restURL,
  success: displayHist
});

function displayHist(data) {
	
	// Log error message
	if (data.message != null){
		console.log(data.message);
		console.log(data.message.error-code);
		console.log(data.message.error-details);
	}
		
	var datas = [];
	var label = [];
	var i;
	var list = data["resource-list"];
	for (i = 0; i < list.length; i++) {
	  label[i] = list[i].year;
	  datas[i] = list[i].num;
	}

	let myChart = document.getElementById('myChart-A').getContext('2d');

	Chart.defaults.global.defaultFontFamily = 'Avant Garde,Avantgarde,Century Gothic,CenturyGothic,AppleGothic,sans-serif';
	Chart.defaults.global.defaultFontSize = 18;
	Chart.defaults.global.defaultFontColor = '#303234';

	let massPopChart = new Chart(myChart, {
	  type:'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
	  data:{
		labels: label,
		datasets:[{
		  label:'Pubblications',
		  data: datas,
		  //backgroundColor:'green',
		  backgroundColor:[
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997',
			'#20c997'
		  ],
		  borderWidth:1,
		  borderColor:'#777',
		  hoverBorderWidth:3,
		  hoverBorderColor:'#000'
		}]
	  },
	  options:{
		title:{
		  display:true,
		  text:['Number of publications over years'],
		  fontSize:21
		},
		scales: {
		  yAxes: [{
			ticks: {
			  beginAtZero: true
			}
		  }]
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
};
