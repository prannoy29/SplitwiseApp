
function getusers(grupid) {
    var l = "http://localhost:8080/group/findAllUsersByGroupId?groupId=".concat(grupid);
    test(l);

    function test(_url) {
        getuser(_url, function (d) {
            return d;
        });
    }

    function getuser(_url, callback) {
        var data;
        var p = 0;
        var nameslist = [];
        $.ajax({
            url: _url,
            type: 'GET',
            dataType: "json",
            success: function (response) {
                var datalen = response.length;
                for(var i=0;i<datalen;i++){
                    if(response[i] != null)
                    nameslist.push("<b>"+response[i].name+"</b>");

                }
                var g  = nameslist.toString();
                document.getElementById("sp").innerHTML = "Members of group are : "+g;
                callback(data);


            },
            error: function(){
                document.getElementById("sp").innerHTML = "no members";
            }
        });
    }

}


function getsplits(grupid) {
    var t = "http://localhost:8080/group/showSplits?groupId=".concat(grupid);
    test1(t);

    function test1(_url) {
        getsplit(_url, function (d) {
            return d;
        });
    }

    function getsplit(_url, callback) {
        var data;
        var p = 0;
        var splitlist = [];
        $.ajax({
            url: _url,
            type: 'GET',
            dataType: "json",
            success: function (response) {
                var datalen = response.length;
                for(var i=0;i<datalen;i++){

                    /*creditorId	2
                      creditorName	"shruti sinha"
                      debtorId	1
                      debtorName	"Apurva Singhal"
                      amount	500
                    */

                    if(response[i] != null && response[i].amount != 0)
                        splitlist.push("<b>"+response[i].debtorName + "</b>"+ " owes " + "<b>"+ response[i].creditorName+"</b>"+" the amount of "+response[i].amount + "<br />");

                }
                var g  = splitlist.toString();
                if(splitlist.length>0)
                document.getElementById("sp1").innerHTML = "Splits are : <br />"+g;
                else document.getElementById("sp1").innerHTML = "Nothing due in this group";

                callback(data);


            },
            error: function(){
                document.getElementById("sp1").innerHTML = "Nothing due in this group";
            }
        });
    }
}