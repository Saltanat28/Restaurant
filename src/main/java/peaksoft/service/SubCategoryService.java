package peaksoft.service;

import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.SubCategory;

import java.util.List;

public interface SubCategoryService {
    SimpleResponse save(SubCategory subCategory);
    SubCategory getById(Long subCatId);
    List<SubCategory>getAll();
    SimpleResponse delete(Long subCatId);
    SimpleResponse update(Long subCatId, SubCategory subCategory);
    SimpleResponse setSubCatToCat(SubCategory subCategory, Long catId);
}
