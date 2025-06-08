package com.example.test.web.controller;

import com.example.test.application.service.ServiceService;
import com.example.test.application.usecase.Service.DeleteServiceUseCase;
import com.example.test.application.usecase.Service.UpdateServiceUseCase;

import com.example.test.domain.model.Service;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v0/service")
@CrossOrigin
public class ServiceController {
    private final ServiceService serviceService;
    private final UpdateServiceUseCase updateServiceUseCase;
    private final DeleteServiceUseCase deleteServiceUseCase;

    public ServiceController(ServiceService serviceService, UpdateServiceUseCase updateServiceUseCase, DeleteServiceUseCase deleteServiceUseCase) {
        this.serviceService = serviceService;
        this.updateServiceUseCase = updateServiceUseCase;
        this.deleteServiceUseCase = deleteServiceUseCase;
    }

    @PostMapping("/addService")
    public Service saveService(@RequestBody Service service){
        return serviceService.saveService(service);
    }

    @GetMapping("/listServices")
    public List<Service> getListServices(){
        return serviceService.getListServices();
    }

    @GetMapping("/{id}")
    public Optional<Service> getService(@PathVariable String id){
        return serviceService.getServiceById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable String id) {
        deleteServiceUseCase.execute(id); // ✅ Correction de l'appel
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateService (@PathVariable String id, @RequestBody Service service){
        try {
            Service updatedService;
            updatedService = updateServiceUseCase.execute(id,service);
            return ResponseEntity.ok(updatedService);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Retourne un message clair si l'ID n'existe pas
        }
    }
}
