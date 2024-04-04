package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.exceptions.TaskNotFoundException;
import com.example.demo.exceptions.UserStoryNotFoundException;
import com.example.demo.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class TaskController {
    private final TaskRepository repository;

    public TaskController(TaskRepository taskRepository) {
        this.repository = taskRepository;
    }
    @GetMapping("/task")
    List<Task> getAll() {
        return repository.findAll();
    }


    @PostMapping("/task")
    Task newTask( @RequestBody Task task) {
        return repository.save(task);
    }


    @GetMapping("/task/{id}")
    Task getOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
    @PutMapping("/task/{id}")
    Task replaceTask(@RequestBody Task newTask, @PathVariable Long id) {

        return repository.findById(id)
                .map(task -> {
                    task.setTitle(newTask.getTitle());
                    task.setDescription(newTask.getDescription());
                    task.setStatus(newTask.getStatus());
                    task.setUserstory_id(newTask.getUserstory_id());
                    return repository.save(task);
                })
                .orElseThrow(() -> new UserStoryNotFoundException(id));
    }
    @DeleteMapping("/task/{id}")
    void deleteTask(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/task/search")
    List<Task> searchTasks(@RequestParam(value = "keyword") String keyword) {
        List<Task> allTasks = repository.findAll();
        return allTasks.stream()
                .filter(task -> task.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        task.getDescription().toLowerCase().contains(keyword.toLowerCase()) ||
                        task.getStatus().name().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    @GetMapping("/task/story/{storyId}")
    public List<Task> getTasksForStory(@PathVariable Long storyId) {
        return repository.findByUserStory_Id(storyId);
    }



    @GetMapping("/task/stats")
    public Map<Task.Status, Long> getTaskStats() {
        List<Task> allTasks = repository.findAll();

        return allTasks.stream()
                .collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));
    }



}
