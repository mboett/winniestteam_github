$("#search").hover(function(){
    var content = document.getElementsByClassName("search-content");
    content[0].style.display = "block";
	if (content[0].clientHeight) {
	} else {
		var wrapper = document.querySelector('.measuringWrapper');
		content[0].style.height = wrapper.clientHeight + "px";
	}
});
  
$(".search-content").hover(function(){
	}, function(){
	var content = document.getElementsByClassName("search-content");
	if (content[0].clientHeight) {
		content[0].style.height = 0;
	} else {
		var wrapper = document.querySelector('.measuringWrapper');
		content[0].style.height = wrapper.clientHeight + "px";
	}
	content[0].style.display = "none";	
	
});

$("#pw-img").click(function () {
    $("#pw").trigger("select");
});

$("#user-img").click(function () {
    $("#email").trigger("select");
});
