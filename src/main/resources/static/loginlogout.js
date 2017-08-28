function nction() {
        window.location.replace("http://localhost:8080/user");
    }


function ncion() {
    if (confirm("Confirm logout!") == true) {
        localStorage.setItem('name1', '');
        localStorage.setItem('id1', '');
        window.location.replace("http://localhost:8080/user");

    }
}

function logilogo() {
    if(localStorage.getItem('name1') == "") {
        document.getElementById("loginn").style.visibility = "visible";
        document.getElementById("logoutt").style.visibility = "hidden";

    }
    else{
        document.getElementById("loginn").style.visibility = "hidden";
        document.getElementById("logoutt").style.visibility = "visible";
    }
}