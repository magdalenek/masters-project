var rootURL = "http://localhost:9051";

$(document).ready(function () {
    $("#formNotes").submit(function () {
        console.log("sending form");
        var id_first = window.location.search.substring(1).split("=");
        var id = id_first[1];
        var name_first = window.location.search.substring(2).split("=");
        var charityName = name_first[1];
        console.log(id);
        console.log(charityName);
        var notes = document.getElementById("notes").value;
        console.log(notes);

        $.ajax({
            url: rootURL + "api/charity/notes/" + id,
            type: 'PUT',
            dataType: "json",
            data: JSON.stringify({
                id: id,
                charityName: charityName,
                note: notes
            }),
            contentType: "application/json"
        }).complete(function (data, statusText, xhr) {
            console.log(data);

            if (data.status === 200) {
                console.log("success");
            } else {
                console.log(statusText + " ; " + xhr + data);
            }
        });
        console.log("end");
    });
});