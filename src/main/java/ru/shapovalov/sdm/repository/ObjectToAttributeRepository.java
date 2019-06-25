package ru.shapovalov.sdm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shapovalov.sdm.domain.ObjectToAttribute;

import java.util.UUID;

@Repository
public interface ObjectToAttributeRepository extends CrudRepository<ObjectToAttribute, UUID> {
}
