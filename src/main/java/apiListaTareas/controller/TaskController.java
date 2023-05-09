package apiListaTareas.controller;

import apiListaTareas.entity.Task;
import apiListaTareas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @CrossOrigin(origins = "*")
    @PostMapping("/crearTarea")
    public Task createTask(@RequestBody Task task){
        return repository.save(task);
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") Long taskId){
        Optional<Task> task = repository.findById(taskId);
        return task.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable(value = "id") Long taskId,
                                               @RequestBody Task taskDetails){
        Optional<Task> task = repository.findById(taskId);
        if(task.isPresent()) {
            Task updatedTask = task.get();
            updatedTask.setDescripcion(taskDetails.getDescripcion());
            repository.save(updatedTask);
            return ResponseEntity.ok(updatedTask);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable(value = "id") Long taskId) {
        Optional<Task> task = repository.findById(taskId);
        if(task.isPresent()) {
            repository.delete(task.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
