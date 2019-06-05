var secondsToWait = 5;
var intervalUpdate = 1;
var currTime = secondsToWait;

// Will execute myCallback every 1 second
var intervalID = window.setInterval(myCallback, intervalUpdate*1000);

function myCallback() {
	
	console.log(currTime);
	currTime = currTime - 1;
	document.getElementById("redirect-message").innerHTML = currTime;
	
	if (currTime == 0) {
		// Move to home Page
		window.location.href = "home.jsp";
	}
}
	
