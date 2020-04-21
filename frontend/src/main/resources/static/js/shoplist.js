$(window).load( function() {
	var data = [
		{"name":"pomodori","url_img":"https://www.supermercato24.it/asset/smhd/28f2e/27ce9/a5c3d/2045729846_img.jpg","notes":"compra quelli gialli","automatic_gen":false,"buy_callback":"url","delete_callback":"url"},
		{"name":"zucchine","url_img":"https://www.supermercato24.it/asset/smhd/73dab/3daf8/ec220/1271065111_img.jpg","notes":"compra quelli gialli","automatic_gen":true,"buy_callback":"url","delete_callback":"url"},
	];

	var table = "";
	$.each( data, function(index,item){
		table += '<tr>';

		// Foto e nome Item
			table += '<td>';
				table += '<img src=' + item.url_img + ' alt=' + item.name + '>'
				table += "<a class='user-link'>" + item.name + "</a>"
			table += '</td>';

		// Note item
		table += '<td>';
			table += item.notes
		table += '</td>';

		//Item Generation
		table += '<td class="text-center">';
		if (item.automatic_gen){
			table += '<span class="label label-success">Manual</span>'
		} else {
			table += '<span class="label label-default">Automatic</span>'
		}
		table += '</td>';

		//Button 1
		table += '<td style="width: 20%;">';

		table += '<a href=' + item.buy_callback +' class="table-link">';
		table += '\t<span class="fa-stack">\n        ';
		table += '		<i class="fa fa-square fa-stack-2x"></i>';
		table += '		<i class="fa fa-check fa-stack-1x fa-inverse"></i>';
		table += '	</span>';
		table += '</a>';

		//Button 3
		table += '	<a href=' + item.delete_callback +' class="table-link danger">';
		table += '		<span class="fa-stack">';
		table += '			<i class="fa fa-square fa-stack-2x"></i>';
		table += '			<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>';
		table += '		</span>';
		table += '	</a>';

		table += '</td>'

		//End each element
		table += '</tr>';
	});
	$("tbody").html(table);

});