package com.example.restServiceHospital.api.model;

import jakarta.persistence.*;

@Entity
public class Hospital {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String hospitalName;
    private float hospitalLocationLat;
    private float hospitalLocationLong;
    private int numberOfSeats;

    public Hospital() {
    }

    public Hospital(String hospitalName, float hospitalLocationLat, float hospitalLocationLong) {
        this.hospitalName = hospitalName;
        this.hospitalLocationLat = hospitalLocationLat;
        this.hospitalLocationLong = hospitalLocationLong;
    }

    public Hospital(int id, String hospitalName, float hospitalLocationLat, float hospitalLocationLong)
    {
        this.id = id;
        this.hospitalName = hospitalName;
        this.hospitalLocationLat = hospitalLocationLat;
        this.hospitalLocationLong = hospitalLocationLong;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", hospitalName='" + hospitalName + '\'' +
                ", hospitalLocationLat=" + hospitalLocationLat +
                ", hospitalLocationLong=" + hospitalLocationLong +
                '}';
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public float getHospitalLocationLat() {
        return hospitalLocationLat;
    }

    public void setHospitalLocationLat(float hospitalLocationLat) {
        this.hospitalLocationLat = hospitalLocationLat;
    }

    public float getHospitalLocationLong() {
        return hospitalLocationLong;
    }

    public void setHospitalLocationLong(float hospitalLocationLong) {
        this.hospitalLocationLong = hospitalLocationLong;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}