package com.learn.taskManager.Controllers;

import com.learn.taskManager.Entities.TaskEntity;
import com.learn.taskManager.dtos.CreateTaskDto;
import com.learn.taskManager.dtos.ErrorResponseDto;
import com.learn.taskManager.dtos.UpdateTaskDto;
import com.learn.taskManager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }

    @GetMapping("/")
    public ResponseEntity<List<TaskEntity>> getTasks(){
        var tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id){
        var task = taskService.getTaskById(id);
        if(task==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDto body) throws ParseException {
        var task = taskService.addtask(body.getTitle() , body.getDescription() , body.getDeadline());
        return ResponseEntity.ok(task);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id , @RequestBody UpdateTaskDto body) throws ParseException {
        var task = taskService.updateTask(id , body.getDescription(),body.getDeadline(),body.getCompleted());
        if(task==null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleErrors(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDto("Invalid Date Format"));
        }
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDto("Internal Server Error"));

    }

}
