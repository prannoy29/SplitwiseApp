function func(userid) {

    var l = "http://localhost:8080/user/totalExpense?userId=".concat(userid);
    test2(l);

    function test2(_url) {
        gettotalexpense(_url, function (d) {
            return d;
        });
    }

    function gettotalexpense(_url, callback) {
        var data;
        var p = 0;
        var totexpense;
        $.ajax({
            url: _url,
            type: 'GET',
            dataType: "json",
            success: function (response) {
                document.getElementById("span3").innerHTML = "<b>Debt is :<h3>"+ response +"</h3></b>";
                callback(data);


            },
            error: function(){

            }
        });
    }

}


function func1(userid) {
   var t = "http://localhost:8080/user/findById?id=".concat(userid);
   test111(t);

   function test111(_url) {
       getuserdetailz(_url, function (d) {
           return d;
       });
   }

  function getuserdetailz(_url, callback) {
       var data;
       var p = 0;
       var detaillist = [];
       $.ajax({
           url: _url,
           type: 'GET',
           dataType: "json",
           success: function (response) {
               if(response != null) {
                   detaillist.push("<b>UserID is : <h3>" + response.userId + "</h3></b><br />");
                   detaillist.push("<b>User Name is : <h3>" + response.name + "</h3></b><br />");
                   detaillist.push("<b>Email ID is :  <h3>" + response.emailId + "</h3></b><br />");
                   detaillist.push("<b>Phone number is : <h3>" + response.phoneNumber + "</h3></b><br />");
                   detaillist.push("<b>Age is :  <h3>" + response.age + "</h3></b><br />");
                   var a = detaillist.toString().replace(',', '');
                   a = a.replace(',', '');
                   if (detaillist.length > 0)
                       document.getElementById("span1").innerHTML = a;
               }
               else document.getElementById("span1").innerHTML = "No user present";

               callback(data);


           },
           error: function(){
               document.getElementById("span1").innerHTML = "Error, no user accessible.";
           }
       });
   }
}


function func2(userid) {
    var t = "http://localhost:8080//group/findAllByUserId?userId=".concat(userid);
    test112(t);

    function test112(_url) {
        getuserdetailzz(_url, function (d) {
            return d;
        });
    }

    function getuserdetailzz(_url, callback) {
        var data;
        var p = 0;
        var detaillist = [];
        $.ajax({
            url: _url,
            type: 'GET',
            dataType: "json",
            success: function (response) {

                    for(var i = 0; i<response.length;i++){
                        detaillist.push(response[i].groupName+" ");
                    }
                    var a = detaillist.toString();
                    a = a.replace(',', '');
                    if (a.length > 0)
                        document.getElementById("span2").innerHTML = "User is present in : <h3>" + a + "</h3>";

                else document.getElementById("span2").innerHTML = "";

                callback(data);


            },
            error: function(){
                document.getElementById("span2").innerHTML = "";
            }
        });
    }
}