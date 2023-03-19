package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.EnableMBeanExport;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_gen")
    @SequenceGenerator(name = "category_id_gen", sequenceName = "category_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category",cascade = {CascadeType.ALL})
    private List<SubCategory> subCategoryList = new ArrayList<>();

    public void addSubCat(SubCategory subCategory1) {
        this.subCategoryList.add(subCategory1);
    }
}
