package com.primeira_lista.primeiro.controller;

import com.primeira_lista.primeiro.model.Task;
import com.primeira_lista.primeiro.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(TaskController.class);
    logger.info("Tentando excluir a tarefa com ID: {}", id);

    Optional<Task> task = taskRepository.findById(id);
    if (task.isPresent()) {
        logger.info("Tarefa encontrada: {}", task.get());
        taskRepository.delete(task.get());
        logger.info("Tarefa excluída com sucesso.");
        return ResponseEntity.noContent().build();
    } else {
        logger.warn("Tarefa com ID {} não encontrada.", id);
        return ResponseEntity.notFound().build();
    }
}
}