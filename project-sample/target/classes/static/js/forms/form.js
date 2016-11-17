var rootURL = "http://localhost:9051";

$(document).ready(function () {
    $("form").submit(function () {
        console.log("sending form");

        var charityName = document.getElementById("charityName").value;
        var charityWebsiteName = document.getElementById("charityWebsiteName").value;
        var charityContactName = document.getElementById("charityContactName").value;
        var charityTel = document.getElementById("charityTel").value;
        var charityAddress = document.getElementById("charityAddress").value;
        var charityShortDescription = document.getElementById("charityShortDescription").value;
        var charityDescription = document.getElementById("charityDescription").value;
        var averageDonations = document.getElementById("averageDonations").value;
        var employeesNo = document.getElementById("employeesNo").value;
        var charityEmail = document.getElementById("charityEmail").value;
        var registrationNumber = document.getElementById("registrationNumber").value;
        var categoryList = document.getElementById("categoryList").value.split( /,\s*/ );

        $.ajax({
            url: rootURL + "/api/charity",
            type: 'POST',
            dataType: "json",
            data: JSON.stringify({
                charityName: charityName,
                charityWebsiteName: charityWebsiteName,
                charityContactName: charityContactName,
                charityTel: charityTel,
                charityAddress: charityAddress,
                charityEmail: charityEmail,
                charityShortDescription: charityShortDescription,
                charityDescription: charityDescription,
                averageDonations : averageDonations,
                employeesNo: employeesNo,
                categoryList : categoryList,
                registrationNumber: registrationNumber
            }),
            contentType: "application/json"
        }).complete(function (data, statusText, xhr) {
            if (data.status === 200) {
            }
            alert("Great news: Your Charity was submitted for a review! We will be back with you within next 24 hours!");
        });
    });
});
