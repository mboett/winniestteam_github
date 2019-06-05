var links = document.getElementsByClassName("nav-link");
var url = location.pathname.split("/");
var i;

for(i = 0; i < links.length; i++)	{
	
	var element = links[i];
	var elHref = element.href.split("/");
	if(elHref[elHref.length-1] === url[url.length-1]){
		element.className += " active";
	}
	
}

if(url[url.length-1] == "login"){
	links[0].className += " active";
}