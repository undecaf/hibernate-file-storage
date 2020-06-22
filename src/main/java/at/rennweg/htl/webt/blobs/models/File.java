package at.rennweg.htl.webt.blobs.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
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

    /**
     * Creates a new {@link File} entity without materializing the content in memory.
     */
    public File(MultipartFile multipartFile) throws IOException {
        setName(multipartFile.getOriginalFilename());
        setMimeType(multipartFile.getContentType());
        setSize(multipartFile.getSize());
        setContent(BlobProxy.generateProxy(multipartFile.getInputStream(), multipartFile.getSize()));
    }


    @NotBlank
    private String name;

    @NotNull
    private String mimeType;

    private long size;

    @Lob
    @NotNull
    private Blob content;

}
