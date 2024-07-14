package mahamudul.example.virtual_power_plant.repository;

import mahamudul.example.virtual_power_plant.entity.BatteryEntity;
import mahamudul.example.virtual_power_plant.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatteryRepository extends JpaRepository<BatteryEntity,Long> {

    @Query("SELECT b.name FROM BatteryEntity b WHERE b.postcode BETWEEN :startPostcode AND :endPostcode")
    List<String> findAllBatteryNamesInRange(@Param("startPostcode") int startPostcode, @Param("endPostcode") int endPostcode);

    @Query("SELECT new mahamudul.example.virtual_power_plant.model.Statistics(SUM(b.wattCapacity), AVG(b.wattCapacity)) FROM BatteryEntity b WHERE b.postcode BETWEEN :startPostcode AND :endPostcode")
    Statistics calculateStatistics(int startPostcode, int endPostcode);

}
