package ru.dubna.todolist.entities.tasks;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dubna.todolist.config.db.BaseEntity;
import ru.dubna.todolist.entities.user.User;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task extends BaseEntity {
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private TaskStatuses status;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Task(String name, String description, TaskStatuses status, User user) {
		this.name = name;
		this.description = description;
		this.status = status;
		this.user = user;
	}

	public Task(int id, String name, String description, TaskStatuses status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
	}
}
