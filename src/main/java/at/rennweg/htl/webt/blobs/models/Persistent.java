package at.rennweg.htl.webt.blobs.models;

import lombok.Getter;

import javax.persistence.*;
import java.util.UUID;


/**
 * Base class of persistent entities, provides an UUID
 * in addition to the primary key.
 *
 * @author F. Kasper, ferdinand.kasper@bildung.gv.at
 */
@MappedSuperclass
@Getter
public class Persistent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(updatable = false, nullable = false)
    private UUID uuid;


    @PrePersist
    public void initUuid() {
        this.uuid = UUID.randomUUID();
    }

}
