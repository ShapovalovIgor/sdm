package ru.shapovalov.sdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shapovalov.sdm.domain.Attribute;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, UUID> {
    Optional<Attribute> findByNameAndPropertiesLike(String name, String property);
}
