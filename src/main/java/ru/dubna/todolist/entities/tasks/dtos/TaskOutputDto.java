package ru.dubna.todolist.entities.tasks.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dubna.todolist.entities.tasks.TaskStatuses;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskOutputDto {
	private int id;
	private String name;
	private String description;
	private TaskStatuses status;
}
