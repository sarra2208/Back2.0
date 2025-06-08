package com.example.test.application.usecase.ElementPrescrit;

import com.example.test.domain.model.ElementPrescrit;
import com.example.test.domain.repository.ElementPrescritRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateElementPrescritUseCase {
    private final ElementPrescritRepository elementPrescritRepository;
@Autowired
    public UpdateElementPrescritUseCase(ElementPrescritRepository elementPrescritRepository) {
        this.elementPrescritRepository = elementPrescritRepository;
    }
    public ElementPrescrit execute(String id, ElementPrescrit updatedElementPrescrit) {
        return elementPrescritRepository.findElementPrescritById(id)
                .map(existingElementPrescrit -> {
                    // Mettre à jour uniquement si l'ID existe
                    existingElementPrescrit.setType(updatedElementPrescrit.getType());
                    existingElementPrescrit.setNom(updatedElementPrescrit.getNom());
                    existingElementPrescrit.setDescription(updatedElementPrescrit.getDescription());


                    return elementPrescritRepository.save(existingElementPrescrit);
                })
                .orElseThrow(() -> new RuntimeException("⚠️ Appointment not found with ID: " + id));
    }
    
}
