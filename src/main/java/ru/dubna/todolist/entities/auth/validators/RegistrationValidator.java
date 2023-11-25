package ru.dubna.todolist.entities.auth.validators;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ru.dubna.todolist.entities.auth.dtos.UserInputDto;
import ru.dubna.todolist.entities.user.UserService;
import ru.dubna.todolist.exceptions.specific.UnauthorizedException;

@Component
@RequiredArgsConstructor
public class RegistrationValidator implements Validator {
	private final UserService userService;

	public void validate(Object target) {
		UserInputDto userInputDto = (UserInputDto) target;

		if (!userInputDto.getPassword().equals(userInputDto.getConfirmPassword()))
			throw new UnauthorizedException("Пароли не совпадают");

		if (userService.findByUsername(userInputDto.getUsername()).isPresent())
			throw new UnauthorizedException("Пользователь с указанным именем уже существует");
	}
}
