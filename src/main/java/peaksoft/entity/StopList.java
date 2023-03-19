package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.text.DateFormat;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.REFRESH;

@Entity
@Table(name = "stop_lists")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StopList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stopList_id_gen")
    @SequenceGenerator(name = "stopList_id_gen", sequenceName = "stopList_id_seq",allocationSize = 1)
    private Long id;
    private String reason;
    private DateFormat date;
    @OneToOne(cascade = {MERGE,PERSIST, DETACH,REFRESH})
    private MenuItem menuItem;
}
