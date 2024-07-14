package mahamudul.example.virtual_power_plant.service.impl;

import lombok.extern.slf4j.Slf4j;
import mahamudul.example.virtual_power_plant.entity.BatteryEntity;
import mahamudul.example.virtual_power_plant.exception.BatteryNotFoundException;
import mahamudul.example.virtual_power_plant.exception.InvalidBatteryException;
import mahamudul.example.virtual_power_plant.model.BatteriesInfoDto;
import mahamudul.example.virtual_power_plant.model.Battery;
import mahamudul.example.virtual_power_plant.model.Statistics;
import mahamudul.example.virtual_power_plant.repository.BatteryRepository;
import mahamudul.example.virtual_power_plant.service.BatteryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class BatteryServiceImpl implements BatteryService {



    private final BatteryRepository batteryRepository;

    public BatteryServiceImpl(BatteryRepository batteryRepository) {
        this.batteryRepository = batteryRepository;
    }


    public List<Battery> create(List<Battery> batteries) {

        List<Battery> savedBatteries = new ArrayList<>();
        log.info("Saving Battries -> {}", batteries);

        if(batteries.isEmpty()){
          log.info("Battery list must not be empty");
          throw new BatteryNotFoundException("Battery list must not be empty");
        }

        for (Battery battery : batteries) {
            validateBattery(battery);
            BatteryEntity batteryEntity = new BatteryEntity();
            batteryEntity.setName(battery.getName());
            batteryEntity.setPostcode(battery.getPostcode());
            batteryEntity.setWattCapacity(battery.getWattCapacity());
            Battery saveBattery  = convertToBattery(batteryRepository.save(batteryEntity));
            savedBatteries.add(saveBattery);
        }

      log.info("Battries saved -> {}", savedBatteries);
        return savedBatteries;

    }


    @Override
    public BatteriesInfoDto retrieveBatteryInfoByPostcodeRange(int startPostcode, int endPostcode) {
        log.info("Retrieving Batteries by startPostcode -> {} and endPostcode -> {} ", startPostcode ,endPostcode);



        List<String> batteries = batteryRepository.findAllBatteryNamesInRange(startPostcode, endPostcode);
        Statistics statistics = batteryRepository.calculateStatistics(startPostcode,endPostcode);

        if (batteries != null) {
            log.info("Retrieved Batteries by startPostcode -> {} and endPostcode -> {} ", startPostcode ,endPostcode);

        } else {
            log.info("No info found");
            throw new BatteryNotFoundException("No Battery found");
        }

        return new BatteriesInfoDto(batteries, statistics);

    }


    private void validateBattery(Battery battery) {

        if (battery == null || battery.getName() == null || battery.getPostcode() == null) {
            log.warn("Invalid Battery Data: {}", battery);
            throw new InvalidBatteryException("Battery name and postcode cannot be null");
        }
    }
    private Battery convertToBattery(BatteryEntity batteryEntity) {
        return new Battery(batteryEntity.getId(), batteryEntity.getName(), batteryEntity.getPostcode(),batteryEntity.getWattCapacity());
    }

}
