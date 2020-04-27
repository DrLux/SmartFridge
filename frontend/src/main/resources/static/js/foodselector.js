var category;
var date;
var food;
var imgSrc;


function jsfoodselector() {

    $('#category').on('select2:select', function (e) {
        category = $('#category').select2('data')[0].text;
        check_foodselector();
    });

    $('#selection_food').on('select2:select', function (e) {
        food = $('#selection_food').select2('data');
        check_foodselector();
    });

    reset_foodselector();

    $( "#datepicker" ).datepicker({
        minDate: +1,
        dateFormat: 'dd-mm-yy',
        onSelect: function(dateText) {
            date = document.getElementById('datepicker').value;
            check_foodselector();
        }
    });

    $('#text_area').on('keypress',function(e) {
        if(e.which == 13) {
            imgSrc = document.getElementById('text_area').value;
            $('#food_img').attr('src', imgSrc);
            check_foodselector();
        }
    });

    $('#button_image').click(function(e) {
        imgSrc = document.getElementById('text_area').value;
        $('#food_img').attr('src', imgSrc);
        check_foodselector();
    });


};

function send_to_server(){
    if (category && date && food && imgSrc ){
        var splitted_date = date.split('-'); //0 = day, 1 month, 2 year


        var json_data = { "name":food[0].text,
            "url_img":imgSrc,
            "year":splitted_date[2],
            "month": String(parseInt(splitted_date[1])),
            "day": String(parseInt(splitted_date[0])),
            "category":category};

        //send to the server
        post_url = "http://localhost:8081/fridge/addFood";
        $.ajax({
            type: 'POST',
            url: post_url,
            data: JSON.stringify(json_data),
            success: function(response){ total_foods.add(response);},
            contentType: "application/json",
            dataType: 'json'
        });

        $("#send_button").prop("disabled", true);

        //add new food into foodselector
        var foods = total_foods.values().next().value;
        var newOption = new Option(food[0].text, foods.length, false, false);
        $('#selection_food').append(newOption).trigger('change');

        reset_foodselector();
        jsfoodselector();
    } else {
        alert("Fill all the forms before send to the server!");
    }
}

function check_foodselector() {
    if (category && date && food && imgSrc) {
        $("#send_button").prop("disabled", false);
    }
}

function reset_foodselector(){
    category = null;
    date = null;
    food = null;
    imgSrc = null;

    $("#category").val('Select food category').trigger('change');
    $("#selection_food").val('Insert food name').trigger('change');

    document.getElementById('datepicker').value = null;
    document.getElementById('text_area').value = null;
    $('#food_img').attr('src', "");
    $("#send_button").prop("disabled", true);

}



