function loginfuncId(gu){
    globalUser = gu;
}

function loginfuncName(gun){
    globalUserName = gun;
}

function getloginfuncId(){
    return globalUser;
}

function getloginfuncName(){
    return globalUserName;
}


/*


function logoutclick() {
    globalUser = 0;
    globalUserName = '';
    globalGroup = 0;
    document.getElementById("logo").innerHTML = "You are logged out.";

}
*/
