$( document ).ready(function() {
	
	// SUBMIT FORM
    $("#customerForm").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPost();
	});
    
    
    function ajaxPost(){
    	
    	// PREPARE FORM DATA
    	var formData = {
    		name : $("#name").val(),
    		age :  $("#age").val()
    	}

    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "http://localhost:8081/api/food/create",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
					$("#postResultDiv").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>" +
												"Post Successfully! <br>" +
												"---> Customer's Info: Name = " +
												result.name + " ,Age = " + result.age + "</p>");
			},
			error : function(e) {
				$("#postResultDiv").html("<strong>Error</strong>");
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    	
    	// Reset FormData after Posting
    	resetData();

    }
    
    function resetData(){
    	$("#name").val("");
    	$("#age").val("");
    }
})