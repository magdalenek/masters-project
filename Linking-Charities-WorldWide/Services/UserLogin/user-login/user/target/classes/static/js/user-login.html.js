var rootURL = "http://localhost:9052";

function myFunction() {

    console.log("sending login for user");

    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    $.ajax({
        url: rootURL + "/user/login",
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
            var userid = "123";

            console.log("received token: " + token);

            document.cookie = "AA_TOKEN=" + token + ";";

            var destination = rootURL + "/user/" + userid;

            var redirect = window.open(destination).focus();

            redirect.location;

        }

        if(data.status == 405) {
            alert("incorrect details, try again.");
        }
    });

}