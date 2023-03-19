package peaksoft.api;

import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Category;
import peaksoft.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryApi {
    private  final CategoryService categoryService;
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @PostMapping

    public SimpleResponse save (@RequestBody Category category ){
        return categoryService.save(category);
    }
    @GetMapping
    public List<Category> getAll(){
        return categoryService.getAll();
    }
    @GetMapping("/{categoryId}")
    public Category getById(@PathVariable Long categoryId){
        return categoryService.getById(categoryId);
    }
    @DeleteMapping("/{categoryId}")
    public SimpleResponse delete(@PathVariable Long categoryId){
        return categoryService.delete(categoryId);
    }
}
