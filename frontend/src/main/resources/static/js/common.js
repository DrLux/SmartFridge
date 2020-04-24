$(document).ready(function() {
    openFoodSelector();
});



function openFoodSelector() {
        $('#div_foodselector').show();
        closeShopList();
        closeCalendar();
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
    }

    function closeShopList() {
        $('#div_shoplist').hide();
    }

    function openCalendar() {
        $('#div_calendar').show();
        closeShopList();
        closeFoodSelector();
    }

    function closeCalendar() {
        $('#div_calendar').hide();
    }
