$( document ).ready(function() {

	// GET REQUEST
	$("#getAllCustomerId").click(function(event){
		event.preventDefault();
		ajaxGet();
	});

	// DO GET
	function ajaxGet(){
		$.ajax({
			type : "GET",
			url : "http://localhost:8081/api/food/get",
			success: function(result){
				$('#getResultDiv ul').empty();
				var custList = "";
				if (result.length == 0){
					$('#getResultDiv .list-group').append("Database is empty!")
				} else {
					jQuery.each(result, function (i, customer) {
						//var customer = "- index = " + i + ", id = " + customer.id + ", Name = " + customer.name + " Age = " + customer.age + "<br>";
						$('#getResultDiv .list-group').append(customer)
					});
				}
				console.log("Success: ", result);
			},
			error : function(e) {
				$("#getResultDiv").html("<strong>Error</strong>");
				console.log("ERROR: ", e);
			}
		});
	}
})