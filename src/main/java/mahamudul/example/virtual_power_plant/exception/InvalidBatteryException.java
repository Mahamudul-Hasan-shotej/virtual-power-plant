package mahamudul.example.virtual_power_plant.exception;

public class InvalidBatteryException extends RuntimeException {
    public InvalidBatteryException(String message) {
        super(message);
    }
}
