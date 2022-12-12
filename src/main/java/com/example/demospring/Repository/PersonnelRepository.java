package com.example.demospring.Repository;

import com.example.demospring.Entite.Personnel;
import com.example.demospring.Entite.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel,Integer> {


}
