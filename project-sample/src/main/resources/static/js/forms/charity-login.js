var rootURL = "http://localhost:9051";

function myFunction() {

    console.log("sending login for charity");

    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    $.ajax({
        url: rootURL + "/admin/login",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            username : username,
            password : password
        })
    }).complete(function (data, statusText, xhr) {
        if (data.status === 200) {
            var token = data.responseText;
            var charid = "123";

            console.log("received token: " + token);

            document.cookie = "AA_TOKEN=" + token + ";";

            var destination = rootURL + "/admin/" + charid;

            var redirect = window.open(destination).focus();

            redirect.location;

        }

        if(data.status == 405) {
            alert("incorrect details, try again.");
        }
    });

}
