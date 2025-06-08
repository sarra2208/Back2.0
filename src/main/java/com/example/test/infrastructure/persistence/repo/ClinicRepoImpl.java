package com.example.test.infrastructure.persistence.repo;

import com.example.test.domain.model.Clinic;
import com.example.test.domain.repository.ClinicRepository;
import com.example.test.infrastructure.persistence.entity.ClinicEntity;
import com.example.test.infrastructure.persistence.mapper.ClinicMapper;
import com.example.test.infrastructure.persistence.repo.jpa.ClinicRepo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClinicRepoImpl implements ClinicRepository {

    private final ClinicRepo clinicRepo;

    public ClinicRepoImpl(@Lazy ClinicRepo clinicRepo) {
        this.clinicRepo = clinicRepo;
    }

    @Override
    public Clinic save(Clinic clinic) {
        ClinicEntity entity = ClinicMapper.toEntity(clinic);
        return ClinicMapper.toDomain(clinicRepo.save(entity));
    }

    @Override
    public List<Clinic> findAllClinics() {
        return clinicRepo.findAll()
                .stream()
                .map(ClinicMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Clinic> findClinicById(String id) {
        return clinicRepo.findById(id)
                .map(ClinicMapper::toDomain);
    }

    @Override
    public void deleteById(String id) {
        clinicRepo.deleteById(id);
    }

    @Override
    public Clinic update(String id, Clinic updatedClinic) {
        ClinicEntity entity = clinicRepo.findById(id)
                .map(existingClinic -> {
                    existingClinic.setName(updatedClinic.getName());
                    existingClinic.setAddress(updatedClinic.getAddress());
                    existingClinic.setPhone(updatedClinic.getPhone());
                    existingClinic.setEmail(updatedClinic.getEmail());
                    return clinicRepo.save(existingClinic);
                })
                .orElseThrow(() -> new RuntimeException("Clinic not found: " + id));

        return ClinicMapper.toDomain(entity);
    }
}
