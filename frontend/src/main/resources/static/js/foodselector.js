function jsfoodselector() {
    var prendi_url = 'https://api.github.com/search/repositories';

    $("#state").select2({
        tags: true,
        selectOnClose: true,
        placeholder: "Insert food name",
        disabled: false,
        allowClear: true,
        //templateResult: dove vare quando ha selezionato
        /*ajax: {
            url: prendi_url,
            dataType: 'json'
            // Additional AJAX parameters go here; see the end of this chapter for the full code of this example
        }*/

    });


    $("#category").select2({
        selectOnClose: true,
        placeholder: "Select food category",
        disabled: false,

    });

    //$("#state").prop("disabled", true);

    $( "#datepicker" ).datepicker({
        minDate: +1,
        dateFormat: 'dd-mm-yy',
    });

    $('#text_area').on('keypress',function(e) {
        if(e.which == 13) {
            var imgSrc = document.getElementById('text_area').value;
            $('#food_img').attr('src', imgSrc);
        }
    });

    $('#button_image').click(function(e) {
        var imgSrc = document.getElementById('text_area').value;
        $('#food_img').attr('src', imgSrc);
    });

};

