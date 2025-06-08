package com.example.test.web.controller;

import com.example.test.application.service.StaffService;
import com.example.test.application.usecase.Staff.DeleteStaffUseCase;
import com.example.test.application.usecase.Staff.UpdateStaffUseCase;
import com.example.test.domain.model.Staff;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v0/staff")
@CrossOrigin
public class StaffController {
    private final StaffService staffService;
    private final UpdateStaffUseCase updateStaffUseCase;
    private final DeleteStaffUseCase deleteStaffUseCase;

    public StaffController(StaffService staffService, UpdateStaffUseCase updateStaffUseCase, DeleteStaffUseCase deleteStaffUseCase) {
        this.staffService = staffService;
        this.updateStaffUseCase = updateStaffUseCase;
        this.deleteStaffUseCase = deleteStaffUseCase;
    }

    @PostMapping("/addStaff")
    public Staff saveStaff(@RequestBody Staff staff){
        return staffService.saveStaff(staff);
    }

    @GetMapping("/listStaffs")
    public List<Staff> getListStaffs(){
        return staffService.getListStaffs();
    }

    @GetMapping("/{id}")
    public Optional<Staff> getStaff(@PathVariable String id){
        return staffService.getStaffById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable String id) {
        deleteStaffUseCase.execute(id); // ✅ Correction de l'appel
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStaff (@PathVariable String id, @RequestBody Staff staff){
        try {
            Staff updatedStaff;
            updatedStaff = updateStaffUseCase.execute(id,staff);
            return ResponseEntity.ok(updatedStaff);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Retourne un message clair si l'ID n'existe pas
        }
    }
}
