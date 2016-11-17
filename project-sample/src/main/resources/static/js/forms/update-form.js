var rootURL = "http://localhost:9051";

$(document).ready(function () {

    $("form").submit(function () {
        console.log("sending form");

        var pathArray = window.location.search.substring(1);
        var charityName = document.getElementById("charityName").value;
        var charityWebsiteName = document.getElementById("charityWebsiteName").value;
        var charityContactName = document.getElementById("charityContactName").value;
        var charityTel = document.getElementById("charityTel").value;
        var charityAddress = document.getElementById("charityAddress").value;
        var charityShortDescription = document.getElementById("charityShortDescription").value;
        var charityDescription = document.getElementById("charityDescription").value;
        var averageDonations = document.getElementById("averageDonations").value;
        var employeesNo = document.getElementById("employeesNo").value;
        var categoryList = document.getElementById("categoryList").value.split(/,\s*/);

        $.ajax({
            url: rootURL + "api/charity/" + pathArray,
            type: 'PUT',
            dataType: "json",
            data: JSON.stringify({
                charityName: charityName,
                charityWebsiteName: charityWebsiteName,
                charityContactName: charityContactName,
                charityTel: charityTel,
                charityAddress: charityAddress,
                charityShortDescription: charityShortDescription,
                charityDescription: charityDescription,
                averageDonations: averageDonations,
                employeesNo: employeesNo,
                categoryList: categoryList
            }),
            contentType: "application/json"
        }).complete(function (data, statusText, xhr) {
            if (data.status === 200) {
                console.log("success");
            }
        });
        console.log("end");
    });
});