$(document).ready(function() {
	// Load data from local json
	$.getJSON("../json/authors.json", function(data) {
		// DataTables initialization
		var table = $('#authors').DataTable({
			"data": data.author,
			"columns": [
				{
					"data": "name",
					"render": 
						function(data, type, row, meta){
							if(type === 'display'){
								data = '<a class="link" href="search-author?id='+row.ID+'">' + data + '</a>';
							}
							return data;
						}
				}
			]
		});
	});
});