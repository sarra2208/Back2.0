package com.example.test.web.controller;

import com.example.test.application.service.PatientService;
import com.example.test.domain.model.Patient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v0")
@CrossOrigin
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/addPatient")
    public Patient savePatient(@RequestBody Patient patient){
        return patientService.savePatient(patient);
    }

    @GetMapping("/patients")
    public List<Patient> getListPatients(){
        return patientService.getListPatients();
    }

    @GetMapping("/{id}")
    public Optional<Patient> getPatient(@PathVariable String id){
        return patientService.getPatientById(id);
    }
}
