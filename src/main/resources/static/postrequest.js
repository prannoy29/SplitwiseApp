$( document ).ready(function() {
	
	var url = window.location;
	
	// SUBMIT FORM
    $("#contact").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPost();
	});
    
    
    function ajaxPost(){
        var lend = $("#lender").val().split(',');
        var bor = $("#borrower").val().split(',');
        var dateVar = $("#dot").val();
        var dsplit = dateVar.split("/");
        var d=new Date(dsplit[0],dsplit[1]-1,dsplit[2]);
    	
    	// PREPARE FORM DATA
    	var formData = {
            groupId : $("#groupId").val(),
    		description : $("#description").val(),
    		amount :  $("#amount").val(),
			lender: lend,
			borrower: bor,
			mop : $("#mop").val(),
    		dot : d,

    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : url,
			data : JSON.stringify(formData),
            dataType : 'json',
			success : function() {
                $("#post").html(result.status);

				if(result.status == "Done"){
					$("#post").html("<strong>" + "Post Successfully!</strong>");
				}
				console.log("Success");
			},
            done : function(e) {
                console.log("DONE");
            }
			/*error : function(e) {
				alert("Error!")
				console.log("ERROR: ");
			}*/
		});
    	
    	// Reset FormData after Posting
    	resetData();

    }
    
    function resetData(){
        document.getElementById("w").innerHTML = "<h1>Saved</h1>";
        $("#groupId").val("");
        $("#description").val("");
			$("#amount").val("");
            $("#lender").val("");
		$("#borrower").val("");
           $("#mop").val("");
        $("#dot").val("");

    }
})