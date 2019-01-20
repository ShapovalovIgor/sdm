package ru.shapovalov.sdm.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
@Data()
@RequiredArgsConstructor
@Table(name = "objects")
public class Objects {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "object_uuid")
    private UUID object_uuid;

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
