package com.example.demo.repository;

import com.example.demo.Task;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.mapper.TaskRowMapper;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
@Slf4j
public class DataBaseTaskRepository implements TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Task> findAll() {
        log.debug("Calling findAll in DataBase");
        String sql = "select * from task";
        return jdbcTemplate.query(sql, new TaskRowMapper());
    }

    @Override
    public Optional<Task> findById(Long id) {
        log.debug("Calling findById in DataBase");
        String sql = "select * from task where id = ?";
        Task task = DataAccessUtils.singleResult(jdbcTemplate.query(sql,
                new ArgumentPreparedStatementSetter(new Object[]{id}),
                new RowMapperResultSetExtractor<>(new TaskRowMapper(), 1)));
        return Optional.ofNullable(task);
    }

    @Override
    public Task save(Task task) {
        log.debug("Calling save in DataBase");
        task.setId(System.currentTimeMillis());
        String sql = "INSERT INTO task (title, description, priority, id) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getPriority(), task.getId());
        return task;
    }

    @Override
    public Task update(Task task) {
        log.debug("Calling update in DataBase");
        Task existsTask = findById(task.getId()).orElse(null);
        if (existsTask != null) {
            String sql = "Update task set title = ?, description = ?, priority = ? where id = ?";
            jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getPriority(), task.getId());
            return task;
        }
        log.warn("Task not found");
        throw new TaskNotFoundException("Task not found");
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Calling deleteById in DataBase");
        String sql = "delete from task where id = ?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public void batchInsert(List<Task> tasks) {
        log.debug("Calling batchInsert in DataBase");
        String sql = "INSERT INTO task (id,title,descpription,priority) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Task task = tasks.get(i);
                ps.setInt(1, task.getId().intValue());
                ps.setString(2, task.getTitle());
                ps.setString(3, task.getDescription());
                ps.setInt(4, task.getPriority());
/*                ps.setString(1, task.getTitle());
                ps.setString(2, task.getDescription());
                ps.setInt(3, task.getPriority());
                ps.setLong(4, task.getId());*/
            }

            @Override
            public int getBatchSize() {
                return tasks.size();
            }
        });
    }
}
