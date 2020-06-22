package at.rennweg.htl.webt.blobs.controllers;

import at.rennweg.htl.webt.blobs.models.File;
import at.rennweg.htl.webt.blobs.models.Image;
import at.rennweg.htl.webt.blobs.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.UUID;


/**
 * Handles up- and downloads of {@link File}s and {@linkImage}s.
 *
 * @author F. Kasper, ferdinand.kasper@bildung.gv.at
 */
@RestController
@RequestMapping("/content")
public class ContentController {

    public static final int[] THUMB_DIMENSIONS = { 100, 200, 400 };

    private final FileRepository fileRepository;


    @Autowired
    public ContentController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public ResponseEntity<String> upload(
        @RequestPart("file") MultipartFile uploaded,
        HttpServletRequest request) throws IOException, URISyntaxException {

        File file;

        // Has an image been uploaded?
        if (uploaded.getContentType().startsWith("image/")) {
            // Persist the image plus several thumbnails
            file = new Image(uploaded);

            for (int maxDimension : THUMB_DIMENSIONS) {
                fileRepository.save(new Image(uploaded, maxDimension));
            }

        } else {
            // Persist only the file
            file = new File(uploaded);
        }

        // Build the URI of the file
        file = fileRepository.save(file);
        URI fileUri = new URI(String.format("%s/%s", request.getRequestURL(), file.getUuid()));

        // Return the URI in the "Location" header
        return ResponseEntity.created(fileUri).build();
    }


    @GetMapping(path = "/{uuid}")
    public void download(
        @PathVariable("uuid") UUID uuid,
        @RequestParam(name = "dl", required = false) String dl,
        HttpServletRequest request,
        HttpServletResponse response) throws SQLException, IOException {

        // Get the requested file
        File file = fileRepository.findFileByUuid(uuid)
                                  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));

        response.addHeader("Content-Type", file.getMimeType());

        // Download the file?
        if (dl != null) {
            response.addHeader("Content-Disposition", "attachment; filename=" + file.getName());
        }

        FileCopyUtils.copy(file.getContent().getBinaryStream(), response.getOutputStream());
    }

}
