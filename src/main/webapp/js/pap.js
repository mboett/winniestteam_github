$(document).ready(function() {
	// Load data from local json
	$.getJSON("../json/papers.json", function(data) {
	// Table column filters
    $('#papers thead tr').clone(true).appendTo( '#papers thead' );
    $('#papers thead tr:eq(1) th').each( function (i) {
        var title = $(this).text();
        $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
        $( 'input', this ).on( 'keyup change', function () {
            if ( table.column(i).search() !== this.value ) {
                table
                    .column(i)
                    .search( this.value )
                    .draw();
            }
        } );
    } );
		// DataTables initialization
		var table = $('#papers').DataTable({
			"data": data.papers,
			"columns": [
							{
								"data": "title",
								"render": function(data, type, row, meta){
										data = '<a class="link" href="' + row.ee + '">' + data + '</a>';
										return data;
									}
							},
							{
								"data": "authors",
								"render": function(data, type, row, meta){
									let result = data;
									if(type === 'display'){
										result = "<p>";
										for (idx = 0; idx < data.length; idx++){
											if (data[idx]!=null) {
												result = result + '<a class="link" href="search-author?id='+row.ID[idx]+'">' + data[idx] + '</a> ';
											} else {
												result = result + '<a>None</a> ';
											}
											
										}
										result = result + "</p>"
									}
									return result;
								}
							},
      	{ "data": "year" },
      	{ "data": "mdate" }
      ],
			"orderCellsTop": true,
      "fixedHeader": true
		});
	});
});
