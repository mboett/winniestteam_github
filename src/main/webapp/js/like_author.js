// Get the button of like
var button_like = document.getElementById("like-button");
var image_like = document.getElementById("like-image");
var like_counter = document.getElementById("like-counter");

var image_classes = image_like.className;
var id = url.searchParams.get("id");

console.log(image_classes);

// Set listener to button click
button_like.addEventListener("click", function(){
	
	// Check if we want to like or dislike the author	
	// NOT liked
	if (image_classes == "far fa-heart"){
		
		// Ajax call to add like
		$.ajax({
				type: "POST",
				contentType: "application/json; charset=utf-8",
				url: "rest/like/"+id,
				success: addLikeUpdateCount,
				error: printError
		});

	// liked
	} else if (image_classes == "fas fa-heart"){
		
		// Ajax call to remove like
		// Ajax call to add like
		$.ajax({
				type: "DELETE",
				contentType: "application/json; charset=utf-8",
				url: "rest/like/"+id,
				success: removeLikeUpdateCount
		});
		
	}
});

// Add Like and update the count of likes
function addLikeUpdateCount(data){
	
	// Remove dislike
	image_like.classList.remove("far");
	// Add like
	image_like.classList.add("fas");
	
	// Update number of likes
	like_counter.textContent = data.likes.count + " people liked this author";
}

// Remove Like and update the count of likes
function removeLikeUpdateCount(data){
	
	// Remove dislike
	image_like.classList.remove("fas");
	// Add like
	image_like.classList.add("far");
	
	// Update number of likes
	like_counter.textContent = data.likes.count + " people liked this author";
}

// Print error message
function printError(msg){
	console.log(msg.message.message);
	console.log(msg.message["error-code"]);
	console.log(msg.message["error-details"]);
}

	
