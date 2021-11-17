package lt.codeacademy.registration.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "repair_orders")
public class RepairOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //  @JsonIgnore
    private Long id;

    @NotNull
    private Long RegistrationNr;

    @NotNull
    private LocalDate registrationDate;

    private String repairDescription;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Device device;
}
