package Class;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Patient {
    public static final String Analysis = null;
    private String patientID;
    private String name;
    private String nationalID;
    private int age;
    private String bloodType;
    private String gender;
    private String address;
    private String contactNum;
    private String pictureURL;
    private String maritalStatus;
    private String email;
    private String dateOfBirth;
    private String occupation;
    private String emergencyContact;
    private String emergencyName;
    private String emergencyRel;

    public Patient() {}

    @JsonCreator
    public Patient(@JsonProperty("patientID") String patientID,
            @JsonProperty("name") String name,
            @JsonProperty("nationalID") String nationalID,
            @JsonProperty("age") int age,
            @JsonProperty("bloodType") String bloodType,
            @JsonProperty("gender") String gender,
            @JsonProperty("address") String address,
            @JsonProperty("contactNum") String contactNum,
            @JsonProperty("pictureURL") String pictureURL,
            @JsonProperty("maritalStatus") String maritalStatus,
            @JsonProperty("email") String email,
            @JsonProperty("dateOfBirth") String dateOfBirth,
            @JsonProperty("nationality") String nationality,
            @JsonProperty("occupation") String occupation,
            @JsonProperty("emergencyContact") String emergencyContact,
            @JsonProperty("emergencyName") String emergencyName,
            @JsonProperty("emergencyRel") String emergencyRel) {
        this.patientID = patientID;
        this.name = name;
        this.nationalID = nationalID;
        this.age = age;
        this.bloodType = bloodType;
        this.gender = gender;
        this.address = address;
        this.contactNum = contactNum;
        this.pictureURL = pictureURL;
        this.maritalStatus = maritalStatus;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.occupation = occupation;
        this.emergencyContact = emergencyContact;
        this.emergencyName = emergencyName;
        this.emergencyRel = emergencyRel;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public String getEmergencyRel() {
        return emergencyRel;
    }

    public void setEmergencyRel(String emergencyRel) {
        this.emergencyRel = emergencyRel;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

}