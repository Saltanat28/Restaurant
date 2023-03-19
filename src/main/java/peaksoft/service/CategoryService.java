package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Category;

import java.util.List;

@Service
public interface CategoryService {
    SimpleResponse save(Category category);

    List<Category> getAll();

    Category getById(Long categoryId);

    SimpleResponse delete(Long categoryId);
}
