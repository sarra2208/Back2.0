package com.example.test.application.usecase.ElementPrescrit;

import com.example.test.domain.model.ElementPrescrit;
import com.example.test.domain.model.Patient;
import com.example.test.domain.repository.ElementPrescritRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GetListElementPrescritUseCase {
   private final ElementPrescritRepository elementPrescritRepository;

    public GetListElementPrescritUseCase(ElementPrescritRepository elementPrescritRepository) {
        this.elementPrescritRepository = elementPrescritRepository;
    }
    public List<ElementPrescrit> execute(){
        return elementPrescritRepository.findAllelementPrescrits();
    }
}
