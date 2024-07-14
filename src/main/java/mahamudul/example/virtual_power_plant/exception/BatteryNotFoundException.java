package mahamudul.example.virtual_power_plant.exception;

public class BatteryNotFoundException extends RuntimeException {
    public BatteryNotFoundException(String message) {
        super(message);
    }
}
