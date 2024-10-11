package com.ltc.todo.controller;

import com.ltc.todo.dto.requestDto.TaskRequestDto;
import com.ltc.todo.dto.responseDto.TaskResponseDto;
import com.ltc.todo.entity.Task;
import com.ltc.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create")
    public String createTask(TaskRequestDto taskRequestDto) {
        return taskService.createTask(taskRequestDto);
    }

    @PutMapping("/update/{id}")
    public String updateTask(@PathVariable Long id,@RequestBody TaskRequestDto taskRequestDto) {
        return taskService.updateTask(taskRequestDto, id);
    }

    @GetMapping("/getAllTasks")
    public List<TaskResponseDto> getAllTask() {
        return taskService.getAllTasks();
    }

    @GetMapping("/filter")
    public  List<TaskResponseDto> filterTasksByCategory(@RequestParam Long categoryId) {
        return taskService.filterTasksByCategory(categoryId);
    }


    @DeleteMapping("/delete")
    public String deleteTask(Long id) {
        return taskService.deleteTask(id);
    }

}
