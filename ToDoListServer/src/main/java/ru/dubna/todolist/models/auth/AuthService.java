package ru.dubna.todolist.models.auth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.dubna.todolist.models.auth.dtos.CookieInfoDto;
import ru.dubna.todolist.models.auth.dtos.CredentialsDto;
import ru.dubna.todolist.models.auth.dtos.UserInputDto;
import ru.dubna.todolist.models.auth.validators.AuthValidator;
import ru.dubna.todolist.models.auth.validators.RegistrationValidator;
import ru.dubna.todolist.models.user.User;
import ru.dubna.todolist.models.user.UserService;
import ru.dubna.todolist.models.user.dtos.UserOutputDto;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
	private final UserService userService;
	private final RegistrationValidator registrationValidator;
	private final AuthValidator authValidator;

	public CookieInfoDto signIn(CredentialsDto credentialsDto) {
		authValidator.validate(credentialsDto);

		User user = userService.getByUsername(credentialsDto.getUsername());
		return new CookieInfoDto(user.getId(), user.getUsername());
	}

	public UserOutputDto signUp(UserInputDto userInputDto) {
		registrationValidator.validate(userInputDto);

		User user = userService.create(userInputDto);
		return new UserOutputDto(user.getId(), user.getUsername());
	}
}
