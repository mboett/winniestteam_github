// Get the button of like
var button_like = document.getElementById("like-button");
var image_like = document.getElementById("like-image");
var like_counter = document.getElementById("like-counter");

if (button_like != null && image_like != null){
	
	var image_classes = image_like.className;
	var id = url.searchParams.get("id");

	console.log(image_classes);

	// Set listener to button click
	button_like.addEventListener("click", function(){
		
		// Update classes
		image_classes = image_like.className;
		console.log(image_classes);
		
		// Check if we want to like or dislike the author	
		// NOT liked
		if (image_classes == "fa-heart far"){
			
			// Ajax call to add like
			$.ajax({
					type: "POST",
					contentType: "application/json; charset=utf-8",
					url: "rest/like/"+id,
					success: addLikeUpdateCount,
					error: printError
			});
			

		
		}
		// liked
		if (image_classes == "fa-heart fas"){
			
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
	function printError(jqXHR, textStatus, errorThrown){
		console.log(jqXHR);
		console.log(textStatus);
		console.log(errorThrown);
	}
}
	
