package com.ltc.todo.service;

import com.ltc.todo.dto.requestDto.TaskRequestDto;
import com.ltc.todo.dto.responseDto.TaskResponseDto;
import com.ltc.todo.entity.Category;
import com.ltc.todo.entity.Task;
import com.ltc.todo.repository.CategoryRepository;
import com.ltc.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    public List<TaskResponseDto> getAllTasks () {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(task -> modelMapper.map(task, TaskResponseDto.class)).toList();
    }

    public String createTask(TaskRequestDto taskRequestDto) {
        if(taskRepository.existsByTitle(taskRequestDto.getTitle())){
            return "Task already exist";
        }
        Optional<Category> category = categoryRepository.findById(taskRequestDto.getCategoryId());
        if(category.isPresent()){
            Task task = modelMapper.map(taskRequestDto, Task.class);
            task.setCategory(category.get());
            taskRepository.save(task);
            return "Created Task is Successfully";
        }
       return "Category does not exist";
    }

    public String updateTask(TaskRequestDto taskRequestDto, Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        Category category = categoryRepository.findById(taskRequestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        task.setCategory(category);
        modelMapper.map(taskRequestDto, task);
        task.setId(taskId);
        taskRepository.save(task);
        return "Updated Task is Successfully";
    }

    public String deleteTask(Long taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if(taskOptional.isPresent()){
            Task task = taskOptional.get();

            Category category = task.getCategory();
            if (category != null) {
                category.getTasks().remove(task);
                categoryRepository.save(category);
            }
            taskRepository.delete(task);
            return "Deleted Task is Successfully";
        } else {
            throw new RuntimeException("Task not found with id " + taskId);
        }

    }

    public List<TaskResponseDto> filterTasksByCategory(Long categoryId){
        List<Task> tasks = taskRepository.findByCategoryId(categoryId);
        return tasks.stream().map(task -> modelMapper.map(task, TaskResponseDto.class)).toList();
    }

}
