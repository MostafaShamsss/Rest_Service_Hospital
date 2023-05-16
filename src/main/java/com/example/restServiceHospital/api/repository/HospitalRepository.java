package com.example.restServiceHospital.api.repository;

import com.example.restServiceHospital.api.model.Hospital;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalRepository extends CrudRepository<Hospital, Integer> {
    @Query("SELECT d FROM Hospital d")
    List<Hospital> getAllHospitals();
    Hospital findHospitalById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO hospital(hospital_name, hospital_location_lat, hospital_location_long, number_of_seats) VALUES (:hospitalName, :hospitalLocationLat, :hospitalLocationLong, :numberOfSeats)", nativeQuery = true)
    void insertData(@Param("hospitalName") String hospitalName, @Param("hospitalLocationLat") float hospitalLocationLat, @Param("hospitalLocationLong") float hospitalLocationLong, @Param("numberOfSeats") int numberOfSeats);

    @Query("SELECT h FROM Hospital h WHERE h.numberOfSeats > 0")
    List<Hospital> findAvailableHospitals();

    @Modifying
    @Query("UPDATE Hospital h SET h.numberOfSeats = :numberOfSeats WHERE h.id = :id")
    @Transactional
    int updateHospitalSeats(@Param("id") Integer id, @Param("numberOfSeats") Integer numberOfSeats);

}

