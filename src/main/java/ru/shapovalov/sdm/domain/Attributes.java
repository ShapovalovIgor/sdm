package ru.shapovalov.sdm.domain;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Data
@AttributeOverride(name = "uuid", column = @Column(name = "uuid", updatable = false))
@Table(name = "attributes")
public class Attributes extends AbstractEntity{

    @NonNull
    @ManyToOne
    @Column(name = "object_to_attributes")
    private ObjectToAttributes objectToAttributes;

    @NonNull
    @OneToOne
    @Column(name = "attribute_type")
    private String attributeType;

    @NonNull
    @OneToMany
    @Column(name = "attribute_management")
    private Attributes attributeManagement;

    @NonNull
    @Column(name = "name")
    private String name;

    @Column(name = "properties")
    private String properties;
}