package com.example.test.application.usecase.Staff;

import com.example.test.domain.model.Staff;
import com.example.test.domain.repository.StaffRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GetListStaffUseCase {
   private final StaffRepository staffRepository;

    public GetListStaffUseCase(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }
    public List<Staff> execute(){
        return staffRepository.findAllStaff();
    }
}
