package com.example.restServiceHospital.api.controller;


import com.example.restServiceHospital.api.model.Hospital;
import com.example.restServiceHospital.api.model.LocationDTO;
import com.example.restServiceHospital.api.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HospitalController {
    @Autowired
    HospitalRepository hospitalRepository;

    @GetMapping("/hospital")
    public List<Hospital> getAll() {
        return (List<Hospital>) hospitalRepository.findAll();
    }

    @GetMapping("/hospital/{id}")
    public Optional<Hospital> getByID(@PathVariable String id) {
        Integer ID = Integer.parseInt(id);
        return hospitalRepository.findById(ID);

    }

    @PostMapping("/hospital/nearest")
    public Optional<Hospital> getByNearestHospital(@RequestBody LocationDTO location) {
        float hospitalLocationLat = location.getLatitude();
        float hospitalLocationLong = location.getLongitude();

        Hospital closestHospital = null;
        double minDistance = Double.MAX_VALUE;
        List<Hospital> hospitals = (List<Hospital>) hospitalRepository.findAvailableHospitals();

        for (Hospital hospital : hospitals) {
            double distance = calculateDistance(hospitalLocationLat, hospitalLocationLong,
                    hospital.getHospitalLocationLong(), hospital.getHospitalLocationLat());
            if (distance < minDistance) {
                minDistance = distance;
                closestHospital = hospital;
            }
        }

        return Optional.ofNullable(closestHospital);
    }

    @PostMapping("/hospital/confirm/{id}")
    public String confirmRequest(@PathVariable String id) {
        int rowsAffected = hospitalRepository.updateHospitalSeats(Integer.valueOf(id),hospitalRepository.findHospitalById(Integer.valueOf(id)).getNumberOfSeats()-1);
        if(rowsAffected > 0)
        {
            return "successful";
        }
        else return "Not successful";
    }

    @PostMapping("/hospital/cancel/{id}")
    public String cancelRequest(@PathVariable String id) {
        int rowsAffected = hospitalRepository.updateHospitalSeats(Integer.valueOf(id),hospitalRepository.findHospitalById(Integer.valueOf(id)).getNumberOfSeats()+1);
        if(rowsAffected > 0)
        {
            return "successful";
        }
        else return "Not successful";
    }


    /**
     * Create a new driver
     */
    @PostMapping("/hospital")
    public Hospital createHospital(@RequestBody Hospital newHospital) {
        return hospitalRepository.save(newHospital);
    }

    private float calculateDistance(double requestLat, double requestLong, double hospitalLong, double hospitalLat) {
        double distance = Math.sqrt(Math.pow((hospitalLong - requestLong),
                2) + Math.pow((hospitalLat - requestLat), 2));

        return (float) distance;

    }
}