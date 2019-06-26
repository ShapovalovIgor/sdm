package ru.shapovalov.sdm.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "attribute")
public class Attribute extends AbstractEntity{
    private static final long serialVersionUID = 9187855247587310623L;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="object", nullable = false, foreignKey = @ForeignKey(name = "attribute__objects_fk"))
    private Object object;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="attribute_management_uuid", foreignKey = @ForeignKey(name = "attribute__attribute_fk"))
    private Attribute attributeManagement;

    @NonNull
    @Column(name = "name")
    private String name;

    @Column(name = "properties")
    private String properties;

    @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Column(name = "parameters")
    private List<Parameter> parameters;
}
