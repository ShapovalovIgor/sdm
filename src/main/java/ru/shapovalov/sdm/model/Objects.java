package ru.shapovalov.sdm.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@ToString(exclude="object_uuid")
@EqualsAndHashCode(exclude="object_uuid")
@Table(name = "objects")
public class Objects {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "object_uuid")
    private UUID object_uuid;

    @Getter
    @Setter
    @ManyToOne
    @Column(name = "parent_uuid")
    private Objects parent;

    @Getter
    @Setter
    @NonNull
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @NonNull
    @Column(name = "object_type_uuid")
    private ObjectTypes objectType;

    @Getter
    @Setter
    @Column(name = "properties")
    private String properties;

    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "version", nullable = false)
    private int version;

    @Getter
    @Setter
    @Column(name = "order_number", nullable = false)
    private int orderNumber;

    @Getter
    @Setter
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Objects> childrenList;
}
