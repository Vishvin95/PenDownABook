var uri = "/pendownabook/"
	
function reviewBook(){
	var email = $("#emailuser").text();
	console.log(email);
	var review="";
	var tableHead="<center>" +
					 "<em>" +
						"<h3>" + "Available Books For Review" + "</h3>" +
					 "</em>" +
					"</center> " +
					"<table class=\"table table-striped text-center\">" +
						"<tr>" +
							"<th>Book Title</th>" +
							"<th>Genre</th>" +
							"<th>Author</th>" +
							"<th>Download Preview</th>" +
							"<th>Status</th>" +
							"<th>Save</th>" +
						"</tr>";
	var tableTail="</table>";
	$.get(uri + "reviewstatus",function(reviewData,status){
		console.log(reviewData);
		var reviewStatusList= " ";
		for(var i = 0;i < reviewData.length; i++)
		{
			reviewStatusList = reviewStatusList + "<option value=\""+ 
			reviewData[i]["id"] +"\">" + 
			reviewData[i]["name"] + "&nbsp;&nbsp;&nbsp;&nbsp;" + 
			reviewData[i]["description"] + "</option>";
		}
		
		$.get(uri + "previewbook/"+email,function(data,status){			
			for(i=0;i<data.length;i++){
				var previewBookId = data[i][0];
				var title = data[i][3];
				var genre = data[i][6];
				var firstName = data[i][4];
				var lastName = data[i][5];
				var pdfPath = data[i][2];
				var reviewStatus= data[i][8];
					if(reviewStatus==null){
						reviewStatus=reviewStatus+"Uploaded";
					}
				review = review + 
				"<tr>" +
					"<td>"+title+"</td>"+
					"<td>"+genre+"</td>"+
					"<td>"+firstName +" "+lastName+"</td>"+
					"<td><a href=\""+pdfPath+"\"> <i class=\"fa fa-fw fa-file-pdf-o \" style=\"font-size:24px\"></i>  </a></td>"+
					"<td>" + 
						"<form method=\"post\" action=\"previewbook/createreview/" + email +"/" + previewBookId + "\">" +
							"<select class=\"form-control\" name = \"reviewstatus\" id=\"reviewstatus\"> " +
							"<option>Select Status</option>" +reviewStatusList+"</select>"+
						"<input type=\"submit\"  class=\"form-control btn-success\" value=\"Submit\" id=\"submitBtn\" size=\"35\"></form>" +
					"</td>" +
				"</tr>"; 
			}
			review= tableHead +review+ tableTail;
			$("#content").html(review);
		});
	});
}	