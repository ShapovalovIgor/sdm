package ru.shapovalov.sdm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shapovalov.sdm.domain.Object;

import java.util.UUID;

@Repository
public interface ObjectRepository extends CrudRepository<Object, UUID> {
}
