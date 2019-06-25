package ru.shapovalov.sdm.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AttributeOverride(name = "uuid", column = @Column(name = "uuid", updatable = false))
@Table(name = "object_to_attribute")
public class ObjectToAttribute extends AbstractEntity{
    private static final long serialVersionUID = -3517438932304685119L;

    @OneToMany(mappedBy = "objectToAttribute", fetch = FetchType.LAZY)
    @Column(name = "attribute_management")
    private List<Attribute> attributeManagement;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="object", nullable = false, foreignKey = @ForeignKey(name = "objects__objects_fk"))
    private Object objectId;
}
