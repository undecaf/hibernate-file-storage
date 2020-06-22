package at.rennweg.htl.webt.blobs.repositories;

import at.rennweg.htl.webt.blobs.models.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * Allows access to persistent {@link Image} entities.
 *
 * @author F. Kasper, ferdinand.kasper@bildung.gv.at
 */
public interface ImageRepository extends CrudRepository<Image, Long> {

    List<Image> findByWidthLessThanEqualAndHeightLessThanEqualOrderByName(int width, int height);

    List<Image> findByNameIgnoreCaseOrderBySize(String name);

}
