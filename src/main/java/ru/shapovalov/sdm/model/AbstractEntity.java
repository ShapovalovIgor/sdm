package ru.shapovalov.sdm.model;


import javax.persistence.*;
import java.util.UUID;

@Entity
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "object_uuid")
    private UUID object_uuid;
}
