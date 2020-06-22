package at.rennweg.htl.webt.blobs.controllers;

import at.rennweg.htl.webt.blobs.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;


/**
 * Handles uploads to and GETs of JSP pages.
 *
 * @author F. Kasper, ferdinand.kasper@bildung.gv.at
 */
@Controller
public class PageController {

    private final FileRepository fileRepository;
    private final ContentController contentController;


    @Autowired
    public PageController(FileRepository fileRepository,
                          ContentController contentController) {
        this.fileRepository = fileRepository;
        this.contentController = contentController;
    }


    @GetMapping("/files")
    public String files(Map<String, Object> model) {
        model.put("files", fileRepository.findAllByOrderByName());
        return "files";
    }


    @PostMapping(path = "/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload(
        @RequestPart("file") MultipartFile uploaded,
        HttpServletRequest request) throws IOException, URISyntaxException {

        contentController.upload(uploaded, request);
        return "redirect:/files";
    }

}
