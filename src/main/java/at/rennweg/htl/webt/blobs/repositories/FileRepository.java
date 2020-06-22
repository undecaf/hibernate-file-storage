package at.rennweg.htl.webt.blobs.repositories;

import at.rennweg.htl.webt.blobs.models.File;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * Allows access to persistent {@link File} entities.
 *
 * @author F. Kasper, ferdinand.kasper@bildung.gv.at
 */
public interface FileRepository extends CrudRepository<File, Long> {

    <T extends File> Optional<T> findFileByUuid(UUID uuid);

    List<File> findAllByOrderByName();

}
