function jsfridge(){

    $("#fridge_category").select2({
        selectOnClose: true,
        placeholder: "Select food category",
        disabled: false,

    });

    var fridge_data = [
        {"id":"0","name":"pomodori","url_img":"https://www.supermercato24.it/asset/smhd/28f2e/27ce9/a5c3d/2045729846_img.jpg","expiry_date":"01/02/2020","category": "latticini","toshoplist_callback":"url/deletebyid/0"},
        {"id":"0","name":"zucchine","url_img":"https://www.supermercato24.it/asset/smhd/73dab/3daf8/ec220/1271065111_img.jpg","expiry_date":"01/02/2020","category": "carne","toshoplist_callback":"url/deletebyid/0"},
    ];

    var fridge_table = "";
    $.each( fridge_data, function(index,item){
        fridge_table += '<tr id =itemId_'+ item.id +' >';

        // Foto e nome Item
        fridge_table += '<td>';
        fridge_table += '<img src=' + item.url_img + ' alt=' + item.name + '>'
        fridge_table += "<a class='user-link'>" + item.name + "</a>"
        fridge_table += '</td>';

        // Note item
        fridge_table += '<td>';
        fridge_table += item.expiry_date
        fridge_table += '</td>';

        //Item Generation
        fridge_table += '<td class="text-center">';
        fridge_table += '<span class="label label-primary">'+item.category+'</span>';
        fridge_table += '</td>';

        //Button 1
        fridge_table += '<td>';

        fridge_table += '<a href=' + item.toshoplist_callback +' class="table-link">';
        fridge_table += '\t<span class="fa-stack" style="width: 50%;">\n';
        fridge_table += '		<i class="fa fa-square fa-stack-2x"></i>';
        fridge_table += '		<i class="fa fa-list-ul fa-stack-1x fa-inverse"></i>';
        fridge_table += '	</span>';
        fridge_table += '</a>';


        fridge_table += '</td>'

        //End each element
        fridge_table += '</tr>';
    });
    console.log(fridge_table);
    $("tbody").html(fridge_table);


    // Delete an item of a list
    $(document.body).on("click", ".fa-trash-o", function (event) {
        console.log("in ajax");
        event.preventDefault();
        var href = $(this).parent().parent().attr("href");
        if(href) {
            //Remove item on screen
            var id_to_remove = '#itemId_'+href.slice(-1);
            $(id_to_remove).remove();

            //Remove item on server
            /*$.ajax({
                url: href,
                type: 'DELETE',
                success:  function(){
                    $(this).remove();
                    setMonthly(currentMonth, currentYear);
                }
            });
            */
        }
    });
};