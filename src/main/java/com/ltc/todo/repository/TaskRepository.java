package com.ltc.todo.repository;

import com.ltc.todo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByTitle(String title);

    List<Task> findByCategoryId(Long categoryId);

}
