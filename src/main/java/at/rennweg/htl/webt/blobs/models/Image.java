package at.rennweg.htl.webt.blobs.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.jdbc.BlobProxy;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.function.Consumer;


/**
 * An image file which may be scaled when instantiated.
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


    /**
     * Creates a thumbnailed {@link Image} entity from an uploaded file,
     * neither materializing the original nor the resized image in memory.
     */
    public Image(MultipartFile multipartFile, int maxDimension) throws IOException {
        setProperties(multipartFile, op -> op.thumbnail(maxDimension));

        // FIXME Set width and height correctly
        width = height = maxDimension;
    }


    private int width = 123;

    private int height = 456;


    /**
     * Processes the original file and sets the {@link Image} properties,
     * neither materializing the original nor the processed image in memory.
     */
    protected void setProperties(MultipartFile original, Consumer<IMOperation> processingSteps) throws IOException {
        // Pipe the original image into ImageMagick
        ConvertCmd cmd = new ConvertCmd();
        cmd.setInputProvider(outputStream -> FileCopyUtils.copy(original.getInputStream(), outputStream));

        // Save the processed image in a temporary file
        Path processed = Files.createTempFile("imagick", "");

        // Set up the processing steps
        IMOperation op = new IMOperation();
        op.addImage("-");
        processingSteps.accept(op);
        op.addImage("jpg:" + processed);

        // Process the original image
        try {
            cmd.run(op);
        } catch (IM4JavaException | InterruptedException ex) {
            // Assume that something was wrong with the image
            throw new IllegalArgumentException(ex);
        }

        // Set the properties
        setName(original.getOriginalFilename());
        setMimeType(original.getContentType());
        setSize(Files.size(processed));
        setContent(BlobProxy.generateProxy(Files.newInputStream(processed, StandardOpenOption.DELETE_ON_CLOSE), getSize()));
    }

}
