package com.example.usuitakumi.reblood;

import android.net.Uri;

import java.util.Date;

public class Users {
    //private String documentId;
    private String uid;
    private String displayName;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private boolean disabled;
    private boolean emailVerified;
    private String phoneNumber;
    private String photoURL;

    private BloodData bloodData;

    public Users() {

    }

    public Users(String uid, String displayName, String email, Date createdAt, Date updatedAt, boolean disabled, boolean emailVerified, String phoneNumber, String photoURL, BloodData bloodData) {
        this.uid = uid;
        this.displayName = displayName;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.disabled = disabled;
        this.emailVerified = emailVerified;
        this.phoneNumber = phoneNumber;
        this.photoURL = photoURL;
        this.bloodData = bloodData;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public BloodData getBloodData() {
        return bloodData;
    }

    public void setBloodData(BloodData bloodData) {
        this.bloodData = bloodData;
    }
}