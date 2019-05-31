$.getJSON("singleAuthor.json", function(data){
    var file_data = '';
    var i;
    for (i = 0; i < data.author.publications.length; i++) {
      file_data += '<tr>';
      file_data +='<td><a class="link" href="'+ data.author.ee[i] +'"</a>'+ data.author.publications[i] +'</td>'
			file_data += '</tr>';
		}
	$('#file_table').append(file_data);
});
