
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
                    nameslist.push(response[i].name);

                }
                var g  = nameslist.toString().replace(",","");
                if(g.length>0){

                    for ( var tt=0; tt<nameslist.length; tt++) {
                        $('#ulll').append('<li>' + nameslist[tt] + '</li>');
                    }}
                else
                    document.getElementById("sp").innerHTML = "No members";
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
        var splitlist1 = [];
        var splitlist2 = [];
        var splitlist3 = [];

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
                    {
                        $('#ulll2').append('<li>Rs. '+response[i].amount+" - "+ response[i].debtorName + " owes " + response[i].creditorName + '</li>');
                    }
                        }
                var g  = splitlist.toString();


                callback(data);


            },

        });
    }
}