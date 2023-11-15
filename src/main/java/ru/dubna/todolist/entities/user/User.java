package ru.dubna.todolist.entities.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dubna.todolist.config.db.BaseEntity;
import ru.dubna.todolist.entities.tasks.Task;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@OneToMany(mappedBy = "user")
	private List<Task> tasks;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
