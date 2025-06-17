package com.example.test.infrastructure.persistence.repo.jpa;

import com.example.test.infrastructure.persistence.entity.AppointmentEntity;
import com.example.test.infrastructure.persistence.entity.AppointmentStatsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface AppointmentRepo extends JpaRepository<AppointmentEntity, Long> {
    @Query("SELECT a FROM AppointmentEntity a " +
            "WHERE a.patient.id = :patientId " +
            "AND (a.date > :todayDate OR (a.date = :todayDate AND a.heure > :currentTime)) " +
            "ORDER BY a.date ASC, a.heure ASC")
    List<AppointmentEntity> findNextAppointmentByPatientId(
            @Param("patientId") String patientId,
            @Param("todayDate") String todayDate,
            @Param("currentTime") String currentTime);

    @Query("SELECT new com.example.test.infrastructure.persistence.entity.AppointmentStatsDTO(" +
            "SUBSTRING(a.date, 1, 7), COUNT(a)) " +
            "FROM AppointmentEntity a " +
            "GROUP BY SUBSTRING(a.date, 1, 7) " +
            "ORDER BY SUBSTRING(a.date, 1, 7)")
    List<AppointmentStatsDTO> countAppointmentsByMonth();

    @Query("SELECT a FROM AppointmentEntity a WHERE a.date = :date")
    List<AppointmentEntity> findByDate(@Param("date") String date);
}
