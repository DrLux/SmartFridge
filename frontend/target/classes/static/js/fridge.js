function jsfridge(category){
    var fridge_table = "";
    $.get( backend_url + fridge_service+"/getFoodPerCategory/"+category, function( data ) {
        data = JSON.parse(data);

        $.each(data, function (i, item) {
            fridge_table += '<tr id =itemId_' + item.food.id + ' >';

            // Foto e nome Item
            fridge_table += '<td>';
            fridge_table += '<img src=' + item.food.url_img + ' alt=' + item.food.name + '>';
            fridge_table += "<a class='user-link'>" + item.food.name + "</a>";
            fridge_table += '</td>';

            // Note item
            fridge_table += '<td>';
            fridge_table += item.food.day + '-' + item.food.month + '-' + item.food.year;
            fridge_table += '</td>';

            //Item Generation
            fridge_table += '<td class="text-center">';
            fridge_table += '<span class="label label-primary">' + item.food.category + '</span>';
            fridge_table += '</td>';

            //Button 1
            fridge_table += '<td style="width: 30%;">';

            fridge_table += '<a href=' + item.to_shoplist_callback +' class="table-link">';
            fridge_table += '\t<span class="fa-stack" style="width: 50%;">\n';
            fridge_table += '		<i class="fa fa-square fa-stack-2x" style="color: #ffc107;"></i>';
            fridge_table += '		<i class="to_shoplist_btn fa fa-list-ul fa-stack-1x fa-inverse"></i>'; //
            fridge_table += '	</span>';
            fridge_table += '</a>';

            //Button 3
            fridge_table += '<a  href=' + item.delete_callback +' class="table-link danger">';
            fridge_table += '			<span class="fa-stack">';
            fridge_table += '				<i class="fa fa-square fa-stack-2x"></i>';
            fridge_table += '				<i class="to_shop_after_del fa fa-trash-o fa-stack-1x fa-inverse"></i>';
            fridge_table += '			</span>';
            fridge_table += '		</a>';


            fridge_table += '</td>'

            //End each element
            fridge_table += '</tr>';
        });
        $("#fridge_body").html(fridge_table);

    });

};



