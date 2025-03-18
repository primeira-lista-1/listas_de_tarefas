package com.primeira_lista.primeiro.controller;

import com.primeira_lista.primeiro.model.Task;
import com.primeira_lista.primeiro.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  @Autowired
  private TaskRepository taskRepository;

  @GetMapping
  public List<Task> getallTasks() {
    return taskRepository.findAll();
  }

  @GetMapping("/id")
  public ResponseEntity<Task> getTaskById(@RequestParam Long id) {
    Optional<Task> task = taskRepository.findById(id);
    return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Task> createTask(@RequestBody Task task) {
    Task savedTask = taskRepository.save(task);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetail) {
    Optional<Task> task = taskRepository.findById(id);
    if (task.isPresent()) {
      Task exitingTask = task.get();
      exitingTask.setTitle(taskDetail.getTitle());
      exitingTask.setDescription(taskDetail.getDescription());
      exitingTask.setCompleted(taskDetail.isCompleted());

      Task updatedTask = taskRepository.save(exitingTask);
      return ResponseEntity.ok(updatedTask);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
    Optional<Task> task = taskRepository.findById(id);
    if (task.isPresent()) {
      taskRepository.delete(task.get());
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}