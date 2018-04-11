$(document).ready(function () {

    $("form").submit(function () {
        var username = document.getElementById("inputUsername").value;
        var password = document.getElementById("inputPassword").value;
        var authParam = document.getElementById("Authorization");
        var credentials = username + ":" + password;
        var base64 = btoa(credentials);
        authParam.value = "Basic " + base64;

        return true;
    });
})
;
