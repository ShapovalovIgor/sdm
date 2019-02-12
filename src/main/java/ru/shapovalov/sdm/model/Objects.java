package ru.shapovalov.sdm.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Data
@AttributeOverride(name = "uuid", column = @Column(name = "uuid", updatable = false))
@Table(name = "objects")
public class Objects extends AbstractEntity {

    @ManyToOne
    @Column(name = "parent_uuid")
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

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Objects> childrenList;
}
