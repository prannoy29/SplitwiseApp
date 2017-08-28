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
                document.getElementById("span3").innerHTML = response;
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
       var detaillist1 = [];
       $.ajax({
           url: _url,
           type: 'GET',
           dataType: "json",
           success: function (response) {
               if(response != null) {
                   detaillist.push("UserID"); detaillist1.push(response.userId);
                   detaillist.push("User Name "); detaillist1.push(response.name);
                   detaillist.push("Email ID "); detaillist1.push(response.emailId);
                   detaillist.push("Phone number "); detaillist1.push(response.phoneNumber);
                   detaillist.push("Age "); detaillist1.push(response.age);
                   var htmm = "<br /><table border=0.5 width=100%>";
                   for(j=0;j<5;j++)
                   {
                       htmm += "<tr><th>" + detaillist[j] + "</th><td>"+ detaillist1[j] +"</td></tr>";
                   }
                   htmm += "</tr></table>";
                   document.getElementById("span1").innerHTML = htmm;




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
    var t = "http://localhost:8080/group/findAllByUserId?userId=".concat(userid);
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
        var groupidlist = [];
        $.ajax({
            url: _url,
            type: 'GET',
            dataType: "json",
            success: function (response) {
                var grouplist = [];


                    for(var i = 0; i<response.length;i++){
                        detaillist.push(response[i].groupName+" ");
                        groupidlist.push(response[i].groupId+" ");
                        grouplist.push("<a target='_blank' href='http://localhost:8080/group?groupId=" +
                            response[i].groupId + "'>" + response[i].groupName + "</a>");
                    }

                    var a = detaillist.toString();
                //document.getElementById("span2").innerHTML = "User is present in : " + grouplist;


                    a = a.split(",") ;


                    if (detaillist.length > 0)/*
                        document.getElementById("span2").innerHTML = "User is present in : " + grouplist;
*/                  {
                        for ( var i=0; i<grouplist.length; i++) {
                            $('#ull').append('<li>' + detaillist[i] + '</li>');
                        }
                    }
                else document.getElementById("span2").innerHTML = "";

                callback(data);


            },
            error: function(){
                document.getElementById("span2").innerHTML = "";
            }
        });
    }
}