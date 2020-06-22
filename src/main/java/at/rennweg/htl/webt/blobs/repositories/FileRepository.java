package at.rennweg.htl.webt.blobs.repositories;

import at.rennweg.htl.webt.blobs.models.File;
import org.springframework.data.repository.CrudRepository;


/**
 * Allows access to persistent {@link File} entities.
 *
 * @author F. Kasper, ferdinand.kasper@bildung.gv.at
 */
public interface FileRepository extends CrudRepository<File, Long> {

}
