package ru.shapovalov.sdm.domain;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Data
@AttributeOverride(name = "uuid", column = @Column(name = "uuid", updatable = false))
@Table(name = "objects")
public class Objects extends AbstractEntity {
    private static final long serialVersionUID = 8030321545840126735L;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="parent_uuid", nullable = true, foreignKey = @ForeignKey(name = "objects_objects_fk"))
    private Objects parent;

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

    @OneToMany(mappedBy = "objects.uuid", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Objects> childrenList;
}
