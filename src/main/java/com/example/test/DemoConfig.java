package com.example.test;

import com.example.test.application.service.AppointmentService;
import com.example.test.application.service.PatientService;
import com.example.test.application.service.ServiceService;
import com.example.test.application.usecase.Appointment.*;
import com.example.test.application.usecase.Service.*;
import com.example.test.application.usecase.Staff.GetListStaffUseCase;
import com.example.test.application.usecase.patient.*;
import com.example.test.domain.repository.AppointmentRepository;
import com.example.test.domain.repository.PatientRepository;
import com.example.test.domain.repository.ServiceRepository;
import com.example.test.domain.repository.StaffRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class DemoConfig {
    @Bean
    public PatientService patientService(SavePatientUseCase savePatientUseCase,
                                         GetListPatientsUseCase getListPatientsUseCase,
                                         GetPatientUseCase getPatientUseCase) {
        return new PatientService(savePatientUseCase, getListPatientsUseCase, getPatientUseCase);
    }

    @Bean
    public AppointmentService appointmentService (SaveAppointmentUseCase saveAppointmentUseCase,
                                                  GetListAppointmentUseCase getListAppointmentUseCase,
                                                  GetAppointmentUseCase getAppointmentUseCase){
        return new AppointmentService(saveAppointmentUseCase,getListAppointmentUseCase ,getAppointmentUseCase);
    }

    @Bean
    public ServiceService serviceService(SaveServiceUseCase saveServiceUseCase,
                                         GetListServiceUseCase getListServiceUseCase,
                                         GetServiceUseCase getServiceUseCase) {
        return new ServiceService(getListServiceUseCase, getServiceUseCase, saveServiceUseCase);
    }


    @Bean
    public SavePatientUseCase savePatientUseCase(PatientRepository patientRepository) {
        return new SavePatientUseCase(patientRepository);
    }

    @Bean
    public SaveAppointmentUseCase saveAppointmentUseCase(AppointmentRepository appointmentRepository) {
        return new SaveAppointmentUseCase(appointmentRepository);
    }

    @Bean
    public SaveServiceUseCase saveServiceUseCase(ServiceRepository serviceRepository) {
        return new SaveServiceUseCase(serviceRepository);
    }

    @Bean
    public GetListPatientsUseCase getListPatientsUseCase(PatientRepository patientRepository) {
        return new GetListPatientsUseCase(patientRepository);
    }

    @Bean
    public GetListAppointmentUseCase getListAppointmentUseCase(AppointmentRepository appointmentRepository) {
        return new GetListAppointmentUseCase(appointmentRepository);
    }
    @Bean
    public GetListServiceUseCase getListServiceUseCase(ServiceRepository serviceRepository) {
        return new GetListServiceUseCase(serviceRepository);
    }
    @Bean
    public GetListStaffUseCase getListStaffUseCase(StaffRepository staffRepository) {
        return new GetListStaffUseCase(staffRepository);
    }
    @Bean
    public GetPatientUseCase getPatientUseCase(PatientRepository patientRepository) {
        return new GetPatientUseCase(patientRepository);
    }

    @Bean
    public GetAppointmentUseCase getAppointmentUseCase(AppointmentRepository appointmentRepository) {
        return new GetAppointmentUseCase(appointmentRepository);
    }
    @Bean
    public GetServiceUseCase getServiceUseCase(ServiceRepository serviceRepository) {
        return new GetServiceUseCase(serviceRepository);
    }
    @Bean
    public DeletePatientUseCase deletePatientUseCase(PatientRepository patientRepository){
        return new DeletePatientUseCase(patientRepository);
    }
    @Bean
    public DeleteAppointmentUseCase deleteAppointmentUseCase(AppointmentRepository appointmentRepository){
        return new DeleteAppointmentUseCase(appointmentRepository);
    }
    @Bean
    public DeleteServiceUseCase deleteServiceUseCase(ServiceRepository serviceRepository){
        return new DeleteServiceUseCase(serviceRepository);
    }
    @Bean
    public UpdatePatientUseCase updatePatientUseCase(PatientRepository patientRepository){
        return new UpdatePatientUseCase(patientRepository);
    }
    @Bean
    public UpdateAppointmentUseCase updateAppointmentUseCase(AppointmentRepository appointmentRepository){
        return new UpdateAppointmentUseCase(appointmentRepository);
    }
    @Bean
    public UpdateServiceUseCase updateServiceUseCase(ServiceRepository serviceRepository){
        return new UpdateServiceUseCase(serviceRepository);
    }
}