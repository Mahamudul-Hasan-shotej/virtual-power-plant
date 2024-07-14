package mahamudul.example.virtual_power_plant.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "battery_table")

public class BatteryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    @Column(name = "name") private String name;
    @Column(name = "post_code") private String postcode;
    @Column(name = "watt_capacity") private int wattCapacity;
}
