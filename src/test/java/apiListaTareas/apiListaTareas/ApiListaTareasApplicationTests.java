package apiListaTareas.apiListaTareas;

import apiListaTareas.controller.TaskController;
import apiListaTareas.entity.Task;
import apiListaTareas.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ApiListaTareasApplicationTests {

	@InjectMocks
	private TaskController taskController;

	@Mock
	private TaskRepository repository;

	@Test
	void createTask() {
		Task task = new Task(null, "Tarea de prueba", new Date(), true);
		when(repository.save(any(Task.class))).thenReturn(task);

		Task createdTask = taskController.createTask(task);

		assertThat(createdTask).isEqualTo(task);
		verify(repository).save(task);
	}
}
