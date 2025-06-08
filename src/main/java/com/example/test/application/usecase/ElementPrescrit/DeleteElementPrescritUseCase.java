package com.example.test.application.usecase.ElementPrescrit;

import com.example.test.domain.repository.ElementPrescritRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteElementPrescritUseCase {

    private final ElementPrescritRepository elementPrescritRepository;

    public DeleteElementPrescritUseCase(ElementPrescritRepository elementPrescritRepository) {
        this.elementPrescritRepository = elementPrescritRepository;
    }

    public  void execute(String id) {
        elementPrescritRepository.deleteById(id);
    }
}

