package com.example.demo.service;

import com.example.demo.Task;
import com.example.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        log.debug("Call findAll in TaskServiceImpl");
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        log.debug("Call findById in TaskServiceImpl");
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task save(Task task) {
        log.debug("Call save in TaskServiceImpl");
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        log.debug("Call update in TaskServiceImpl");
        return taskRepository.update(task);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Call deleteById in TaskServiceImpl");
        taskRepository.deleteById(id);
    }

    @Override
    public void batchInsert(List<Task> tasks) {
        log.debug("Call batchInsert in TaskServiceImpl");
        taskRepository.batchInsert(tasks);
    }
}
