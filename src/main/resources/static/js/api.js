var uri = "/pendownabook/"

function clear(){	
	$("#content").html(" ");
}

function getPublishingServices() {
	clear();
	var email = $("#emailuser").text();
	$.get(uri + "subscription/subscriptiondetails?email="+email,function(data,status){
		if(data.length>0){
			var genreList = " ";
			$.get(uri + "genre", function(data, status) {			
				for(var i = 0;i < data.length; i++)
				{
					genreList = genreList + "<option value=\""+ data[i]["id"] +"\">" + data[i]["name"] + "&nbsp;&nbsp;&nbsp;&nbsp;" + data[i]["description"] + "</option>";
				}
				
				var form = "<form class=\"form-horizontal shadow no-border\" " +
								"method=\"post\" " +
								"enctype=\"multipart/form-data\" " +
								"action=\"previewbook/createpreviewbook/" + email + "\">" +
				"<fieldset> " +
				"<legend><center>Upload Your Preview Book</center></legend>" +
				"<div class=\"form-group\">" +
					"<label for=\"previewBookTitle\" class=\"col-lg-2 control-label\">Preview Book Title:</label>" +
					"<div class=\"col-lg-10\">" +
						"<input type=\"text\"  class=\"form-control\" name=\"previewBookTitle\" id=\"previewBookTitle\" size=\"35\"   placeholder=\"Preview Book Title\">" +
					"</div>" +
				"</div> " +
				"<div class=\"form-group\"><label for=\"genre\" class=\"col-lg-2 control-label\">Genre:</label>" +
					"<div class=\"col-lg-10\">" +
						"<select class=\"form-control\" name=\"genre\" id=\"genre\" >" +
							"<option>Select Genre</option>" +
							genreList + 
						"</select>" +
					"</div>" +
				"</div> " +
				"<label for=\"uploadfile\" class=\"col-lg-2 control-label\">Upload:</label>" +
				"<div class=\"col-lg-10\">" +
					"<input type=\"file\"  class=\"form-control\" name=\"previewBookFile\" id=\"previewBookFile\" size=\"35\">" +
				"</div>" +
				"</div><br>" +
				"<div class=\"form-group\">" +
					"<div class=\"col-lg-10\">" +
						"<input type=\"submit\"  class=\"form-control btn-success\" value=\"Submit\" id=\"submitBtn\" size=\"35\">" +
					"</div> " +
				"</div> ";
				
				$("#content").html(form);
			});
		}
		else{
			$.get(uri + "service", function(data, status) {
				var service = "";
				var cardHead = "<div class=\"row\" style= \"margin-top:30px;margin-left:1%; height:200px;\">";
				var cardTail = "</div>";
				var publishing_service="Publishing Service";
					
				for (var i = 0; i < data.length; i++) {
					if((publishing_service==data[i]["serviceTitle"])){
						var serviceTitle = data[i]["serviceTitle"];
						var serviceDescription = data[i]["serviceDescription"];
						var servicePeriod = data[i]["servicePeriod"]
						var serviceCost = data[i]["serviceCost"]
						service = service + "<div class=\"card border-0 shadow text-center mr-2 card-subscription\"><form method=\"post\" " 
										  + "action=\"" + uri + "payment/plan/" + data[i]["id"] +"/" + email + "\"> <div class = \"card-header bg-dark \" style=\"color: #fff;\"> <strong>" + serviceTitle 
										  + "</strong></div>" + "<div class=\"card-body\"> <h5 class=\"card-title\">" + serviceDescription 
										  + "</h5> <h6 class=\"card-text\"> Price:" + serviceCost 
										  + " INR </h6> <input type=\"submit\" class=\"btn btn-success\" value=\"Select this plan\" /></form></div> </div>";
					}
				}
				service =cardHead + service + cardTail;
				$("#content").html(service);
			});
		}
	})	
}

function getProofreadingServices() {
	clear();
	var email = $("#emailuser").text();
	$.get(uri + "subscription/subscriptiondetails?email="+email,function(data,status){
		if(data.length>0){
			var form = "<form class=\"form-horizontal shadow no-border\"><fieldset> <legend><center>Upload Book for Proofreading</center></legend><label for=\"uploadfile\" class=\"col-lg-2 control-label\">Upload:</label> <div class=\"col-lg-10\"><input type=\"file\"  class=\"form-control\" name=\"uploadFile\" id=\"upload\" size=\"35\"></div></div> <br><div class=\"form-group\"> <div class=\"col-lg-10\"><input type=\"submit\"  class=\"form-control btn-success\" name=\"uploadFile\" id=\"submitBtn\" size=\"35\"></div> </div>";
			$("#content").html(form);
		}
		else{
			$.get(uri + "service", function(data, status) {
				var service = "";
				var cardHead = "<div class=\"row\" style= \"margin-top:30px; margin-left:1%; height:200px;\">";
				var cardTail = "</div>";
				var proofreading_service="Proofreading Service";	
				for (var i = 0; i < data.length; i++) {
					if((proofreading_service==data[i]["serviceTitle"])){
					var serviceTitle = data[i]["serviceTitle"];
					var serviceDescription = data[i]["serviceDescription"];
					var servicePeriod = data[i]["servicePeriod"]
					var serviceCost = data[i]["serviceCost"]
					service = service + "<div class=\"card border-0 shadow text-center mr-2 card-subscription\"> <div class = \"card-header bg-dark \" style=\"color: #fff;\"> <strong>" + serviceTitle + "</strong></div>" + "<div class=\"card-body\"> <h6 class=\"card-title\">"
							+ serviceDescription + "</h6> <h6 class=\"card-text\"> Price:" + serviceCost +" INR </h6> <a href=\"#\" class=\"btn\">Select this Plan</a> </div> </div>";
					}
				}
				service =cardHead + service + cardTail;
				$("#content").html(service);
			});
		}
	})
}

function getPreviewBooks(){
	clear();
	var email = $("#emailuser").text();
	$.get(uri + "previewbook/showpreviewbooks?email="+email,function(data,status){
		var previewBook = "";
		var status ="";
		var previewBookTableStart = "<table class=\"table text-center shadow\"> <thead class=\"thead-dark\"> <tr> <th>Title</th><th>Preview Book ID</th> <th>Genre</th> <th>Date of Upload</th> <th>Status</th> </tr> </thead><tbody>";
		var previewBookTableEnd = "</tbody></table>";
		if(data.length>0){
			for(var i=0;i <data.length;i++){
				var dateOfUpload = data[i]["dateOfUpload"];
				var genre = data[i]["genre"];
				var previewBookId = data[i]["id"];
				var title = data[i]["title"];
				previewBook = previewBook + "<tr><td>"+ title + "</td> <td>"+ previewBookId + "</td> <td>"+ genre["name"] + "</td> <td>"+ dateOfUpload + "</td></tr>";
	 		}		
		}
		else{
			previewBook = previewBook + "<tr style = \"text-align:center;\"><td colspan=\"5\"\"><center>No Data to Display<center></td></tr>";
		}
		previewBook = previewBookTableStart + previewBook + previewBookTableEnd;
		$("#content").html(previewBook);
	});
}

function getBooksForPurchase(){
	clear();
	var email = $("#emailuser").text();
	$.get(uri + "book/showAll", function(data, status) {
		var books = "";
		var cardHead = "<div class=\"row\" style= \"margin-top:30px; margin-left:1%;\">";
		var cardTail = "</div>";
		var img_string ="";
		for (var i = 0; i < data.length; i++) {
			var bookTitle = data[i]["bookTitle"];
			var genre = data[i]["genre"];
			var ISBN = data[i]["isbn"];
			var price = data[i]["price"];
			var publisher=data[i]["publisher"];
			books = books + "<div class=\"card border-3 shadow text-center mr-2 card-buybooks\"> <div class = \"card-header bg-light \" style=\"color: #000;\"> <strong>" + bookTitle + "</strong></div>" + "<div class=\"card-body\"> <h6 class=\"card-text\"> Price: " + price +" <i class=\"fa fa-rupee\"style=\"font-size:15px\"></i></h6> <h6 class=\"card-title\"> Genre: "
					+ genre["name"] + "</h6> <h6 class=\"card-text\"> Publisher: " + publisher["publisherFirm"] +" </h6><h6 class=\"card-text\"> ISBN:" + ISBN +" </h6> <a href=\"#\" class=\"btn btn-primary\">Buy This Book</a> </div> </div>";
			
		}
		books =cardHead + books + cardTail;
		$("#content").html(books);
	});
}

function getUser(){
	clear();
	var email = $("#emailuser").text();
	$.get(uri + "home/profile?email="+email, function(data, status){
		var user="";
		var firstName = data["firstName"];
		var lastName = data["lastName"];
		var contact = data["contact"];
		
		user =user+"<table class=\"table shadow\"><thead><th colspan=\"2\"><center>Profile Information<center></th></thead><tbody><tr><td><b>First Name:</b></td><td>"+firstName+"</td></tr><tr><td><b>Last Name: </b></td><td>"+lastName+"</td></tr><tr><td><b>Email: </b></td><td>"+email+"</td></tr><tr><td><b>Contact: </b> </td><td>"+contact+"</td></tr></tbody></table>";	
	$("#content").html(user);
	});
}

