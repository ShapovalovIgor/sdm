package ru.shapovalov.sdm.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AttributeOverride(name = "uuid", column = @Column(name = "uuid", updatable = false))
@Table(name = "object_to_attributes")
public class ObjectToAttributes extends AbstractEntity{
    private static final long serialVersionUID = -3517438932304685119L;

    @Id
    @OneToMany
    @Column(name = "attribute_management_uuid")
    private Attributes attributeManagmet;

    @Id
    @OneToOne
    @Column(name = "attribute_uuid")
    private Attributes attribute;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="object_uuid", nullable = true, foreignKey = @ForeignKey(name = "objects__objects_fk"))
    private Objects objectId;
}
