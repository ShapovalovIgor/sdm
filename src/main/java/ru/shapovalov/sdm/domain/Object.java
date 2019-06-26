package ru.shapovalov.sdm.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "object")
public class Object extends AbstractEntity {
    private static final long serialVersionUID = 8030321545840126735L;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="parent_uuid", nullable = true, foreignKey = @ForeignKey(name = "objects__objects_fk"))
    private Object parent;

    @NonNull
    @Column(name = "name")
    private String name;

    @Column(name = "properties")
    private String properties;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "version", nullable = false)
    private int version;

    @Column(name = "order_number", nullable = false)
    private int orderNumber;

    @OneToMany(mappedBy = "uuid", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Object> childrenList;

    @OneToMany(mappedBy = "object", fetch = FetchType.LAZY)
    @Column(name = "attributes")
    private List<Attribute> attributes;
}
