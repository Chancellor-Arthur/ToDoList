package ru.dubna.todolist.entities.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.dubna.todolist.entities.auth.dtos.AuthOutputDto;
import ru.dubna.todolist.entities.auth.dtos.CredentialsDto;
import ru.dubna.todolist.entities.auth.dtos.UserInputDto;
import ru.dubna.todolist.entities.auth.validators.AuthValidator;
import ru.dubna.todolist.entities.auth.validators.RegistrationValidator;
import ru.dubna.todolist.entities.user.User;
import ru.dubna.todolist.entities.user.UserService;
import ru.dubna.todolist.entities.user.dtos.UserOutputDto;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
	private final UserService userService;
	private final RegistrationValidator registrationValidator;
	private final AuthValidator authValidator;

	public AuthOutputDto signIn(CredentialsDto credentialsDto) {
		authValidator.validate(credentialsDto);

		UserDetails userDetails = userService.loadUserByUsername(credentialsDto.getUsername());
		return new AuthOutputDto(userDetails.getUsername());
	}

	public UserOutputDto signUp(UserInputDto userInputDto) {
		registrationValidator.validate(userInputDto);

		User user = userService.create(userInputDto);
		return new UserOutputDto(user.getId(), user.getUsername());
	}
}
