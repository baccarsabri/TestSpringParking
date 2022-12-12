package com.example.demospring.Repository;

import com.example.demospring.Entite.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking,Integer> {
    public List<Parking> findAllByAdresse(String addresse);
}
