// Get all the nav bar elements
var links = document.getElementsByClassName("nav-link");

// Get the URL of the current page
var url = location.pathname.split("/");
console.log(url.length);

// Search the button to activate
for(var i = 0; i < links.length-1; i++)	{
	
	// Select one nav element
	var el = links[i];
	
	// Get the link and divide it into pieces
	var elHref = el.href.split("/");
	if(url.length < 4 && elHref[elHref.length-1] == "home.jsp"){
		links[i].className += " active";
	} else if(elHref[elHref.length-1] === url[url.length-1]){
		el.className += " active";
	} else if((url[url.length-1] == "login" || url[url.length-1] == "logout") && elHref[elHref.length-1] == "home.jsp" ){
		links[i].className += " active";
	}
}