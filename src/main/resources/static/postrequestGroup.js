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
            groupName : $("#groupname").val(),
            createdBy :  localStorage.getItem('name1')
        }

        // DO POST
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : url,
            data : JSON.stringify(formData),
            dataType : 'json',
            success : function() {
                if(result.status == "Done"){
                    $("#postResultDiv").html("<strong>" + "Saved Successfully! Group Name = "
                            + result.data.groupName + " which is created by " + result.data.createdBy + "</strong>");
                }
                console.log("Success");
            },/*
            error : function(e) {
                alert("Error!")
                console.log("ERROR: ");
            }*/
        });

        // Reset FormData after Posting
        resetData();

    }

    function resetData(){
        document.getElementById("w").innerHTML = "<h1>Saved</h1>";
        $("#groupname").val("");
        $("#name").val("");
    }
})