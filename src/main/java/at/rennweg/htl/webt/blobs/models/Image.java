package at.rennweg.htl.webt.blobs.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import java.io.IOException;


/**
 * An image file.
 *
 * @author F. Kasper, ferdinand.kasper@bildung.gv.at
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Image extends File {

    /**
     * Creates a new {@link Image} entity without materializing the image in memory.
     */
    public Image(MultipartFile multipartFile) throws IOException {
        super(multipartFile);
    }


    // FIXME Set width and height correctly
    private int width = 123;

    private int height = 456;

}
