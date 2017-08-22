function clicked() {
    var s = $("#groupMembers").val();
    var userIDlist = s.split(',');
    console.log(s);
    var len = userIDlist.length;
    var userparam = '';
    var groupparam = $("#groupId").val();
    console.log(groupparam);
    for(var i=0; i<userIDlist.length;i++) userIDlist[i] = +userIDlist[i];
    var len = userIDlist.length;
    for(var count = 0; count < len; count++){
        userparam += "&userId=".concat(userIDlist[count]);
    }
    console.log(userIDlist[0]+0);
    var obj = {};
    /*$(userIDlist).each(function() {
        obj["&userId=" + this.id] = true;
    });*/
    $.extend(obj, {"groupId" : groupparam});
    $.extend(obj, {"userId" : userIDlist});/*
    $.each( userIDlist, function( index, value ){
        $.extend(obj, {"userId" : value});
    });*/
    var url = window.location+"?groupId="+groupparam+userparam;
document.getElementById("LL").innerHTML = url;


    // SUBMIT FORM
    $("#contact").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

        // DO POST
        $.ajax({
            type : "POST",
            url : url,
            data : obj,

            success : function(response) {
                alert("Success");
                console.log("Success");
                /*if(result.status == "Done"){
                    $("#postResultDiv").html("<strong>" + "Post Successfully! Customer's Info: FirstName = "
                            + result.data.firstname + " ,LastName = " + result.data.lastname + "</strong>");
                }else{
                    $("#postResultDiv").html("<strong>Error</strong>");
                }*/
            },
            error : function(e) {
                alert("Error!");
                console.log("Error");
                console.log(url);
                console.log(obj);
            }
        });

        // Reset FormData after Posting
        resetData();

    }

    function resetData(){
        $("#groupname").val("");
        $("#groupMembers").val("");
    }
}
