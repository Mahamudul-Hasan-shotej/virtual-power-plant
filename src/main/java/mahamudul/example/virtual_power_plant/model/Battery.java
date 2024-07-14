package mahamudul.example.virtual_power_plant.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Battery {
    private Long id;
    private String name;
    private String postcode;
    private int wattCapacity;


}
