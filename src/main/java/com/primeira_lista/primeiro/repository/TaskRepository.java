package com.primeira_lista.primeiro.repository;

import com.primeira_lista.primeiro.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
  
}
