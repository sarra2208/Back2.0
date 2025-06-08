package com.example.test.application.usecase.ElementPrescrit;

import com.example.test.domain.model.ElementPrescrit;
import com.example.test.domain.model.Patient;
import com.example.test.domain.repository.ElementPrescritRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveElementPrescritUseCase {
   private final ElementPrescritRepository elementPrescritRepository;

    public SaveElementPrescritUseCase(ElementPrescritRepository elementPrescritRepository) {
        this.elementPrescritRepository = elementPrescritRepository;
    }
    public ElementPrescrit execute(ElementPrescrit elementPrescrit){
        return elementPrescritRepository.save(elementPrescrit);
    }
}
