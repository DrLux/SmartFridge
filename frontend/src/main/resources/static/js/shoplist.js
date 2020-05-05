function jsshoplist(){

	$.get( backend_url + shoplist_service +"/getItems", function( data ) {

		data = JSON.parse(data);
		var table = "";
		$.each(data, function (index, item) {

			table += '<tr id =itemId_' + item.shopitem.id + ' >';

			// Foto e nome Item
			table += '<td>';
			table += '<img src=' + item.shopitem.url_img + ' alt=' + item.shopitem.name + '>';
			table += "<a class='user-link'>" + item.shopitem.name + "</a>";
			table += '</td>';

			// Note item
			table += '<td>';
			table += item.shopitem.notes;
			table += '</td>';

			//Item Generation
			table += '<td class="text-center">';

			table += '<span class="label label-warning">'+item.shopitem.category+'</span>';

			/*
			if (item.shopitem.automatic_gen) {
				table += '<span class="label label-success">Manual</span>';
			} else {
				table += '<span class="label label-default">Automatic</span>';
			}
			*/
			table += '</td>';

			//Button 1
			table += '<td style="width: 20%;">';

			table += '<a href=' + item.delete_callback +' class="table-link">';
			table += '		<span class="fa-stack">\n        ';
			table += '			<i class="fa fa-square fa-stack-2x"></i>';
			table += '			<i class="fa fa-check fa-stack-1x fa-inverse"></i>';
			table += '	</span>';
			table += '</a>';

			//Button 3
			table += '<a  href=' + item.delete_callback +' class="table-link danger">';
			table += '			<span class="fa-stack">';
			table += '				<i class="fa fa-square fa-stack-2x"></i>';
			table += '				<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>';
			table += '			</span>';
			table += '		</a>';


			table += '</td>';

			//End each element
			table += '</tr>';
		});
		$("tbody").html(table);
	});



};

