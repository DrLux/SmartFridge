$(document).ready(function() {
    openShopList();

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
        jsfridge();
    }

    function closeFridge() {
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
