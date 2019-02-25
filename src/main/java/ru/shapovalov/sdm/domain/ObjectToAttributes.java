package ru.shapovalov.sdm.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AttributeOverride(name = "uuid", column = @Column(name = "uuid", updatable = false))
@Table(name = "object_to_attributes")
public class ObjectToAttributes extends AbstractEntity{
    private static final long serialVersionUID = -3517438932304685119L;

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
