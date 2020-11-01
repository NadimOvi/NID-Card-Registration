package net.simplifiedcoding.retrofitandroidtutorial.NotNeeded;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OurDataSet {

    Json json;

    public Json getJson() {
        return json;
    }

    public void setJson(Json json) {
        this.json = json;
    }

    @SerializedName("UserName")
    @Expose
    private String userName;

    @SerializedName("FullNameEng")
    @Expose
    private String fullNameEng;
    @SerializedName("FullNameLocal")
    @Expose
    private String fullNameLocal;
    @SerializedName("DateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("FatherName")
    @Expose
    private String fatherName;
    @SerializedName("MotherName")
    @Expose
    private String motherName;
    @SerializedName("NewNID")
    @Expose
    private String newNID;
    @SerializedName("OldNID")
    @Expose
    private String oldNID;
    @SerializedName("PasswordHash")
    @Expose
    private String passwordHash;
    @SerializedName("MacAddress")
    @Expose
    private String macAddress;


    public OurDataSet(String userName, String fullNameEng, String fullNameLocal, String dateOfBirth, String fatherName, String motherName, String newNID, String oldNID, String passwordHash, String macAddress) {
        this.userName = userName;
        this.fullNameEng = fullNameEng;
        this.fullNameLocal = fullNameLocal;
        this.dateOfBirth = dateOfBirth;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.newNID = newNID;
        this.oldNID = oldNID;
        this.passwordHash = passwordHash;
        this.macAddress = macAddress;
    }

    public OurDataSet(String userName, String passwordHash, String fullNameEng, String dateOfBirth, String newNID, String oldNID) {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullNameEng() {
        return fullNameEng;
    }

    public void setFullNameEng(String fullNameEng) {
        this.fullNameEng = fullNameEng;
    }

    public String getFullNameLocal() {
        return fullNameLocal;
    }

    public void setFullNameLocal(String fullNameLocal) {
        this.fullNameLocal = fullNameLocal;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getNewNID() {
        return newNID;
    }

    public void setNewNID(String newNID) {
        this.newNID = newNID;
    }

    public String getOldNID() {
        return oldNID;
    }

    public void setOldNID(String oldNID) {
        this.oldNID = oldNID;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

}
