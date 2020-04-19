$( document ).ready(function() {

    // Delete REQUEST
    $("#deleteAllCustomers").click(function(event){
        event.preventDefault();
        ajaxDelete();
    });

    // DO GET
    function ajaxDelete(){
        $.ajax({
            type : "DELETE",
            url : "http://localhost:8081/api/customers/delete",
            success: function(result){
                $('#deleteResultDiv').empty();
                $('#deleteResultDiv').append(result)
                console.log("Success: ", result);
            },
            error : function(e) {
                $("#deleteResultDiv").html("<strong>Error During Delete</strong>");
                console.log("ERROR: ", e);
            }
        });
    }
})