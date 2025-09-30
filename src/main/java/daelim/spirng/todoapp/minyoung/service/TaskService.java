package daelim.spirng.todoapp.minyoung.service;

import daelim.spirng.todoapp.minyoung.dto.TaskRequest;
import daelim.spirng.todoapp.minyoung.dto.TaskResponse;

import java.util.List;

public interface TaskService {
    List<TaskResponse> findAll();
    TaskResponse findById(Long id);
    Long create(TaskRequest req);
    void update(Long id, TaskRequest req);
    void delete(Long id);
}
