package com.example.pillremainder.models;

public class Medication{

    String medType;
    String pillName;
    String dose;
    String pillTotal;
    String frequency;

    public Medication() {
    }

    public Medication(String medType, String pillName, String dose, String pillTotal, String frequency) {

        this.medType = medType;
        this.pillName = pillName;
        this.dose = dose;
        this.pillTotal = pillTotal;
        this.frequency = frequency;
    }

    public String getMedType() {
        return medType;
    }

    public void setMedType(String medType) {
        this.medType = medType;
    }

    public String getPillName() {
        return pillName;
    }

    public void setPillName(String pillName) {
        this.pillName = pillName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getPillTotal() {
        return pillTotal;
    }

    public void setPillTotal(String pillTotal) {
        this.pillTotal = pillTotal;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
