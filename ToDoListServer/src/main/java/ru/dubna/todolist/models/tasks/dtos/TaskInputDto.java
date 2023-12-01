package ru.dubna.todolist.models.tasks.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dubna.todolist.models.tasks.TaskStatuses;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskInputDto {
	@NotBlank(message = "Название задачи не может быть пустым")
	private String name;

	private String description;

	private TaskStatuses status;
}
