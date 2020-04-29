var first_call;
var total_foods = new Set();
var category;
var date;
var food;
var imgSrc;

$(document).ready(function() {

    $.get( "http://localhost:8081/api/firstCall", function( data ) {
        first_call = JSON.parse(data);
        $.get( "http://localhost:8081/api/food/getAllFood", function( foods,status ) {
            foods.forEach(item => total_foods.add(item));

            $("#category").select2({
                selectOnClose: true,
                disabled: false,
                placeholder: "Select food category",
                //allowClear: true,
            });

            $("#selection_food").select2({
                tags: true,
                selectOnClose: true,
                disabled: false,
                placeholder: 'Insert food name',
            });

            $("#fridge_category").select2({
                selectOnClose: true,
                placeholder: "Select food category",
                disabled: false,
            });

            $.each(first_call.Categories, function( index, value ) {
                var select_food_option = new Option(value, index, false, false);
                $('#category').append(select_food_option).trigger('change');
                var fridge_category_option = new Option(value, index, false, false);
                $('#fridge_category').append(fridge_category_option).trigger('change');
            });

            $('#fridge_category').on('select2:select', function (e) {
                category = $('#fridge_category').select2('data')[0].text;
                jsfridge(category);
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


            //create food selector items
            var it = total_foods.values()
            var data = it.next();
            var idx = 0;
            while (!data.done){
                var newOption = new Option(data.value.name, idx, false, false);
                $('#selection_food').append(newOption).trigger('change');
                data = it.next();
                idx++;
            }

            $(document.body).on("click", ".fa-trash-o", function (event) {
                event.preventDefault();
                var delete_url = $(this).parent().parent().attr('href');
                //Remove item on screen
                $(this).parent().parent().parent().parent().remove();

                //fill foodselector forms
                $.ajax({
                    url: delete_url,
                    type: 'DELETE',
                });
            })

            $(document.body).on("click", ".fa-check", function (event) {
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

                        $("#selection_food").select2({
                            tags: true,
                            selectOnClose: true,
                            disabled: false,
                            placeholder: food,
                        });
                        $('#selection_food').val(food).trigger('change');

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

            openFridge();
        });
    });



    $('#mycalendar').monthly({
        mode: 'event',
        jsonUrl: 'http://localhost:8081/api/event/getEvents/{year}/{month}',
        dataType: 'json',
        xmlUrl: 'events.xml'
    });
});

    function openFridge() {
        $('#div_fridge').show();
        closeShopList();
        closeCalendar();
        closeFoodSelector();
        jsfridge("all");
    }

    function closeFridge() {
        //$("#fridge_category").val('Select food category').trigger('change');
        $('#fridge_category').val(null).trigger('change');
        $('#div_fridge').hide();
    }

    function openFoodSelector() {
        $('#div_foodselector').show();
        closeShopList();
        closeCalendar();
        closeFridge();
        jsfoodselector();
        //$('#button_fridge').addClass("enable");
    }

    function closeFoodSelector() {
        $('#div_foodselector').hide();
        //$('#button_fridge').addClass("disabled");
    }

    function openShopList() {
        $('#div_shoplist').show();
        closeCalendar();
        closeFoodSelector();
        closeFridge();
        jsshoplist();
    }

    function closeShopList() {
        $("tbody").html("");
        $('#div_shoplist').hide();
    }

    function openCalendar() {
        $('#div_calendar').show();
        closeShopList();
        closeFoodSelector();
        closeFridge();
    }

    function closeCalendar() {
        $('#div_calendar').hide();
    }
