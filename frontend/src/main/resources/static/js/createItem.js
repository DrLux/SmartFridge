/*
var item_category;
var item_food;
var item_imgSrc;
var item_note;
 */

function jscreateItem(){

    $('#newitem_category').on('select2:select', function (e) {
        item_category = $('#newitem_category').select2('data')[0].text;
        check_createitem();
    });

    $('#newitem_selection_food').on('select2:select', function (e) {
        item_food = $('#newitem_selection_food').select2('data')[0].text;
        check_createitem();
    });

    reset_checkitem();

    $('#item_img_text_area').on('keypress',function(e) {
        if(e.which == 13) {
            item_imgSrc = document.getElementById('item_img_text_area').value;
            $('#item_food_img').attr('src', item_imgSrc);
            check_createitem();
        }
    });

    $('#item_button_image').click(function(e) {
        item_imgSrc = document.getElementById('item_img_text_area').value;
        $('#item_food_img').attr('src', item_imgSrc);
        check_createitem();
    });

    $('#note_text_area').on('keypress',function(e) {
        if(e.which == 13) {
            item_note = document.getElementById('note_text_area').value;
            check_createitem();
        }
    });

    $('#button_note').click(function(e) {
        item_note = document.getElementById('note_text_area').value;
        check_createitem();
    });

};

function send_newitem_to_server(){
    if (item_category && item_note && item_food && item_imgSrc){

        var json_data = {
            "name":item_food,
            "url_img":item_imgSrc,
            "notes":item_note,
            "category":item_category};

        //send to the server
        post_url = backend_url + shoplist_service +"/addItem";
        $.ajax({
            type: 'POST',
            url: post_url,
            data: JSON.stringify(json_data),
            success: function(response){ console.log(response);},
            contentType: "application/json",
            dataType: 'json'
        });



        $("#send_newitem_button").prop("disabled", true);

        jscreateItem();
    } else {
        alert("Fill all the forms before send to the server!");
    }
}

function check_createitem() {
    if (item_category && item_note && item_food && item_imgSrc) {
        $("#send_newitem_button").prop("disabled", false);
    }
}

function reset_checkitem(){
    item_category = null;
    item_note = null;
    item_food = null;
    item_imgSrc = null;


    $("#newitem_category").select2({ placeholder: 'Select food category' });
    $('#newitem_category').val('Select food category').trigger('change');
    $("#send_newitem_button").prop("disabled", true);


    $("#newitem_selection_food").select2({
        tags: true,
        selectOnClose: true,
        disabled: false,
        placeholder: 'Insert food name',
    });
    $('#newitem_selection_food').val('Select food category').trigger('change');

    document.getElementById('item_img_text_area').value = null;
    document.getElementById('note_text_area').value = null;
    $('#item_food_img').attr('src', "");

}