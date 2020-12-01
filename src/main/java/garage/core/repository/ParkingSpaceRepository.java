package garage.core.repository;

import garage.core.entity.parking.ParkingSpace;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
    @Query("SELECT p FROM ParkingSpace p WHERE p.status = garage.core.entity.parking.SpaceStatus.VACANT")
    List<ParkingSpace> findVacant();
}
