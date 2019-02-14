package ru.shapovalov.sdm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shapovalov.sdm.domain.Attributes;

import java.util.UUID;

@Repository
public interface AttributesRepository extends CrudRepository<Attributes, UUID> {
}
