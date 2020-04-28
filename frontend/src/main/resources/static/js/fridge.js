function jsfridge(category){
    url_temporaneo = "http://localhost:8081/api/food/getFoodPerCategory/"+category;
    var fridge_table = "";
    $.get( url_temporaneo, function( data ) {
        for (i = 0; i < data.length; i++) {
            var item = data[i];
            fridge_table += '<tr id =itemId_' + item.id + ' >';

            // Foto e nome Item
            fridge_table += '<td>';
            fridge_table += '<img src=' + item.url_img + ' alt=' + item.name + '>';
            fridge_table += "<a class='user-link'>" + item.name + "</a>";
            fridge_table += '</td>';

            // Note item
            fridge_table += '<td>';
            fridge_table += item.day + '-' + item.month + '-' + item.year;
            fridge_table += '</td>';

            //Item Generation
            fridge_table += '<td class="text-center">';
            fridge_table += '<span class="label label-primary">' + item.category + '</span>';
            fridge_table += '</td>';

            //Button 1
            fridge_table += '<td>';

            fridge_table += '<a href=' + 'url_callback' +' class="table-link">';
            fridge_table += '\t<span class="fa-stack" style="width: 50%;">\n';
            fridge_table += '		<i class="fa fa-square fa-stack-2x"></i>';
            fridge_table += '		<i class="to_shoplist_btn fa fa-list-ul fa-stack-1x fa-inverse"></i>'; //
            fridge_table += '	</span>';
            fridge_table += '</a>';


            fridge_table += '</td>'

            //End each element
            fridge_table += '</tr>';
        }
        $("#fridge_body").html(fridge_table);
    });

    // Delete an item of a list
    $(document.body).on("click", ".to_shoplist_btn", function (event) {
        event.preventDefault();
        var add_url = $(this).parent().parent().attr('href');
        $(this).parent().parent().remove();
        $.get( add_url, function( ) {
            alert("Added to shoplist!");
        });
    });

};



