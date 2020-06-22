package at.rennweg.htl.webt.blobs.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Blob;


/**
 * A file whose content, name, MIME type and size are persisted.
 *
 * @author F. Kasper, ferdinand.kasper@bildung.gv.at
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Getter
@Setter
public class File extends Persistent {

    @NotBlank
    private String name;

    @NotNull
    private String mimeType;

    private long size;

    @Lob
    @NotNull
    private Blob content;

}
