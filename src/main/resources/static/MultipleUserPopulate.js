function myFunction() {
    var l = "http://localhost:8080/user/names";
    var namelist = test(l);

    var listt = namelist.split(',');
    document.getElementById('sp1').innerHTML = listt;
    multiple(listt);
    function test(_url) {
        var jsonnames = getNameArray(_url, function (d) {
            return d;
        });
        return jsonnames;
    }

    function getNameArray(_url, callback) {
        var data = 0;
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


    function multiple(items) {

        function split(val) {
            return val.split(/,\s*/);
        }

        function extractLast(term) {
            return split(term).pop();
        }

        $("#lender")
            .autocomplete({
                minLength: 0,
                source: function (request, response) {
                    response($.ui.autocomplete.filter(
                        items, extractLast(request.term)));
                },
                focus: function () {
                    return false;
                },
                select: function (event, ui) {
                    var terms = split(this.value);
                    // remove the current input
                    terms.pop();
                    // add the selected item
                    terms.push(ui.item.value);
                    // add placeholder to get the comma-and-space at the end
                    terms.push("");
                    this.value = terms.join(", ");
                    return false;
                }
            });
    };
}