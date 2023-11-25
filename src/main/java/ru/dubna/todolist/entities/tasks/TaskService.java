package ru.dubna.todolist.entities.tasks;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.dubna.todolist.entities.tasks.dtos.TaskInputDto;
import ru.dubna.todolist.entities.user.User;
import ru.dubna.todolist.exceptions.specific.NotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {
	private final TaskRepository taskRepository;

	public Task create(TaskInputDto taskInputDto, User user) {
		Task task = new Task(taskInputDto.getName(), taskInputDto.getDescription(), taskInputDto.getStatus(), user);
		return taskRepository.save(task);
	}

	public Task update(int id, TaskInputDto taskInputDto, User user) {
		Task task = new Task(id, taskInputDto.getName(), taskInputDto.getDescription(), taskInputDto.getStatus());
		return taskRepository.save(task);
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
		return find(id).orElseThrow(() -> new NotFoundException(String.format("Задача '%d' не найдена", id)));
	}
}
