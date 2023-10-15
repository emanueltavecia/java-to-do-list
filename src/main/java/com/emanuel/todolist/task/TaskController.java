package com.emanuel.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emanuel.todolist.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  @Autowired
  private ITaskRepository taskRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    var idUser = request.getAttribute("idUser");
    taskModel.setIdUser((UUID) idUser);
    var currentDate = LocalDateTime.now();
    if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
      return ResponseEntity.status(400).body("The start and end date must be greater than the current one.");
    }
    if(taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
      return ResponseEntity.status(400).body("The start date must be before the end date.");
    }
    var task = this.taskRepository.save(taskModel);
    return ResponseEntity.status(200).body(task);
  }

  @GetMapping("/")
  public List<TaskModel> list(HttpServletRequest request) {
    var idUser = request.getAttribute("idUser");
    var tasks = this.taskRepository.findByIdUser((UUID) idUser);
    return tasks;
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request) {
    
    var task = this.taskRepository.findById(id).orElse(null);

    if (task == null) {
      return ResponseEntity.status(400).body("Task not found.");
    }

    var idUser = request.getAttribute("idUser");
    if (!task.getIdUser().equals(idUser)) {
      return ResponseEntity.status(401).body("You don't have permission to change this task.");
    }

    Utils.copyNonNullProperties(taskModel, task);
    
    var taskUpdated = this.taskRepository.save(task);
    return ResponseEntity.ok().body(taskUpdated);
  }
}
