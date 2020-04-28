function jsshoplist(){

	url_temporaneo = "http://localhost:8081/api/shopitems/getItems";
	$.get( url_temporaneo, function( data ) {

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
			table += '			<i id='+item.shopitem.id+' class="fa fa-check fa-stack-1x fa-inverse"></i>';
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

	$(document.body).on("click", ".fa-trash-o", function (event) {
		event.preventDefault();
		var delete_url = $(this).parent().parent().attr('href');
		//Remove item on screen
		$(this).parent().parent().parent().parent().remove();

		//fill foodselector forms
		$.ajax({
			url: delete_url,
			type: 'DELETE',
			success: function (response) {
				openFoodSelector();

				$("#button_fridge").prop("disabled", true);
				$("#button_calendar").prop("disabled", true);
				$("#button_shoplist").prop("disabled", true);
				$("#button_foodselector").prop("disabled", true);

				$('#div_foodselector').show();


				category = response.category;
				food = response.name;
				imgSrc = response.url_img;

				$("#selection_food").select2({ placeholder: food });
				$("#category").select2({ placeholder: category });


				$("#category").prop("disabled", true);
				$("#selection_food").prop("disabled", true);

				$('#button_image').prop("disabled", true);
				document.getElementById('text_area').value = imgSrc;
				$('#text_area').prop("disabled", true);

				$('#food_img').attr('src', imgSrc);

			}
		});
	});
};

