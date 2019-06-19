package ru.shapovalov.sdm.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AttributeOverride(name = "uuid", column = @Column(name = "uuid", updatable = false))
@Table(name = "attributes")
public class Attributes extends AbstractEntity{
    private static final long serialVersionUID = 9187855247587310623L;

    @NonNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="contract_uuid", nullable = false, foreignKey = @ForeignKey(name = "attributes__object_to_attributes_fk"))
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
