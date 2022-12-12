package com.example.demospring.Controller;

import com.example.demospring.Entite.Parking;
import com.example.demospring.Entite.Personnel;
import com.example.demospring.Entite.Zone;
import com.example.demospring.Repository.ParkingRepository;
import com.example.demospring.Repository.PersonnelRepository;
import com.example.demospring.Repository.ZoneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
@RequestMapping("parking")

public class RestController {
    @Autowired
    ParkingRepository parkingRepository;
    @Autowired
    ZoneRepository zoneRepository;
    @Autowired
    PersonnelRepository personnelRepository;

    @PostMapping("addPersonnel")
    public Personnel ajouterPersonnel(@RequestBody Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    @PostMapping("addParking")
    public void ajouterPersonnel(@RequestBody Parking parking) {
        parkingRepository.save(parking);
    }

    @PostMapping("affecterPersonnelZone/{idZone}/{idGarde}")
    public void affecterPersonnelZone(@PathVariable int idZone, @PathVariable int idGarde) {
        Personnel personnel = personnelRepository.findById(idGarde).orElse(null);
        Zone zone = zoneRepository.findById(idZone).orElse(null);
        personnel.setZone(zone);
        personnelRepository.save(personnel);

    }

    @GetMapping("pp")
    public Parking findAll() {
        return parkingRepository.findById(1).orElse(null);
    }

    @PostMapping("getPersoParking")
    public List<Personnel> getAllPersonnelByParking(@RequestBody Parking parking) {
        Parking p = parkingRepository.findById(parking.getId()).orElse(null);
        List<Zone> zones = p.getZones();
        List<Personnel> personnels = new ArrayList<Personnel>();
        // List<Personnel> personnels2=parking.getZones();

        zones.stream().forEach(zone -> zone.getGardes().forEach(personnel -> personnels.add(personnel)));
        return personnels;

    }


    @GetMapping("GardeJour")
    public int nombreGardeJour(String adresse) {
        List<Parking> parkings =parkingRepository.findAllByAdresse(adresse);
        List<Personnel> personnels = new ArrayList<Personnel>();
        parkings.stream().forEach(parking -> parking.getZones().forEach(zone -> zone.getGardes().forEach(personnel -> personnels.add(personnel))));
        List<Personnel> finale = (List<Personnel>) personnels.stream().filter(personnel ->(personnel.getPoste().equals("Garde_Jour")));
        return finale.size();

    }
}






