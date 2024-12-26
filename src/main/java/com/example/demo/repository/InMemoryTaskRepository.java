package com.example.demo.repository;

import com.example.demo.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class InMemoryTaskRepository implements TaskRepository {

    private List<Task> tasks=new ArrayList<>();

    @Override
    public List<Task> findAll() {
        log.debug("Call findAll InMemoryTaskRepository");
        return tasks;
    }

    @Override
    public Optional<Task> findById(Long id) {
        log.debug("Call find by id InMemoryTaskRepository");
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst();
    }

    @Override
    public Task save(Task task) {
        log.debug("Call save by id InMemoryTaskRepository");
        task.setId(System.currentTimeMillis());
        tasks.add(task);
        return task;
    }

    @Override
    public Task update(Task task) {
        log.debug("Call update InMemoryTaskRepository");
        Task existingTask = findById(task.getId()).orElse(null);
        if (existingTask != null) {
            existingTask.setPriority(task.getPriority());
            existingTask.setDescription(task.getDescription());
            existingTask.setTitle(task.getTitle());
        }
        return existingTask;
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Call update InMemoryTaskRepository");
        tasks.removeIf(task -> task.getId().equals(id));
    }

    @Override
    public void batchInsert(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }
}
