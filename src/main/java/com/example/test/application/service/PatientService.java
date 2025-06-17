package com.example.test.application.service;

import com.example.test.application.usecase.patient.GetListPatientsUseCase;
import com.example.test.application.usecase.patient.GetPatientUseCase;
import com.example.test.application.usecase.patient.SavePatientUseCase;
import com.example.test.domain.model.Patient;
import jakarta.mail.internet.MimeMessage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class PatientService {
    @Autowired
    private JavaMailSender mailSender;
    private final SavePatientUseCase savePatientUseCase;
    private final GetListPatientsUseCase getListPatientsUseCase;
    private final GetPatientUseCase getPatientUseCase;

    public PatientService(SavePatientUseCase savePatientUseCase, GetListPatientsUseCase getListPatientsUseCase, GetPatientUseCase getPatientUseCase) {
        this.savePatientUseCase = savePatientUseCase;
        this.getListPatientsUseCase = getListPatientsUseCase;
        this.getPatientUseCase = getPatientUseCase;
    }

    public Patient savePatient(Patient patient){
        return savePatientUseCase.execute(patient);
    }

    public List<Patient> getListPatients(){
        return getListPatientsUseCase.execute();
    }

    public Optional<Patient> getPatientById(String id){
        return getPatientUseCase.execute(id);
    }

    public void sendPrescriptionEmail(String toEmail, List<String> items) throws Exception {
        byte[] pdfBytes = generatePdf(items);
        sendEmailWithAttachment(toEmail, pdfBytes);
    }

    private byte[] generatePdf(List<String> items) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.LETTER);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Prescription");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            float yPosition = 660;
            contentStream.newLineAtOffset(50, yPosition);

            for (String item : items) {
                contentStream.showText("• " + item);
                contentStream.newLineAtOffset(0, -20);
                yPosition -= 20;
            }

            contentStream.endText();
            contentStream.close();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            return baos.toByteArray();
        }
    }

    private void sendEmailWithAttachment(String toEmail, byte[] pdfBytes) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject("Your Prescription");
        helper.setText("Dear patient, please find your prescription attached.");

        helper.addAttachment("prescription.pdf", new ByteArrayResource(pdfBytes));

        mailSender.send(message);
    }
    }


