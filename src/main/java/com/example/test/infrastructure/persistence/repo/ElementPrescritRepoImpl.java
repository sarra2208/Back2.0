package com.example.test.infrastructure.persistence.repo;

import com.example.test.domain.model.ElementPrescrit;
import com.example.test.domain.repository.ElementPrescritRepository;
import com.example.test.infrastructure.persistence.entity.ElementPrescritEntity;
import com.example.test.infrastructure.persistence.mapper.ElementPrescritMapper;
import com.example.test.infrastructure.persistence.mapper.ElementPrescritMapper;
import com.example.test.infrastructure.persistence.repo.jpa.ElementPrescritRepo;
import jdk.jfr.Label;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ElementPrescritRepoImpl  implements ElementPrescritRepository  {
    private final ElementPrescritRepo elementPrescritRepo;

    public ElementPrescritRepoImpl(@Lazy ElementPrescritRepo elementPrescritRepo) {
        this.elementPrescritRepo = elementPrescritRepo;
    }

    @Override
    public void deleteById(String id) {
        elementPrescritRepo.deleteById(id);

    }

    @Override
    public Optional<ElementPrescrit> findElementPrescritById(String id) {
        return elementPrescritRepo.findById(id)
                .map(ElementPrescritMapper::toDomain);
    }

    @Override
    public List<ElementPrescrit> findAllelementPrescrits() {
        return elementPrescritRepo.findAll()
                .stream()
                .map(ElementPrescritMapper::toDomain)
                .toList();
    }

    @Override
    public ElementPrescrit save(ElementPrescrit elementPrescrit) {
        return ElementPrescritMapper.toDomain( elementPrescritRepo.save(ElementPrescritMapper.toEntity(elementPrescrit)));
    }

    @Override
    public ElementPrescritEntity update(String id, ElementPrescrit updatedElementPrescrit) {
        return elementPrescritRepo.findById(id)
                .map(existingElementPrescrit -> {
                    existingElementPrescrit.setType(updatedElementPrescrit.getType());
                    existingElementPrescrit.setNom(updatedElementPrescrit.getNom());
                    existingElementPrescrit.setDescription(updatedElementPrescrit.getDescription());

                    return elementPrescritRepo.save(existingElementPrescrit);
                })
                .orElseThrow(() -> new RuntimeException(id) ); // Utilisation de l'exception personnalisée
    }
    }

