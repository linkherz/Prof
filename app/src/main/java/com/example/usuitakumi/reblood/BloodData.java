package com.example.usuitakumi.reblood;

import java.util.Date;

public class BloodData {
    private String pmiRegNo;
    private String bloodGroup;
    private String rhesus;
    private String donationCount;
    private Date lastDonationDate;
    private Date nextDonationDate;

    public BloodData(){

    }

    public BloodData(String pmiRegNo, String bloodGroup, String rhesus, String donationCount, Date lastDonationDate, Date nextDonationDate) {
        this.pmiRegNo = pmiRegNo;
        this.bloodGroup = bloodGroup;
        this.rhesus = rhesus;
        this.donationCount = donationCount;
        this.lastDonationDate = lastDonationDate;
        this.nextDonationDate = nextDonationDate;
    }

    public String getPmiRegNo() {
        return pmiRegNo;
    }

    public void setPmiRegNo(String pmiRegNo) {
        this.pmiRegNo = pmiRegNo;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getRhesus() {
        return rhesus;
    }

    public void setRhesus(String rhesus) {
        this.rhesus = rhesus;
    }

    public String getDonationCount() {
        return donationCount;
    }

    public void setDonationCount(String donationCount) {
        this.donationCount = donationCount;
    }

    public Date getLastDonationDate() {
        return lastDonationDate;
    }

    public void setLastDonationDate(Date lastDonationDate) {
        this.lastDonationDate = lastDonationDate;
    }

    public Date getNextDonationDate() {
        return nextDonationDate;
    }

    public void setNextDonationDate(Date nextDonationDate) {
        this.nextDonationDate = nextDonationDate;
    }
}
