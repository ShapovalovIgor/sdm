package ru.shapovalov.sdm.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "object_to_attributes")
public class ObjectToAttributes {

    @Id
    @OneToMany
    @Column(name = "attribute_managmet_uuid")
    private Attributes attributeManagmet;

    @Id
    @OneToOne
    @Column(name = "attribute_uuid")
    private Attributes attribute;

    @OneToMany(mappedBy = "object_types", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ObjectToAttributes> objetToAttributeList;
}
