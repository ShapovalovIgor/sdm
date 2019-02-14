package ru.shapovalov.sdm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shapovalov.sdm.domain.ObjectToAttributes;

import java.util.UUID;

@Repository
public interface ObjectToAttributesRepository extends CrudRepository<ObjectToAttributes, UUID> {
}
