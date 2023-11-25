package ru.dubna.todolist.entities.user;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dubna.todolist.config.db.BaseEntity;
import ru.dubna.todolist.entities.tasks.Task;

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
