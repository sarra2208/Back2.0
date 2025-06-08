package com.example.test.domain.repository;

import com.example.test.domain.model.Staff;
import com.example.test.infrastructure.persistence.entity.ServiceEntity;
import com.example.test.infrastructure.persistence.entity.StaffEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Repository
public interface StaffRepository {
    Optional<Staff> findStaffById(String id) ;

    Staff save(Staff staff);
    List<Staff> findAllStaff();



    void deleteById(String id);


    StaffEntity update(String id, Staff updatedStaff);
}

