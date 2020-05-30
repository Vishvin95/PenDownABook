var uri = "/pendownabook/"
function clear(){
	$("#content").html("");
}	
function reviewBook(){
	clear();
	var email = $("#emailuser").text();
	console.log(email);
	var review="";
	var tableHead="<center>" +
						"<h3>" + "Available Books For Review" + "</h3>" +
					"</center> " +
					"<table class=\"table table-striped text-center\">" +
						"<tr>" +
							"<th>Book Title</th>" +
							"<th>Genre</th>" +
							"<th>Author</th>" +
							"<th>Download Preview</th>" +
							"<th>Current Status</th>" +
							"<th>Update Status</th>" +
						"</tr>";
	var tableTail="</table>";
	$.get(uri + "reviewstatus",function(reviewData,status){				
		
		$.get(uri + "previewbook/"+email,function(data,status){						
			for(i=0;i<data.length;i++)
			{
				var previewBookId = data[i][0];
				var title = data[i][3];
				var genre = data[i][6];
				var firstName = data[i][4];
				var lastName = data[i][5];
				var pdfPath = data[i][2];
				var reviewStatus= data[i][8];
				if(reviewStatus==null){
					reviewStatus="UPLOADED";
				}
				else
				{
					for(var j = 0;j < reviewData.length; j++)
					{
						if(reviewData[j]["id"]==reviewStatus)
							reviewStatus = reviewData[j]["name"];
					}
				}				
				
				var reviewStatusList= " ";
				var flag = false;
				for(var j = 0;j < reviewData.length; j++)
				{
					if(reviewStatus==reviewData[j]["name"])
					{
						flag = true;
						continue;
					}
					
					if(flag)
					{
						reviewStatusList = reviewStatusList + "<option value=\""+ 
						reviewData[j]["id"] +"\">" + 
						reviewData[j]["name"] + "&nbsp;&nbsp;&nbsp;&nbsp;" + 
						reviewData[j]["description"] + "</option>";
					}					
				}
				
				review = review + 
				"<tr>" +
					"<td>"+title+"</td>"+
					"<td>"+genre+"</td>"+
					"<td>"+firstName +" "+lastName+"</td>"+
					"<td><a href=\"previewbook/download/"+pdfPath+"\"> <i class=\"fa fa-fw fa-file-pdf-o \" style=\"font-size:24px\"></i>  </a></td>"+
					"<td>"+reviewStatus+"</td>"+
					"<td>" + 
						"<form method=\"post\" action=\"previewbook/createreview/" + email +"/" + previewBookId + "\">" +
							"<select class=\"form-control\" name = \"reviewstatus\" id=\"reviewstatus\"> " +
							"<option>Select Status</option>" +reviewStatusList+"</select>"+
						"<center><input type=\"submit\"  class=\"form-control btn-success text-center\" value=\"Submit\" id=\"submitBtn\" size=\"35\"></center></form>" +
					"</td>" +
				"</tr>"; 
			}
			review= tableHead +review+ tableTail;
			$("#content").html(review);
		});
	});
}	