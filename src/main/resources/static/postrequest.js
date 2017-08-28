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
        var datee=new Date();

        var lend1 =[];
        var bor1 = [];

        for (var e = 0; e < lend.length; e++){
            var l = "http://localhost:8080/user/findByEmailId?emailId=".concat(lend[e]);
            test211(l);

            function test211(_url) {
                getemailuserid(_url, function (d) {
                    return d;
                });
            }

            function getemailuserid(_url, callback) {
                var data;
                var p = 0;
                $.ajax({
                    url: _url,
                    type: 'GET',
                    dataType: "json",
                    success: function (response) {
                    	document.getElementById("lk").innerHTML = response;

                        lend[e] = document.getElementById("lk").innerHTML;
                        callback(data);
                    }
                });
            }
        }
        document.getElementById("hhh").innerHTML = lend[0];

        for (var e = 0; e < bor.length; e++){
            var l = "http://localhost:8080/user/findByEmailId?emailId=".concat(bor[e]);
            test212(l);

            function test212(_url) {
                getemailuserid1(_url, function (d) {
                    return d;
                });
            }

            function getemailuserid1(_url, callback) {
                var data;
                var p = 0;
                $.ajax({
                    url: _url,
                    type: 'GET',
                    dataType: "json",
                    success: function (response) {
                        bor[e] = response;
                        callback(data);
                    }
                });
            }
		}



        // PREPARE FORM DATA
    	var formData = {
            groupId : $("#groupId").val(),
    		description : $("#description").val(),
    		amount :  $("#amount").val(),
			lender: lend,
			borrower: bor,
			mop : $("#mop").val(),
    		dot : new Date()
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