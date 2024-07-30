package com.example.pillremainder.models;

public class Medication extends User{
    String medType;
    String pillName;
    String dose;
    int pillTotal;
    int frequency;

    public Medication() {
    }

    public Medication(String id, String name, String email, String medType, String pillName, String dose, int pillTotal, int frequency) {
        super(id, name, email);
        this.medType = medType;
        this.pillName = pillName;
        this.dose = dose;
        this.pillTotal = pillTotal;
        this.frequency = frequency;
    }
}
