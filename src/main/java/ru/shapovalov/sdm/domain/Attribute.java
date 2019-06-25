package ru.shapovalov.sdm.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AttributeOverride(name = "uuid", column = @Column(name = "uuid", updatable = false))
@Table(name = "attribute")
public class Attribute extends AbstractEntity{
    private static final long serialVersionUID = 9187855247587310623L;

    @JoinColumn(name = "object_to_attribute", nullable = false,
            foreignKey = @ForeignKey(name = "fk_achievement__achievement_type"))
    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    private ObjectToAttribute objectToAttribute;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="attribute_management_uuid", foreignKey = @ForeignKey(name = "attribute__attribute_fk"))
    private Attribute attributeManagement;

    @NonNull
    @Column(name = "name")
    private String name;

    @Column(name = "properties")
    private String properties;
}
