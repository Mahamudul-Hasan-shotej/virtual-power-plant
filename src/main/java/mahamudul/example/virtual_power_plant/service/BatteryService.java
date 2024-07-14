package mahamudul.example.virtual_power_plant.service;


import mahamudul.example.virtual_power_plant.model.BatteriesInfoDto;
import mahamudul.example.virtual_power_plant.model.Battery;

import java.util.List;

public interface BatteryService {
    List<Battery> create(List<Battery> batteries);
    BatteriesInfoDto retrieveBatteryInfoByPostcodeRange(int startPostcode, int endPostcode);
}
