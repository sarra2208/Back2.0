package com.example.test.application.usecase.Staff;

import com.example.test.domain.model.Staff;
import com.example.test.domain.repository.StaffRepository;
import org.springframework.stereotype.Service;
@Service
public class SaveStaffUseCase {
   private final StaffRepository staffRepository;

    public SaveStaffUseCase(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }
    public Staff execute(Staff staff){
        return staffRepository.save(staff);
    }
}
