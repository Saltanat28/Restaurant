package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Category;
import peaksoft.repository.CategoryRepository;
import peaksoft.service.CategoryService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public SimpleResponse save(Category category) {
        Category category1 = new Category();
        category1.setName(category.getName());
         categoryRepository.save(category);
         return SimpleResponse.builder().status(HttpStatus.OK).massage("Category successfully saved!").build();
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new NoSuchElementException(" Category not found!"));
        return category;
    }

    @Override
    public SimpleResponse delete(Long categoryId) {
        categoryRepository.deleteById(categoryId);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Category successfully deleted !").build();

    }


}
