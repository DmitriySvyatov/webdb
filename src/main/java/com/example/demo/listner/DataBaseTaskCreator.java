package com.example.demo.listner;

import com.example.demo.Task;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataBaseTaskCreator {

    private final TaskService taskService;

    @EventListener(ApplicationReadyEvent.class)
    public void createTaskData() {
        log.debug("Starting task creation");
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int value=i+1;
            Task task = new Task();
            task.setId((long)value);
            task.setTitle("Title "+value);
            task.setDescription("Description "+value);
            task.setPriority(value);
            tasks.add(task);
        }
        taskService.batchInsert(tasks);
    }
}
