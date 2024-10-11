package com.ltc.todo.service;

import com.ltc.todo.dto.requestDto.CategoryRequestDto;
import com.ltc.todo.dto.responseDto.CategoryResponseDto;
import com.ltc.todo.entity.Category;
import com.ltc.todo.repository.CategoryRepository;
import com.ltc.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final TaskRepository taskRepository;

    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> modelMapper.map(category , CategoryResponseDto.class)).toList();
    }

    public String createCategory(CategoryRequestDto categoryRequestDto) {
        if(categoryRepository.existsByName(categoryRequestDto.getName())) {
            return "Category already exist";
        }

        Category category = modelMapper.map(categoryRequestDto, Category.class);
        categoryRepository.save(category);

        return "Create category successfully";
    }

    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        if(category.getTasks().isEmpty()){
            categoryRepository.delete(category);
            return "Delete category successfully";
        } else {
            return "Category has Task";
        }
    }


}
