package com.example.test.web.controller;

import com.example.test.application.service.ElementPrescritService;
import com.example.test.application.usecase.ElementPrescrit.DeleteElementPrescritUseCase;
import com.example.test.application.usecase.ElementPrescrit.UpdateElementPrescritUseCase;
import com.example.test.domain.model.ElementPrescrit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v0/ElementPrescrit")
@CrossOrigin
public class ElementPrescritController {
    private final ElementPrescritService elementPrescritService;
    private final UpdateElementPrescritUseCase updateElementPrescritUseCase;
    private final DeleteElementPrescritUseCase deleteElementPrescritUseCase;

    public ElementPrescritController(ElementPrescritService elementPrescritService, UpdateElementPrescritUseCase updateElementPrescritUseCase, DeleteElementPrescritUseCase deleteElementPrescritUseCase) {
        this.elementPrescritService = elementPrescritService;
        this.updateElementPrescritUseCase = updateElementPrescritUseCase;
        this.deleteElementPrescritUseCase = deleteElementPrescritUseCase;
    }
    @PostMapping("/addElementPrescrit")
    public ElementPrescrit saveElementPrescrit(@RequestBody ElementPrescrit ElementPrescrit){
        return elementPrescritService.saveElementPrescrit(ElementPrescrit);
    }

    @GetMapping("/listElementPrescrits")
    public List<ElementPrescrit> getListElementPrescrits(){
        return elementPrescritService.getListElementPrescrits();
    }

    @GetMapping("/{id}")
    public Optional<ElementPrescrit> getElementPrescrit(@PathVariable String id){
        return elementPrescritService.getElementPrescritById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElementPrescrit(@PathVariable String id) {
        deleteElementPrescritUseCase.execute(id); // ✅ Correction de l'appel
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateElementPrescrit (@PathVariable String id, @RequestBody ElementPrescrit ElementPrescrit){
        try {
            ElementPrescrit updatedElementPrescrit = updateElementPrescritUseCase.execute(id, ElementPrescrit);
            return ResponseEntity.ok(updatedElementPrescrit);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Retourne un message clair si l'ID n'existe pas
        }
    }
}
