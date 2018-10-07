package se.eseas.showroom.ws.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(schema = "car", name = "vehicles")
@NamedQueries({
        @NamedQuery(name = "Vehicle.findByLicence", query = "SELECT u FROM Vehicle u WHERE u.licence = :licence"),
        @NamedQuery(name = "Vehicle.findById", query = "SELECT u FROM Vehicle u WHERE u.id = :id"),
        @NamedQuery(name = "Vehicle.findAll", query = "SELECT s FROM Vehicle s"),
        @NamedQuery(name = "Vehicle.countByMake", query = "SELECT count(s) FROM Vehicle s WHERE s.make LIKE :make"),
})
@Getter
@Setter
@EqualsAndHashCode(of = {"licence", "make", "model"})
@ToString(of = {"id", "licence", "make", "model"})
public class Vehicle implements Serializable { // vehicleRestDto

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Size(max = 40)
    @Column(name = "licence")
    private String licence;

    @Size(max = 20)
    @Column(name = "make")
    private String make;

    @Size(max = 20)
    @Column(name = "model")
    private String model;

    @Column(name = "power")
    private Integer power;
    @Version
    @Column(name = "opt_lock_version")
    private Integer optLockVersion;

}
