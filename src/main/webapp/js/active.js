var links = document.getElementsByClassName("nav-link");

var i;
for(i = 0; i < links.length - 1; i++)	{
	
	var element = links[i];
	if(element.href.split("/")[5] === location.pathname.split("/")[3])	{
		element.className += " active";
	}
	else if (location.pathname.split("/").length < 4){
		links[0].className += " active";
	}
	
}