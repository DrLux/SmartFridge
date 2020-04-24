$(document).ready(function() {
    openFridge();
});

    function openFridge() {
        $('#div_fridge').show();
        closeShopList();
        closeCalendar();
        closeFoodSelector();
    }

    function closeFridge() {
        $('#div_fridge').hide();
    }

    function openFoodSelector() {
        $('#div_foodselector').show();
        closeShopList();
        closeCalendar();
        closeFridge();
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
    }

    function closeShopList() {
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
