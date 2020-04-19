$(document).ready(function(){
    $("#Delbutton").click(function(){
        var str = $("#myInput").val();
        $.del(str, function(data, status){
            $("#resultCall").append(status);
            console.log("Data: " + data + "\nStatus: " + status);
            console.log(data);
        });
    });
});