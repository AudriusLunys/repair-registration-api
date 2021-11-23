package lt.codeacademy.registration.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "repair_orders")
public class RepairOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotNull
    private Long registrationNr;

    @NotNull
    private LocalDate registrationDate;

    private String repairDescription;

    @ManyToOne (cascade = CascadeType.ALL)
    private Customer customer;

    @ManyToOne (cascade = CascadeType.ALL)
    private Device device;
}
