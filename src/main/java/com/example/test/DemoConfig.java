package com.example.test;

import com.example.test.application.service.AppointmentService;
import com.example.test.application.service.PatientService;
import com.example.test.application.usecase.*;
import com.example.test.domain.model.Appointment;
import com.example.test.domain.model.Patient;
import com.example.test.domain.repository.AppointmentRepository;
import com.example.test.domain.repository.PatientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;


@Configuration
public class DemoConfig {
    @Bean
    public PatientRepository patientRepository(){
    return new PatientRepository() {
        @Override
        public Patient save(Patient patient) {
            return null;
        }

        @Override
        public List<Patient> findAllPatients() {
            return null;
        }

        @Override
        public Optional<Patient> findPatientById(String id) {
            return Optional.empty();
        }
    };
    }
    @Bean
    public AppointmentRepository appointmentRepository(){
        return new AppointmentRepository() {
            @Override
            public Appointment save(Appointment appointment) {
                return null;
            }

            @Override
            public List<Appointment> findAllAppointment() {
                return null;
            }

            @Override
            public Optional<Appointment> findAppointmentById(Long id) {
                return Optional.empty();
            }
        };
    }
    @Bean
    public PatientService patientService(SavePatientUseCase savePatientUseCase,
                                         GetListPatientsUseCase getListPatientsUseCase,
                                         GetPatientUseCase getPatientUseCase) {
        return new PatientService(savePatientUseCase, getListPatientsUseCase, getPatientUseCase);
    }

    @Bean
    public AppointmentService appointmentService (SaveAppointmentUseCase saveAppointmentUseCase, GetListAppointmentUseCase getListAppointmentUseCase, GetAppointmentUseCase getAppointmentUseCase){
        return new AppointmentService(saveAppointmentUseCase,getListAppointmentUseCase ,getAppointmentUseCase);
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
    public GetListPatientsUseCase getListPatientsUseCase(PatientRepository patientRepository) {
        return new GetListPatientsUseCase(patientRepository);
    }

    @Bean
    public GetListAppointmentUseCase getListAppointmentUseCase(AppointmentRepository appointmentRepository) {
        return new GetListAppointmentUseCase(appointmentRepository);
    }

    @Bean
    public GetPatientUseCase getPatientUseCase(PatientRepository patientRepository) {
        return new GetPatientUseCase(patientRepository);
    }

    @Bean
    public GetAppointmentUseCase getAppointmentUseCase(AppointmentRepository appointmentRepository) {
        return new GetAppointmentUseCase(appointmentRepository);
    }
}