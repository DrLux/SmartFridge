$(document).ready(function(){
	$("#Getbutton").click(function(){
		var str = $("#myInput").val();
		$.get(str, function(data, status){
			$("#resultCall").append(status);
			console.log("Data: " + data + "\nStatus: " + status);
			console.log(data);
		});
	});
});