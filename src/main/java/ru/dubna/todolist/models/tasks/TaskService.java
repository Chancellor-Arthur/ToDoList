package ru.dubna.todolist.models.tasks;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.dubna.todolist.exceptions.specific.AccessDeniedException;
import ru.dubna.todolist.exceptions.specific.NotFoundException;
import ru.dubna.todolist.models.tasks.dtos.TaskInputDto;
import ru.dubna.todolist.models.user.User;
import ru.dubna.todolist.models.user.UserService;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {
	private final UserService userService;
	private final TaskRepository taskRepository;

	private void checkOwner(int taskId, int userId) {
		User user = userService.getById(userId);
		Task oldTask = get(taskId);

		if (!oldTask.getUser().equals(user))
			throw new AccessDeniedException("Пользователь не является обладателем задачи");
	}

	public Task create(TaskInputDto taskInputDto, int userId) {
		Task task = new Task(taskInputDto.getName(), taskInputDto.getDescription(), taskInputDto.getStatus(),
				new User(userId));
		return taskRepository.save(task);
	}

	public Task update(int id, TaskInputDto taskInputDto, int userId) {
		checkOwner(id, userId);

		Task newTask = new Task(id, taskInputDto.getName(), taskInputDto.getDescription(), taskInputDto.getStatus(),
				new User(userId));
		return taskRepository.save(newTask);
	}

	public Task patch(int id, TaskInputDto taskInputDto, int userId) {
		checkOwner(id, userId);

		Task newTask = get(id);

		if (taskInputDto.getName() != null && !taskInputDto.getName().isBlank())
			newTask.setName(taskInputDto.getName());

		if (taskInputDto.getDescription() != null && !taskInputDto.getDescription().isBlank())
			newTask.setDescription(taskInputDto.getDescription());

		if (taskInputDto.getStatus() != null)
			newTask.setStatus(taskInputDto.getStatus());

		return taskRepository.save(newTask);
	}

	public void delete(int id) {
		taskRepository.deleteById(id);
	}

	public Optional<Task> find(int id) {
		return taskRepository.findById(id);
	}

	public List<Task> getAll(int userId) {
		return taskRepository.findAllByUserId(userId);
	}

	public Task get(int id) {
		return find(id).orElseThrow(() -> new NotFoundException(String.format("Задача: '%d' не найдена", id)));
	}
}
