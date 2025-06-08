package com.example.test.application.usecase.Staff;

import com.example.test.domain.model.Staff;
import com.example.test.domain.repository.StaffRepository;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service
public class GetStaffUseCase {
  private final StaffRepository staffRepository;

    public GetStaffUseCase(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }
    public Optional<Staff> execute(String id){
        return staffRepository.findStaffById(id);
    }
}
