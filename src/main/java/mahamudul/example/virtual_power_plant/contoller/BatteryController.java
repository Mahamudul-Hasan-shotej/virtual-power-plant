package mahamudul.example.virtual_power_plant.contoller;


import mahamudul.example.virtual_power_plant.exception.BatteryNotFoundException;
import mahamudul.example.virtual_power_plant.exception.InvalidBatteryException;
import mahamudul.example.virtual_power_plant.model.Battery;
import mahamudul.example.virtual_power_plant.service.BatteryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/batteries")
public class BatteryController {


    public BatteryController(BatteryService batteryService) {
        this.batteryService = batteryService;
    }

    BatteryService batteryService;



    @PostMapping("/create")
    public ResponseEntity<Object> createBattries(@RequestBody List<Battery> battries) {

        try {
            List<Battery> saveBattries = batteryService.create(battries);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveBattries);
        } catch (InvalidBatteryException ex) {
            return ResponseEntity.status(HttpStatus.OK).body(ex);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR Processing the request ");
        }

    }


    @GetMapping("/get")
    public ResponseEntity<Object> retrieveBatteryInfoByPostcodeRange(@RequestParam int startPostcode, @RequestParam int endPostcode) {

        try {
            var info =  batteryService.retrieveBatteryInfoByPostcodeRange(startPostcode,endPostcode);
            return ResponseEntity.status(HttpStatus.OK).body(info);
        } catch (BatteryNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.OK).body(ex);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR Processing the request");
        }
    }






}
