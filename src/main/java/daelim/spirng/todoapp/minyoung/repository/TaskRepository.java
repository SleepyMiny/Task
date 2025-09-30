package daelim.spirng.todoapp.minyoung.repository;

import daelim.spirng.todoapp.minyoung.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
