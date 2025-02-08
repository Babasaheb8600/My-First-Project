package com.example.todolist.controller;

import com.example.todolist.model.Task;
import com.example.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")  // Base path for all endpoints
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // Display all tasks
    @GetMapping
    public String index(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "index";  // Returns the HTML template (index.html)
    }

    // Add a new task
    @PostMapping("/add")
    public String addTask(@RequestParam String name) {
        if (!name.trim().isEmpty()) {
            taskRepository.save(new Task(name));
        }
        return "redirect:/";  // Redirects to the home page after adding the task
    }

    // Delete a task
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        }
        return "redirect:/";  // Redirects after deletion
    }
}

