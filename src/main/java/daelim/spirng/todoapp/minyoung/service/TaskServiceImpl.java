package daelim.spirng.todoapp.minyoung.service;

import daelim.spirng.todoapp.minyoung.domain.Task;
import daelim.spirng.todoapp.minyoung.dto.TaskRequest;
import daelim.spirng.todoapp.minyoung.dto.TaskResponse;
import daelim.spirng.todoapp.minyoung.repository.TaskRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repo;

    private TaskResponse toRes(Task t){
        return TaskResponse.builder()
                .id(t.getId())
                .title(t.getTitle())
                .description(t.getDescription())
                .completed(t.isCompleted())
                .build();
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> findAll() {
        return repo.findAll().stream().map(this::toRes).toList();
    }

    @Transactional(readOnly = true)
    public TaskResponse findById(Long id) {
        Task t = repo.findById(id).orElseThrow();
        return toRes(t);
    }

    public Long create(TaskRequest req) {
        boolean done = req.getCompleted() != null && req.getCompleted();
        Task t = Task.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .completed(done)
                .build();
        return repo.save(t).getId();
    }

    public void update(Long id, TaskRequest req) {
        Task t = repo.findById(id).orElseThrow();
        t.setTitle(req.getTitle());
        t.setDescription(req.getDescription());
        t.setCompleted(req.getCompleted() != null && req.getCompleted());
    }

    public void delete(Long id) { repo.deleteById(id); }
}
