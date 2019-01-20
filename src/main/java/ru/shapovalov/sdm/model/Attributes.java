package ru.shapovalov.sdm.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table(name = "attributes")
public class Attributes {

    @ManyToOne
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attribute_uuid")
    private UUID attribute_uuid;

    @NonNull
    @Column(name = "attribute_type")
    private String attributeType;

    @NonNull
    @Column(name = "name")
    private String name;

    @Column(name = "properties")
    private String properties;
}
