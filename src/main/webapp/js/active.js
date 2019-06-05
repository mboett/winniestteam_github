var links = document.getElementsByClassName("nav-link");
var url = location.pathname.split("/");
var i;

// Search the button to activate
for(i = 0; i < links.length; i++)	{
	
	var element = links[i];
	var elHref = element.href.split("/");
	if(url.length < 3 && elHref[elHref.length-1] == "home.jsp"){
		links[i].className += " active";
	} else if(elHref[elHref.length-1] === url[url.length-1]){
		element.className += " active";
	} else if((url[url.length-1] == "login" || url[url.length-1] == "logout") && elHref[elHref.length-1] == "home.jsp" ){
		links[i].className += " active";
	}
}