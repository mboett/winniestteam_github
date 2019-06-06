// Get all the nav bar elements
var links = document.getElementsByClassName("nav-link");

// Get the URL of the current page
var url = location.pathname.split("/");
console.log(url.length);

// Search the button to activate
for(var i = 0; i < links.length; i++)	{
	
	// Select one nav element
	var element = links[i];
	
	// If the nav element points to a different page
	if(element.href != null){
		
		// Get the link and divide it into pieces
		var elHref = element.href.split("/");
		if(url.length < 3 && elHref[elHref.length-1] == "home.jsp"){
			links[i].className += " active";
		} else if(elHref[elHref.length-1] === url[url.length-1]){
			element.className += " active";
		} else if((url[url.length-1] == "login" || url[url.length-1] == "logout") && elHref[elHref.length-1] == "home.jsp" ){
			links[i].className += " active";
		}
	}
}