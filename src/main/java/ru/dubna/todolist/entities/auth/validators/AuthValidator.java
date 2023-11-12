package ru.dubna.todolist.entities.auth.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import ru.dubna.todolist.entities.auth.dtos.AuthInputDto;
import ru.dubna.todolist.exceptions.specific.UnauthorizedException;
import ru.dubna.todolist.lib.Validator;

@Component
@RequiredArgsConstructor
public class AuthValidator implements Validator {
	private final AuthenticationManager authenticationManager;

	@Override
	public void validate(Object target) {
		AuthInputDto authInputDto = (AuthInputDto) target;

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authInputDto.getUsername(), authInputDto.getPassword()));
		} catch (BadCredentialsException exception) {
			throw new UnauthorizedException("Неправильный логин или пароль");
		}
	}
}
