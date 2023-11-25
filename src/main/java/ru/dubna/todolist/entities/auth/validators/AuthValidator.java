package ru.dubna.todolist.entities.auth.validators;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ru.dubna.todolist.entities.auth.dtos.CredentialsDto;
import ru.dubna.todolist.exceptions.specific.UnauthorizedException;

@Component
@RequiredArgsConstructor
public class AuthValidator implements Validator {
	private final AuthenticationManager authenticationManager;

	@Override
	public void validate(Object target) {
		CredentialsDto credentialsDto = (CredentialsDto) target;

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentialsDto.getUsername(),
					credentialsDto.getPassword()));
		} catch (BadCredentialsException exception) {
			throw new UnauthorizedException("Неправильный логин или пароль");
		}
	}
}
