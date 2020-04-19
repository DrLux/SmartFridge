var oggetto =
	{
		"name": "Jason",
		"url_img": "https://www.supermercato24.it/asset/smhd/28f2e/27ce9/a5c3d/2045729846_img.jpg",
		"category": "a",
		"expiry_date": "2020-04-18T22:00:00.000+0000"
	}

$(document).ready(function(){
	$("#Postbutton").click(function(){
		var str = $("#myInput").val();
		$.ajax({
			type: "POST",
			contentType : "application/json",
			url: str,
			data: JSON.stringify(oggetto),
			dataType : 'json',
			success: function(data, status){
				$("#resultCall").append(status);
				console.log("Data: " + data + "\nStatus: " + status);
				console.log(data);
				},
			});
	});
});


