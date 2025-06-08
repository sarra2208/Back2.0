package com.example.test.application.service;

import com.example.test.application.usecase.Staff.GetListStaffUseCase;
import com.example.test.application.usecase.Staff.GetStaffUseCase;
import com.example.test.application.usecase.Staff.SaveStaffUseCase;
import com.example.test.domain.model.Staff;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StaffService {
    private final GetListStaffUseCase getListStaffUseCase;
    private final GetStaffUseCase getStaffUseCase;
    private final SaveStaffUseCase saveStaffUseCase;

    public StaffService(GetListStaffUseCase getListStaffUseCase, GetStaffUseCase getStaffUseCase, SaveStaffUseCase saveStaffUseCase) {
        this.getListStaffUseCase = getListStaffUseCase;
        this.getStaffUseCase = getStaffUseCase;
        this.saveStaffUseCase = saveStaffUseCase;
    }

    public Staff saveStaff(Staff staff){
        return saveStaffUseCase.execute(staff);
    }

    public List<Staff> getListStaffs(){
        return getListStaffUseCase.execute();
    }

    public Optional<Staff> getStaffById(String id){
        return getStaffUseCase.execute(id);
    }

}
