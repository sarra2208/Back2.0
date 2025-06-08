package com.example.test.infrastructure.persistence.repo;

import com.example.test.domain.model.Staff;
import com.example.test.domain.repository.StaffRepository;
import com.example.test.infrastructure.persistence.entity.StaffEntity;
import com.example.test.infrastructure.persistence.mapper.StaffMapper;
import com.example.test.infrastructure.persistence.repo.jpa.StaffRepo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class StaffRepoImpl implements StaffRepository {
    private final StaffRepo staffRepo;

    public StaffRepoImpl(@Lazy StaffRepo staffRepo) {
        this.staffRepo = staffRepo;
    }

    public List<Staff> findAllStaff() {
        return staffRepo.findAll()
                .stream()
                .map(StaffMapper::toDomain)
                .toList();
    }

    // @Override
    public Optional<Staff> findStaffById(String id) {
        return staffRepo.findById(id)
                .map(StaffMapper::toDomain);
    }

    @Override
    public Staff save(Staff staff) {
        StaffEntity entity = StaffMapper.toEntity(staff);
        return StaffMapper.toDomain(staffRepo.save(entity));
    }

    //  @Override
    public void deleteById(String id) {
        staffRepo.deleteById(id);
    }
    //  @Override
    public StaffEntity update(String id, Staff updatedStaff) {
        return staffRepo.findById(id)
                .map(existingStaff -> {
                    existingStaff.setNom(updatedStaff.getNom());
                    existingStaff.setPrenom(updatedStaff.getPrenom());
                    existingStaff.setRole(updatedStaff.getRole());
                    existingStaff.setEmail(updatedStaff.getEmail());
                    existingStaff.setTelephone(updatedStaff.getTelephone());
                    existingStaff.setAdresse(updatedStaff.getAdresse());
                    existingStaff.setHireDate(updatedStaff.getHireDate());

                    return staffRepo.save(existingStaff);
                })
                .orElseThrow(() -> new RuntimeException(id) ); // Utilisation de l'exception personnalisée
    }
}


