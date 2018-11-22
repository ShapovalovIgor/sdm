package ru.shapovalov.sdm.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@ToString(exclude="uuid")
@EqualsAndHashCode(exclude="uuid")
@Table(name = "object_types")
public class ObjectTypes {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "object_type_uuid", nullable = false)
    private UUID object_type_uuid;

    @ManyToOne
    @Column(name = "parent_uuid", nullable = true)
    private ObjectTypes parent;

    @Getter
    @Setter
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "properties", nullable = true)
    private String properties;

    @Getter
    @Setter
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ObjectTypes> childrenList;
}
