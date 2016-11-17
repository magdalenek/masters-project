var rootURL = "http://localhost:9051";

function findAll() {
    $.ajax({
        type: 'GET',
        url: rootURL + "/api/charity/all",
        dataType: "json", // data type of response
        success: success
    });
}

function success(e){

    var result="";
    $.each(e, function(index, value){
        result+= "<p>" + value.charityName + "</p>";
    })
    $('.result').html(result);
    console.log("Added");
}

function findById(id) {
    $.ajax({
        type: 'GET',
        url: rootURL + '/' + id,
        dataType: "json",
        success: function(data){
            renderDetails(data);
        }
    });
}


