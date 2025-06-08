package com.example.test.application.usecase.ElementPrescrit;

import com.example.test.domain.model.Appointment;
import com.example.test.domain.model.ElementPrescrit;
import com.example.test.domain.repository.ElementPrescritRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class GetElementPrescritUseCase {
   private final ElementPrescritRepository elementPrescritRepository;

    public GetElementPrescritUseCase(ElementPrescritRepository elementPrescritRepository) {
        this.elementPrescritRepository = elementPrescritRepository;
    }

    public Optional<ElementPrescrit> execute(String id){

        return elementPrescritRepository.findElementPrescritById(id);
    }
}
