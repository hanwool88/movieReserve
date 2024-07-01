package finalproject.infra;

import finalproject.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "reservelists",
    path = "reservelists"
)
public interface ReservelistRepository
    extends PagingAndSortingRepository<Reservelist, Long> {
    List<Reservelist> findByReserveId(Long reserveId);
}
