package ru.shapovalov.sdm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shapovalov.sdm.model.Objects;

import java.util.UUID;

@Repository
public interface ObjectsRepository extends CrudRepository<Objects, UUID> {
}
