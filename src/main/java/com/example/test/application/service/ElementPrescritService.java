package com.example.test.application.service;

import com.example.test.application.usecase.ElementPrescrit.GetElementPrescritUseCase;
import com.example.test.application.usecase.ElementPrescrit.GetListElementPrescritUseCase;
import com.example.test.application.usecase.ElementPrescrit.SaveElementPrescritUseCase;
import com.example.test.domain.model.ElementPrescrit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ElementPrescritService {
    private final SaveElementPrescritUseCase saveElementPrescritUseCase;
    private final GetElementPrescritUseCase getElementPrescritUseCase;
    private final GetListElementPrescritUseCase getListElementPrescritUseCase;

    public ElementPrescritService(SaveElementPrescritUseCase saveElementPrescritUseCase, GetElementPrescritUseCase getElementPrescritUseCase, GetListElementPrescritUseCase getListElementPrescritUseCase) {
        this.saveElementPrescritUseCase = saveElementPrescritUseCase;
        this.getElementPrescritUseCase = getElementPrescritUseCase;
        this.getListElementPrescritUseCase = getListElementPrescritUseCase;
    }


    public ElementPrescrit saveElementPrescrit(ElementPrescrit ElementPrescrit) {
        return saveElementPrescritUseCase.execute(ElementPrescrit);
    }

    public List<ElementPrescrit> getListElementPrescrits() {
        return getListElementPrescritUseCase.execute();
    }

    public Optional<ElementPrescrit> getElementPrescritById(String id) {
        return getElementPrescritUseCase.execute(id);
    }
}
