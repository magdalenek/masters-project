package models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection="charity")
public class Charity {

    @Id
    private String id;

    private String charityName;
    private String charityWebsiteName;
    private String charityContactName;
    private String charityTel;

    @Indexed
    private String charityEmail;
    private String registrationNumber;

    private String charityAddress; //change to Address object
    private String charityShortDescription;
    private String charityDescription;
    private int employeesNo;
    private int averageDonations;

    private ArrayList<String> categoryList;

    private String username;
    private String password;
    private boolean verified;
    private boolean verifiedEmail;
    private boolean verifiedData;
    private String adminNotes;
    private String token;

    public Charity(){}

    public Charity(String charityName, String charityContactName, String charityWebsiteName, String charityTel,
                   String charityEmail, String registrationNumber, String charityAddress, String charityShortDescription,
                   String charityDescription, int employeesNo, int averageDonations, ArrayList<String> categoryList,
                   String username, String password, boolean verified, boolean verifiedEmail, boolean verifiedData, String adminNotes, String token) {

        this.charityName = charityName;
        this.charityContactName = charityContactName;
        this.charityWebsiteName = charityWebsiteName;
        this.charityTel = charityTel;
        this.charityEmail = charityEmail;
        this.registrationNumber = registrationNumber;
        this.charityAddress = charityAddress;
        this.charityShortDescription = charityShortDescription;
        this.charityDescription = charityDescription;
        this.employeesNo = employeesNo;
        this.averageDonations = averageDonations;
        this.categoryList = categoryList;
        this.username = username;
        this.password = password;
        this.verified = verified;
        this.verifiedEmail = verifiedEmail;
        this.verifiedData = verifiedData;
        this.adminNotes = adminNotes;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCharityName() {
        return charityName;
    }

    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    public String getCharityWebsiteName() {
        return charityWebsiteName;
    }

    public void setCharityWebsiteName(String charityWebsiteName) {
        this.charityWebsiteName = charityWebsiteName;
    }

    public String getCharityContactName() {
        return charityContactName;
    }

    public void setCharityContactName(String charityContactName) {
        this.charityContactName = charityContactName;
    }

    public String getCharityTel() {
        return charityTel;
    }

    public void setCharityTel(String charityTel) {
        this.charityTel = charityTel;
    }

    public String getCharityEmail() {
        return charityEmail;
    }

    public void setCharityEmail(String charityEmail) {
        this.charityEmail = charityEmail;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCharityShortDescription() {
        return charityShortDescription;
    }

    public void setCharityShortDescription(String charityShortDescription) {
        this.charityShortDescription = charityShortDescription;
    }

    public ArrayList<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<String> categoryList) {
        this.categoryList = categoryList;
    }

    public String getCharityAddress() {
        return charityAddress;
    }

    public void setCharityAddress(String charityAddress) {
        this.charityAddress = charityAddress;
    }

    public String getCharityDescription() {
        return charityDescription;
    }

    public void setCharityDescription(String charityDescription) {
        this.charityDescription = charityDescription;
    }

    public int getEmployeesNo() {
        return employeesNo;
    }

    public void setEmployeesNo(int employeesNo) {
        this.employeesNo = employeesNo;
    }

    public int getAverageDonations() {
        return averageDonations;
    }

    public void setAverageDonations(int averageDonations) {
        this.averageDonations = averageDonations;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isVerifiedEmail() {
        return verifiedEmail;
    }

    public void setVerifiedEmail(boolean verifiedEmail) {
        this.verifiedEmail = verifiedEmail;
    }

    public boolean isVerifiedData() {
        return verifiedData;
    }

    public void setVerifiedData(boolean verifiedData) {
        this.verifiedData = verifiedData;
    }

    public String getNotes() {
        return adminNotes;
    }

    public void setNotes(String adminNotes) {
        this.adminNotes = adminNotes;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public String toString() {
        return "Charity{" +
                "id='" + id + '\'' +
                ", charityName='" + charityName + '\'' +
                ", charityWebsiteName='" + charityWebsiteName + '\'' +
                ", charityContactName='" + charityContactName + '\'' +
                ", charityTel='" + charityTel + '\'' +
                ", charityAddress='" + charityAddress + '\'' +
                ", charityShortDescription='" + charityShortDescription + '\'' +
                ", charityDescription='" + charityDescription + '\'' +
                '}';
    }