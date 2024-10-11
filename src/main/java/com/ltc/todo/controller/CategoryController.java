package com.ltc.todo.controller;

import com.ltc.todo.dto.requestDto.CategoryRequestDto;
import com.ltc.todo.dto.responseDto.CategoryResponseDto;
import com.ltc.todo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/getAllCategories")
    public List<CategoryResponseDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/create")
    public String createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryService.createCategory(categoryRequestDto);
    }

    @DeleteMapping("/delete")
    public String deleteCategory(@RequestParam Long id) {
        return categoryService.deleteCategory(id);
    }



    }
