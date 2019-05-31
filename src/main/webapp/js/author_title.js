$.getJSON("singleAuthor.json", function(data){
	var author_name = '<h1 class="title">' + data.author.name + '</h1>';
	$('<div class = "container">').append(author_name).appendTo('header');
});
