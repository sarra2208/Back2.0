package com.example.test.services;

import com.example.test.application.service.StaffService;
import com.example.test.application.usecase.Staff.GetListStaffUseCase;
import com.example.test.application.usecase.Staff.GetStaffUseCase;
import com.example.test.application.usecase.Staff.SaveStaffUseCase;
import com.example.test.domain.model.Staff;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StaffServiceTest {

    private GetListStaffUseCase getListStaffUseCase;
    private GetStaffUseCase getStaffUseCase;
    private SaveStaffUseCase saveStaffUseCase;
    private StaffService staffService;

    @BeforeEach
    void setUp() {
        getListStaffUseCase = mock(GetListStaffUseCase.class);
        getStaffUseCase = mock(GetStaffUseCase.class);
        saveStaffUseCase = mock(SaveStaffUseCase.class);

        staffService = new StaffService(getListStaffUseCase, getStaffUseCase, saveStaffUseCase);
    }

    @Test
    void testSaveStaff() {
        Staff staff = new Staff();
        when(saveStaffUseCase.execute(staff)).thenReturn(staff);

        Staff result = staffService.saveStaff(staff);

        assertNotNull(result);
        assertEquals(staff, result);
        verify(saveStaffUseCase, times(1)).execute(staff);
    }

    @Test
    void testGetListStaffs() {
        Staff s1 = new Staff();
        Staff s2 = new Staff();
        List<Staff> expectedList = Arrays.asList(s1, s2);

        when(getListStaffUseCase.execute()).thenReturn(expectedList);

        List<Staff> result = staffService.getListStaffs();

        assertEquals(expectedList, result);
        verify(getListStaffUseCase, times(1)).execute();
    }

    @Test
    void testGetStaffById() {
        String id = "staff-123";
        Staff staff = new Staff();

        when(getStaffUseCase.execute(id)).thenReturn(Optional.of(staff));

        Optional<Staff> result = staffService.getStaffById(id);

        assertTrue(result.isPresent());
        assertEquals(staff, result.get());
        verify(getStaffUseCase, times(1)).execute(id);
    }

    @Test
    void testGetStaffById_NotFound() {
        String id = "nonexistent";

        when(getStaffUseCase.execute(id)).thenReturn(Optional.empty());

        Optional<Staff> result = staffService.getStaffById(id);

        assertFalse(result.isPresent());
        verify(getStaffUseCase, times(1)).execute(id);
    }
}
