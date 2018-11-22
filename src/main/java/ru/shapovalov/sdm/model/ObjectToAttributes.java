package ru.shapovalov.sdm.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@RequiredArgsConstructor
@Table(name = "object_to_attributes")
public class ObjectToAttributes {

    @Getter
    @Setter
    @Id
    @ManyToOne
    @Column(name = "object_types_uuid")
    private ObjectTypes object_types;

    @Getter
    @Setter
    @Id
    @OneToOne
    @Column(name = "attribute_uuid")
    private Attributes attribute;

    @Getter
    @Setter
    @OneToMany(mappedBy = "object_types", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ObjectToAttributes> objetToAttributeList;
}
