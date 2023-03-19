package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "menu_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_item_id_gen")
    @SequenceGenerator(name = "menu_item_gen", sequenceName = "menu_item_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String image;
    private BigDecimal price;
    private String description;
    private Boolean isVegetarian;
    @ManyToOne(cascade = {MERGE,PERSIST, DETACH,REFRESH})
    private Restaurant restaurant;
    @ManyToMany(cascade = ALL)
    private List<Cheque> cheque;
    @OneToOne(cascade = ALL)
    private StopList stopList;

}
