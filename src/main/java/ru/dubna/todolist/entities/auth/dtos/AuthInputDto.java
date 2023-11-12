package ru.dubna.todolist.entities.auth.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthInputDto {
	@NotBlank(message = "Имя пользователя не может быть пустым")
	private String username;

	@NotBlank(message = "Пароль не может быть пустым")
	private String password;
}
