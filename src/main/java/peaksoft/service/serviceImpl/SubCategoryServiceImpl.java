package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Category;
import peaksoft.entity.SubCategory;
import peaksoft.repository.CategoryRepository;
import peaksoft.repository.SubCategoryRepository;
import peaksoft.service.SubCategoryService;

import java.util.List;
import java.util.NoSuchElementException;

@Service

public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, CategoryRepository categoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public SimpleResponse save(SubCategory subCategory) {
         subCategoryRepository.save(subCategory);
         return SimpleResponse.builder().status(HttpStatus.OK).massage("SubCategory successfully saved!").build();
    }

    @Override
    public SubCategory getById(Long subCatId) {
        SubCategory c = subCategoryRepository.findById(subCatId).orElseThrow(()-> new NoSuchElementException(" Category not found!"));
       return c;
    }

    @Override
    public List<SubCategory> getAll() {
        return subCategoryRepository.findAll();
    }

    @Override
    public SimpleResponse delete(Long subCatId) {
        subCategoryRepository.deleteById(subCatId);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("SubCategory successfully saved!").build();
    }

    @Override
    public SimpleResponse update(Long subCatId, SubCategory subCategory) {
        SubCategory c = subCategoryRepository.findById(subCatId).orElseThrow(()-> new NoSuchElementException(" Category not found!"));
        c.setName(subCategory.getName());
        subCategoryRepository.save(c);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("SubCategory successfully updated!").build();

    }

    @Override
    public SimpleResponse setSubCatToCat(SubCategory subCategory, Long catId) {
        Category category = categoryRepository.findById(catId).orElseThrow(()-> new NoSuchElementException(" Category not found!"));
        SubCategory subCategory1 = new SubCategory();
        subCategory1.setName(subCategory.getName());
        subCategory1.setCategory(subCategory.getCategory());
        category.addSubCat(subCategory1);
        subCategoryRepository.save(subCategory1);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("SubCategory successfully updated!").build();

    }
}
