function loginsubmitclick() {

    var usernameinput = document.getElementById("login").value;
    ajaxget();

    $("#login-form").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxget();
    });
    // DO GET
    function ajaxget() {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/user/profile",
            data: {userName : usernameinput},
            dataType: 'json',
            success: function (data) {

                globalUser = data.userId;
                document.getElementById("loginSpan").innerHTML = "You are logged in.";

            },
            error: function () {
                document.getElementById("loginSpan").innerHTML = "Not a valid user!  You may want to register";
            }
        })
    }
}