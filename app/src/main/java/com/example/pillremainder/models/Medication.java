package com.example.pillremainder.models;

public class Medication{
    String medType;
    String pillName;
    String dose;
    int pillTotal;
    int frequency;

    public Medication() {
    }

    public Medication(String medType, String pillName, String dose, int pillTotal, int frequency) {

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

    public int getPillTotal() {
        return pillTotal;
    }

    public void setPillTotal(int pillTotal) {
        this.pillTotal = pillTotal;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
