
function myFunction() {
    var x = document.getElementById('useridname').value;
    var l = "http://localhost:8080/user/totalExpense?userId=".concat(x);
    test(l);

    function test(_url) {
        var ee = getDebt(_url, function (d) {
            return d;
        });
    }

    function getDebt(_url, callback) {
        var data;
        var p = 0;
        $.ajax({
            url: _url,
            type: 'GET',
            success: function (response) {
                document.getElementById('sp').innerHTML = response;
                //console.log(response);
                callback(data);


            },
            error: function (e) {
                alert("Error!");
            }
        });
        p = document.getElementById('sp').innerHTML;

        return p;
    }
}