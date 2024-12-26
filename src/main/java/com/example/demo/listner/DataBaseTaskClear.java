package com.example.demo.listner;

import com.example.demo.Task;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataBaseTaskClear {

    private final TaskService taskService;

    @EventListener(ContextClosedEvent.class)
    public void clearTaskDatabase() {
        log.info("clearTaskDatabase");
        List<Task> tasks = taskService.findAll();
        for (Task task : tasks) {
            taskService.deleteById(task.getId());
        }
    }
}
