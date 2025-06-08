package com.example.test.application.usecase.Staff;

import com.example.test.domain.repository.StaffRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteStaffUseCase {
    private final StaffRepository staffRepository;

    public DeleteStaffUseCase(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public  void execute(String id) {
        staffRepository.deleteById(id);
    }

}
