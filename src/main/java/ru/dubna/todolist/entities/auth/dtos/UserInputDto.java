package ru.dubna.todolist.entities.auth.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInputDto {
	@NotBlank(message = "Имя пользователя не может быть пустым")
	@Size(min = 2, max = 30, message = "Имя пользователя должно быть от 2 до 30 символов длиной")
	private String username;

	@NotBlank(message = "Пароль не может быть пустым")
	@Size(min = 6, max = 200, message = "Пароль должен быть от 6 до 200 символов длиной")
	private String password;

	@NotBlank(message = "Пароль не может быть пустым")
	@Size(min = 6, max = 200, message = "Пароль должен быть от 6 до 200 символов длиной")
	private String confirmPassword;
}
