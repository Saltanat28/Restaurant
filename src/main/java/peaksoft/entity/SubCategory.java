package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sub_categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subCategory_id_gen")
    @SequenceGenerator(name = "subCategory_id_gen", sequenceName = "subcategory_id_seq",allocationSize = 1)
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}
