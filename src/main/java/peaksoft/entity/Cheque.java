package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.*;


@Entity
@Table(name = "cheques")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cheque_id_gen")
    @SequenceGenerator(name = "cheque_id_gen",sequenceName = "cheque_id_seq", allocationSize = 1)
    private Long id;
    private int priceAverage;
    private LocalDate createAt;
    @ManyToOne(cascade = {MERGE,PERSIST, DETACH,REFRESH})
    private User user;
    @ManyToMany(mappedBy = "cheque",cascade = ALL)
    private List<MenuItem>menuItems;
}
