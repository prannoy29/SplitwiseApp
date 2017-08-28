
$( document ).ready(function() {
    var url = window.location;

    // GET REQUEST
    $ ("#login").focusin(function(){
        ajaxget();
    });

    // DO GET
    function ajaxget(){
        $.ajax({
            type : "GET",
            url : "http://localhost:8080/user/names",
            success: function(data){
                console.log("Success");
                var dataLen = data.length;
                for (i=0; i<dataLen; i++) {
                    $('#names').append('<option value="' + data[i] + '">' +  '</option>');
                }
            },
        });
    }
})