$( document ).ready(function() {
	
	var url = window.location;
	
	// SUBMIT FORM
    $("#contact").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPost();
	});
    
    
    function ajaxPost(){
    	
    	// PREPARE FORM DATA
    	var formData = {
    		name : $("#name").val(),
    		emailId :  $("#emailId").val(),
			phoneNumber: $("#phoneNumber").val(),
			age: $("#age").val(),


    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : url,
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function() {
				/*if(result.status == "Done"){
					$("#postResultDiv").html("<strong>" + "Post Successfully! Customer's Info: FirstName = "
							+ result.data.firstname + " ,LastName = " + result.data.lastname + "</strong>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}*/
				console.log("Success");
			}
		});
        document.getElementById("w").innerHTML = "<h1>Saved</h1>";
    	// Reset FormData after Posting
    	resetData();

    }
    
    function resetData(){
        document.getElementById("w").innerHTML = "Saved";
    	$("#name").val("");
    	$("#emailId").val("");
        $("#phoneNumber").val("");
        $("#age").val("");
    }
})